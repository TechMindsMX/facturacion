package com.tim.one.billing.services

import com.tim.one.billing.model.*

interface FacturaServicio {
  Factura generaFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos)
  File generaPdfDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos)
  File generaXmlDeFactura(DatosDeFacturacion datosDeFacturacion, Contribuyente emisor, Contribuyente receptor, List<Concepto> conceptos)
}
