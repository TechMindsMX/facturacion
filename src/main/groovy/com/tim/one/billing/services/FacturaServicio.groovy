package com.tim.one.billing.services

import java.util.List;

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Factura
import com.tim.one.billing.model.Impuesto
import com.tim.one.billing.model.Total

interface FacturaServicio {
  Factura generaFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos, List<Impuesto> impuestos, Total totales)
  File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos, List<Impuesto> impuestos, Total totales)

  File showPdfFacturaWithFolio(String folio, String format)
  File showXmlFacturaWithFolio(String folio, String format)
}
