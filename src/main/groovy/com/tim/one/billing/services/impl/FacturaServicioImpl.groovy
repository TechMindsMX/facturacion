package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.xhtmlrenderer.pdf.ITextRenderer

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Factura
import com.tim.one.billing.model.Impuesto
import com.tim.one.billing.model.Total
import com.tim.one.billing.services.FacturaServicio
import com.tim.one.billing.state.ApplicationState

/**
 * @author sohjiro
 * @author josdem
 * @understands Service who know how to generate and show factura
 *
 */

@Service
class FacturaServicioImpl implements FacturaServicio {
	
	@Autowired
	Properties properties

	String templateLogo
  String templatePdf
  String templateXml
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		templateLogo = properties.getProperty(ApplicationState.FACTURA_TEMPLATE_LOGO)
		templatePdf = properties.getProperty(ApplicationState.FACTURA_TEMPLATE_PDF)
		templateXml = properties.getProperty(ApplicationState.FACTURA_TEMPLATE_XML)
	}
	
  @Override
  public Factura generaFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar, List<Impuesto> impuestos, Total totales) {
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

    factura.impuestos = impuestos
    factura.subTotal = totales.subtotal
		factura.subTotal = totales.totalImpuestosTrasladados
    factura.total = totales.total

    factura
  }

  @Override
  public File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente contribuyenteEmisor, Contribuyente contribuyenteReceptor, List<Concepto> conceptosAFacturar, List<Impuesto> impuestos, Total totales) {
    Factura factura = generaFactura(datosDeFacturacion, contribuyenteEmisor, contribuyenteReceptor, conceptosAFacturar, impuestos, totales)
    def engine = new groovy.text.SimpleTemplateEngine()
    def file = new File(templateXml)
    def text = file.text

    def result = engine.createTemplate(text).make(factura.properties)
    def temporalXmlFile= File.createTempFile(System.currentTimeMillis().toString(),".xml")
    temporalXmlFile.text = result
		log.info("facturaXml: " + temporalXmlFile)
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

}
