package com.tim.one.billing.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.Factura
import com.tim.one.billing.model.Impuesto
import com.tim.one.billing.model.Total
import com.tim.one.billing.services.FacturaServicio

@ContextConfiguration(locations=["classpath:/services-appctx.xml", "classpath:/properties-appctx.xml"])
class FacturaServicioSpec extends Specification {

  @Autowired
  FacturaServicio facturaServicio

  def "Expedicion de factura por cien pesos"(){
    given: "Un emisor y receptor"
      def numeroDeCuentaDePago = "0123456789"
      Contribuyente emisor = new Contribuyente()
      Contribuyente receptor = new Contribuyente(cuenta:numeroDeCuentaDePago)
    and: "Datos de la factura"
      DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
        uuid: "UUID",
        formaDePago: "Pago en una sola exhibicion",
        tipoDeComprobante: "ingreso",
        lugarDeExpedicion: "Mexico DF",
        condicionesDePago: "Contado",
        metodoDePago : "Transferencia electronica de fondos",
        numeroDeCuentaDePago: numeroDeCuentaDePago,
        moneda: "MXN",
        tipoDeCambio: "1.00"
      )
    and: "Un concepto a facturar"
      Concepto concepto = new Concepto()
      concepto.valorUnitario = 100.00
      concepto.descripcion = "Servicio de cafe"
      concepto.unidad = "NO APLICA"
      concepto.cantidad = 1
      def conceptos = []
      conceptos << concepto
		and: "Unos impuestos a facturar"
			Impuesto impuesto = new Impuesto()
			impuesto.tasa = 16
			impuesto.importe = 16.00
			impuesto.impuesto = "IVA"
			def impuestos = []
			impuestos << impuesto
		and: "Totales"
		  def totales = new Total(
			  total:16.00,
			  subtotal:16.00
		  )
    when: "Solicitamos la factura"
      Factura factura = facturaServicio.generaFactura(datosDeFacturacion, emisor, receptor, conceptos, impuestos, totales)
    then: "Verificamos la estructura de la factura"
      factura.datosDeFacturacion.uuid
      factura.datosDeFacturacion.formaDePago
      factura.datosDeFacturacion.tipoDeComprobante
      factura.datosDeFacturacion.lugarDeExpedicion
      factura.datosDeFacturacion.condicionesDePago
      factura.datosDeFacturacion.metodoDePago
      factura.datosDeFacturacion.numeroDeCuentaDePago
      factura.datosDeFacturacion.moneda
      factura.datosDeFacturacion.tipoDeCambio
      factura.subTotal == 16.00
      factura.total == 16.00
      factura.emisor == emisor
      factura.receptor == receptor
      factura.conceptos.get(0).descripcion == "Servicio de cafe"
      factura.conceptos.get(0).valorUnitario == 100.00
      factura.conceptos.get(0).unidad == "NO APLICA"
      factura.conceptos.get(0).cantidad == 1
      factura.impuestos.get(0).importe == 16.00
      factura.impuestos.get(0).tasa == 16
      factura.impuestos.get(0).impuesto == "IVA"
  }

  def "genera factura con una lista de conceptos"(){
    given: "Un emisor y receptor"
      def numeroDeCuentaDePago = "0123456789"
      Contribuyente emisor = new Contribuyente()
      Contribuyente receptor = new Contribuyente(cuenta:numeroDeCuentaDePago)
    and: "Datos de la factura"
      DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
        uuid: "UUID",
        formaDePago: "Pago en una sola exhibicion",
        tipoDeComprobante: "ingreso",
        lugarDeExpedicion: "Mexico DF",
        condicionesDePago: "Contado",
        metodoDePago : "Transferencia electronica de fondos",
        numeroDeCuentaDePago: numeroDeCuentaDePago,
        moneda: "MXN",
        tipoDeCambio: "1.00"
      )
    and: "Una lista de conceptos a facturar"
      def conceptos = []
      conceptos << new Concepto(
        cantidad:1,
        descripcion:"descripcion1",
        unidad:"pieza",
        valorUnitario:100.00
      )
      conceptos << new Concepto(
        cantidad:1,
        descripcion:"descripcion2",
        unidad:"kilogramos",
        valorUnitario:100.00
      )
		and: "Unos impuestos a facturar"
			Impuesto impuesto = new Impuesto()
			impuesto.tasa =16
			impuesto.impuesto = "IVA"
			def impuestos = []
			impuestos << impuesto
	  and: "Totales"
			def totales = new Total(
				total:16.00,
				subtotal:16.00
			)
    when: "Solicitamos la factura"
      Factura factura = facturaServicio.generaFactura(datosDeFacturacion, emisor, receptor, conceptos, impuestos, totales)
    then: "Verificamos la estructura de la factura"
      factura.datosDeFacturacion.uuid
      factura.datosDeFacturacion.formaDePago
      factura.datosDeFacturacion.tipoDeComprobante
      factura.datosDeFacturacion.lugarDeExpedicion
      factura.datosDeFacturacion.condicionesDePago
      factura.datosDeFacturacion.metodoDePago
      factura.datosDeFacturacion.numeroDeCuentaDePago
      factura.datosDeFacturacion.moneda
      factura.datosDeFacturacion.tipoDeCambio
      factura.subTotal == 16.00
      factura.total == 16.00
      factura.emisor
      factura.receptor
      factura.conceptos.size() == 2
      factura.impuestos.size() == 1
  }

  def "genera factura con un concepto y con condiciones de facturacion"(){
    given: "Un emisor y receptor"
      def numeroDeCuentaDePago = "0123456789"
      Contribuyente emisor = new Contribuyente()
      Contribuyente receptor = new Contribuyente(cuenta:numeroDeCuentaDePago)
    and: "Datos de la factura"
      DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
        uuid: "UUID",
        formaDePago: "Pago en una sola exhibicion",
        tipoDeComprobante: "ingreso",
        lugarDeExpedicion: "Mexico DF",
        condicionesDePago: "Contado",
        metodoDePago : "Transferencia electronica de fondos",
        numeroDeCuentaDePago: numeroDeCuentaDePago,
        moneda: "MXN",
        tipoDeCambio: "1.00"
      )
    and: "Una lista de conceptos a facturar"
      def conceptos = []
      conceptos << new Concepto(
        cantidad:1,
        descripcion:"descripcion1",
        unidad:"pieza",
        valorUnitario:100.00
      )
		and: "Unos impuestos a facturar"
			Impuesto impuesto = new Impuesto()
			impuesto.tasa =16
			impuesto.impuesto = "IVA"
			def impuestos = []
			impuestos << impuesto
	  and: "Totales"
			def totales = new Total(
				total:16.00,
				subtotal:16.00
			)
    when: "Solicitamos la factura"
      Factura factura = facturaServicio.generaFactura(datosDeFacturacion, emisor, receptor, conceptos, impuestos, totales)
    then: "Verificamos la estructura de la factura"
      factura.datosDeFacturacion.uuid
      factura.datosDeFacturacion.formaDePago == "Pago en una sola exhibicion"
      factura.datosDeFacturacion.tipoDeComprobante == "ingreso"
      factura.datosDeFacturacion.lugarDeExpedicion == "Mexico DF"
      factura.datosDeFacturacion.condicionesDePago == "Contado"
      factura.datosDeFacturacion.metodoDePago == "Transferencia electronica de fondos"
      factura.datosDeFacturacion.numeroDeCuentaDePago == receptor.getCuenta()
      factura.datosDeFacturacion.moneda == "MXN"
      factura.datosDeFacturacion.tipoDeCambio == "1.00"
      factura.subTotal == 16.00
      factura.total == 16.00
      factura.emisor
      factura.receptor
      factura.conceptos.size() == 1
      factura.impuestos.size() == 1
  }

  def "Genera excepciones"(){
    given: "Datos de facturacion"
      DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
        uuid: "UUID",
        formaDePago: "Pago en una sola exhibicion",
        tipoDeComprobante: "ingreso",
        lugarDeExpedicion: "Mexico DF",
        condicionesDePago: "Contado",
        metodoDePago : "Transferencia electronica de fondos",
        numeroDeCuentaDePago: "0123456789",
        moneda: "MXN",
        tipoDeCambio: "1.00"
      )
    when: "Solicitamos la factura"
      Factura factura = facturaServicio.generaFactura(datosDeFacturacion, emisor, receptor, conceptos)
    then: "Verificamos la estructura de la factura"
      thrown RuntimeException
    where:
      emisor              | receptor            | conceptos
      null                | null                | []
      new Contribuyente() | null                | []
      null                | new Contribuyente() | []
      new Contribuyente() | new Contribuyente() | null
      new Contribuyente() | new Contribuyente() | []
      new Contribuyente() | new Contribuyente() | [new Concepto(cantidad:0)]
      new Contribuyente() | new Contribuyente() | [new Concepto()]
      new Contribuyente() | new Contribuyente() | [new Concepto(valorUnitario:100)]
  }


}
