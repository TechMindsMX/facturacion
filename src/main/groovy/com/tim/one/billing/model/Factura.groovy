package com.tim.one.billing.model

class Factura {

  DatosDeFacturacion datosDeFacturacion

  String folio
  BigDecimal subTotal = 0.0
  BigDecimal total = 0.0
	BigDecimal totalImpuestosTrasladados = 0.0
  Date fechaDeCreacion

  Contribuyente emisor
  Contribuyente receptor

  List<Concepto> conceptos = []
  List<Impuesto> impuestos = []

}
