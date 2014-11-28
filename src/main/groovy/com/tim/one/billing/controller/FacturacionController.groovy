package com.tim.one.billing.controller

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import com.tim.one.billing.services.FacturaServicio
import com.tim.one.billing.model.*

import javax.servlet.http.HttpServletResponse
import org.springframework.util.FileCopyUtils

@RestController
@RequestMapping("/facturacion")
class FacturacionController {

  @Autowired
  FacturaServicio facturaServicio

  @RequestMapping(method = RequestMethod.POST, value="/save")
  def createFacturaAndGenerateFolio() {
    println "WITH FOLIO"
  }

  @RequestMapping(method = RequestMethod.POST, value="/create")
  def createFacturaWithoutGeneratingFolio(FacturaCreateCommand command) {
    def file = facturaServicio."genera${command.format}DeFactura"(command.datosDeFacturacion, command.contribuyenteEmisor, command.contribuyenteReceptor, command.conceptosAFacturar)

    def fis = new FileInputStream(file)
    response.setContentType("application/${command.format}")
    response.setContentLength(((int) file.size()))
    response.setHeader("Content-Disposition","attachment filename=\"" + file.name +"\"")
    FileCopyUtils.copy(fis, response.getOutputStream())

    null
  }

  @RequestMapping(method = RequestMethod.GET)
  def show(FacturaShowCommand command, HttpServletResponse response) {
    String format = command.format.toLowerCase().capitalize()
    def file = facturaServicio."show${format}FacturaWithFolio"(command.folio, command.format)

    def fis = new FileInputStream(file)
    response.setContentType("application/${command.format}")
    response.setContentLength(((int) file.size()))
    response.setHeader("Content-Disposition","attachment filename=\"" + file.getName() +"\"")
    FileCopyUtils.copy(fis, response.getOutputStream())

    null
  }

}

class FacturaCreateCommand {
  DatosDeFacturacion datosDeFacturacion
  Contribuyente emisor
  Contribuyente receptor

  List<Concepto> conceptos
  List<Impuesto> impuestos
  String format = 'pdf'
}

class FacturaShowCommand {
  String folio
  String format = 'pdf'
}
