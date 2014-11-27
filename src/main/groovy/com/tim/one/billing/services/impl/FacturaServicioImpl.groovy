package com.tim.one.billing.services.impl

import com.tim.one.billing.model.*
import org.springframework.stereotype.*
import com.tim.one.billing.services.FacturaServicio

import com.lowagie.text.DocumentException
import org.xhtmlrenderer.pdf.ITextRenderer

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.DocumentBuilder

@Service
class FacturaServicioImpl implements FacturaServicio {

  @Override
  public Factura generaFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar) {
    if(!contribuyenteEmisor || !contribuyenteReceptor) {
      throw new RuntimeException("Contribuyente not found")
    }

    if(!conceptosAFacturar) {
      throw new RuntimeException("Conceptos not found")
    }

    def factura = new Factura()
    factura.datosDeFacturacion = datosDeFacturacion
    factura.emisor = contribuyenteEmisor
    factura.receptor = contribuyenteReceptor
    factura.fechaDeCreacion = new Date()

    conceptosAFacturar.each { conceptoAFacturar ->
      if(conceptoAFacturar.cantidad <= 0) {
        throw new RuntimeException("Cantidades a facturar no deben ser menores o iguales a 0")
      }

      if(!conceptoAFacturar.valorUnitario) {
        throw new RuntimeException("Valores unitarios menores a 0")
      }

      if(!conceptoAFacturar.descripcion) {
        throw new RuntimeException("Descripcion del concepto requerida")
      }

      def conceptoDeFacturacion = new Concepto(
        cantidad:conceptoAFacturar.cantidad,
        descripcion:conceptoAFacturar.descripcion,
        unidad:conceptoAFacturar.unidad ?: "No aplica",
        valorUnitario:conceptoAFacturar.valorUnitario
      )
      factura.conceptos << conceptoDeFacturacion
    }

    def impuestos = [new Impuesto(tasa:"16.00", impuesto:"IVA")]
    factura.subTotal = calculaSubtotal(factura)
    factura.impuestos = calculaImpuestos(factura.subTotal, impuestos)
    factura.total = calculaTotal(factura)

    factura
  }

  @Override
  public File generaPdfDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar) {
    println "da fuq!"
    Properties properties = new Properties()
    String propertyFileName = "config/facturacion.properties"
    println "propertyFileName : ${propertyFileName}"
    def inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)
    println "inputStream : ${inputStream}"
    properties.load(inputStream)

    println "generando"
    Factura factura = generaFactura(datosDeFacturacion, contribuyenteEmisor, contribuyenteReceptor, conceptosAFacturar)
    println "creando"
    def engine = new groovy.text.SimpleTemplateEngine()
    def file = new File(properties.get("factura.template"))
    println "asignando"
    def text = file.text

    def xhtmlWriter = new StringWriter()
    def bindings = factura.properties + [logo:properties.get("empresa.logo")]
    engine.createTemplate(text).make(bindings).writeTo(xhtmlWriter)
    def xhtml = xhtmlWriter.toString()
    xhtmlWriter.close()

    ITextRenderer renderer = new ITextRenderer()
    renderer.setDocumentFromString(xhtml)
    renderer.layout()

    def temporalFileName = "${System.currentTimeMillis()}.pdf".toString()
    OutputStream os = new FileOutputStream(temporalFileName)
    renderer.createPDF(os)
    new File("${temporalFileName}".toString())
  }

  @Override
  public File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar) {
    Properties properties = new Properties()
    String propertyFileName = "config/facturacion.properties"
    def inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)
    properties.load(inputStream)

    Factura factura = generaFactura(datosDeFacturacion, contribuyenteEmisor, contribuyenteReceptor, conceptosAFacturar)
    def engine = new groovy.text.SimpleTemplateEngine()
    def file = new File(properties.get("factura.template.xml"))
    def text = file.text

    def result = engine.createTemplate(text).make(factura.properties)
    def temporalXmlFileName = "${System.currentTimeMillis()}.xml".toString()
    def xmlFileName = new File(temporalXmlFileName)
    xmlFileName.text = result
    xmlFileName
  }

  @Override
  File showPdfFacturaWithFolio(String folio, String format) {
    println "showing pdf"
  }

  @Override
  File showXmlFacturaWithFolio(String folio, String format) {
    println "showing xml"
  }

  private def calculaSubtotal(factura) {
    factura.conceptos.sum(0) { it.valorUnitario * it.cantidad }
  }

  private def calculaImpuestos(subTotal, impuestos) {
    def impuestosTemp = []
    impuestos.each { impuesto ->
      def impuestoTemp = new Impuesto()
      impuestoTemp.tasa = impuesto.tasa
      impuestoTemp.impuesto = impuesto.impuesto
      impuestoTemp.importe = (subTotal * (impuesto.tasa.toBigDecimal()/100))
      impuestosTemp << impuestoTemp
    }
    impuestosTemp
  }

  private def calculaTotal(factura) {
    def subTotal = calculaSubtotal(factura)
    def totalImpuestos = factura.impuestos.sum(0) { it.importe.toBigDecimal() }
    def total = subTotal + totalImpuestos
    total
  }

}
