package com.tim.one.billing.controller

import javax.servlet.http.HttpServletResponse

import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import spock.lang.Specification

class FacturaControllerSpec extends Specification {
	
	def facturacionController = new FacturacionController()

  def "Expedicion de factura parametro incorrecto en cantidad"(){
		given: "A json"
		  String json = "{\"emisor\":{\"datosFiscales\":{\"rfc\":\"AAD990814BP7\",\"razonSocial\":\"Integradora de Emprendimientos Culturales S.A. de C.V.\",\"codigoPostal\":\"11850\",\"pais\":\"MEXICO\",\"ciudad\":\"Ciudad de México\",\"delegacion\":\"Miguel Hidalgo\",\"calle\":\"Tiburcio Montiel\",\"regimen\":\"PERSONA MORAL\"}},\"receptor\":{\"datosFiscales\":{\"rfc\":\"AAD990814BP7\",\"razonSocial\":\"Empresa Cliente - Proveedor\",\"codigoPostal\":\"54910\",\"pais\":\"MEXICO\",\"ciudad\":\"Tultitlán de Mariano Escobedo\",\"delegacion\":\"Tultitlán\",\"calle\":\"Saltillo\",\"regimen\":\"PERSONA MORAL\"}},\"datosDeFacturacion\":{\"moneda\":\"MXN\",\"lugarDeExpedicion\":\"Chihuahua\",\"numeroDeCuentaDePago\":\"DESCONOCIDO\",\"formaDePago\":\"PAGO EN UNA SOLA EXHIBICION\",\"metodoDePago\":\"TRANSFERENCIA ELECTRONICA\",\"tipoDeComprobante\":\"ingreso\"},\"conceptos\":[{\"valorUnitario\":\"1500\",\"descripcion\":\"Boleto concierto nuevo\",\"cantidad\":\"01\",\"unidad\":\"boleto\"}],\"impuestos\":[{\"importe\":165,\"tasa\":11,\"impuesto\":\"IVA\"},{\"importe\":165,\"tasa\":11,\"impuesto\":\"IEPS\"}],\"timbra\":true,\"format\":\"Xml\",\"totales\":{\"total\":1830,\"subtotal\":1500,\"totalImpuestosTrasladados\":330,\"folio\":\"B2\"}}"
		and: "A response"
		  def response = Mock(HttpServletResponse)	
		when: "We send factura"
		  ResponseEntity<String> result = facturacionController.createFacturaWithoutGeneratingFolio(json, response)
		then: "We expect exception"
	    result.getStatusCode() == HttpStatus.BAD_REQUEST
  }

}
