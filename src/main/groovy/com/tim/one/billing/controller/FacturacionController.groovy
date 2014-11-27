package com.tim.one.billing.controller

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import com.tim.one.billing.services.FacturaServicio

import javax.servlet.http.HttpServletResponse
import org.springframework.util.FileCopyUtils

@RestController
@RequestMapping("/facturacion")
class FacturacionController {

  @Autowired
  FacturaServicio facturaServicio

  @RequestMapping(method = RequestMethod.GET)
  def show(FacturaShowCommand command, HttpServletResponse response) {
    String format = command.format.toLowerCase().capitalize()
    def factura = facturaServicio."show${format}FacturaWithFolio"(command.folio, command.format)
    def file = new File('file.xml')
    def fis = new FileInputStream(file)

    response.setContentType("application/xml")
    response.setContentLength(((int) file.size()))
    response.setHeader("Content-Disposition","attachment filename=\"" + file.getName() +"\"")
    FileCopyUtils.copy(fis, response.getOutputStream())

    return null
  }
}

class FacturaShowCommand {
  String folio
  String format
}
