package com.tim.one.billing.controller

import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import com.tim.one.billing.command.FacturaCreateCommand
import com.tim.one.billing.command.FacturaShowCommand
import com.tim.one.billing.services.FacturaServicio

@Controller
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
