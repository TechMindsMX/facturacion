package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.xhtmlrenderer.pdf.ITextRenderer

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Factura
import com.tim.one.billing.model.Impuesto
import com.tim.one.billing.services.FacturaServicio

/**
 * @author sohjiro
 * @author josdem
 * @understands Service who know how to 
 *
 */

@Service
class FacturaServicioImpl implements FacturaServicio {
	
	@Autowired
	Properties properties

	String templateLogo
  String templatePdf
  String templateXml

	@PostConstruct
	public void initialize(){
		templateLogo = properties.getProperty("factura.template.logo");
		templatePdf = properties.getProperty("factura.template.pdf");
		templateXml = properties.getProperty("factura.template.xml");
	}
	
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
    Factura factura = generaFactura(datosDeFacturacion, contribuyenteEmisor, contribuyenteReceptor, conceptosAFacturar)
    def engine = new groovy.text.SimpleTemplateEngine()
    def file = new File(templatePdf)
    def text = file.text

    def xhtmlWriter = new StringWriter()
    def bindings = factura.properties + [logo:templateLogo]
    engine.createTemplate(text).make(bindings).writeTo(xhtmlWriter)
    def xhtml = xhtmlWriter.toString()
    xhtmlWriter.close()

    ITextRenderer renderer = new ITextRenderer()
    renderer.setDocumentFromString(xhtml)
    renderer.layout()

    def temporalFile= File.createTempFile(System.currentTimeMillis().toString(), ".pdf")
    OutputStream os = new FileOutputStream(temporalFile)
    renderer.createPDF(os)
    temporalFile
  }

  @Override
  public File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar) {
    Factura factura = generaFactura(datosDeFacturacion, contribuyenteEmisor, contribuyenteReceptor, conceptosAFacturar)
    def engine = new groovy.text.SimpleTemplateEngine()
    def file = new File(templateXml)
    def text = file.text

    def result = engine.createTemplate(text).make(factura.properties)
    def temporalXmlFile= File.createTempFile(System.currentTimeMillis().toString(),".xml")
    temporalXmlFile.text = result
    temporalXmlFile
  }

  @Override
  File showPdfFacturaWithFolio(String folio, String format) {
    def file= File.createTempFile("factura",".pdf")
    file.text = "facturando"
    file
  }

  @Override
  File showXmlFacturaWithFolio(String folio, String format) {
    def file= File.createTempFile("factura",".xml")
    file.text = """<?xml version='1.0' encoding='UTF-8'?>
    <note>
      <to>Tove</to>
      <from>Jani</from>
      <heading>Reminder</heading>
      <body>Don't forget me this weekend!'</body>
    </note>
    """
    file
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
