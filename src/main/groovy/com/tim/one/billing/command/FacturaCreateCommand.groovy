package com.tim.one.billing.command;

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Impuesto

class FacturaCreateCommand {
	DatosDeFacturacion datosDeFacturacion
  Contribuyente emisor
  Contribuyente receptor

  List<Concepto> conceptos
  List<Impuesto> impuestos
  String format = 'pdf'
}
