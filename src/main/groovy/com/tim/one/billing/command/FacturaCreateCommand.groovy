package com.tim.one.billing.command;

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Impuesto
import com.tim.one.billing.model.Total


class FacturaCreateCommand {
	DatosDeFacturacion datosDeFacturacion
  Contribuyente emisor
  Contribuyente receptor

  List<Concepto> conceptos
  List<Impuesto> impuestos
	List<Total> totales
  Boolean timbra = true
}
