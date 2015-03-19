package com.tim.one.billing.services

import com.tim.one.billing.model.Concepto

import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Factura
import com.tim.one.billing.model.Impuesto

interface FacturaServicio {
  Factura generaFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos, List<Impuesto> impuestos)
  File generaPdfDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos, List<Impuesto> impuestos)
  File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos, List<Impuesto> impuestos)

  File showPdfFacturaWithFolio(String folio, String format)
  File showXmlFacturaWithFolio(String folio, String format)
}
