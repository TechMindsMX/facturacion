package com.tim.one.billing.controller

import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import com.tim.one.billing.command.FacturaCreateCommand
import com.tim.one.billing.command.FacturaDeleteCommand
import com.tim.one.billing.command.FacturaShowCommand
import com.tim.one.billing.services.CancelaServicio
import com.tim.one.billing.services.FacturaServicio
import com.tim.one.billing.services.TimbraServicio

/**
 * @author sohjiro
 * @author josdem
 * @understands A class who can create, show and timbrar an factura.
 *
 */

@Controller
class FacturacionController {

	@Autowired
	FacturaServicio facturaServicio
	@Autowired
	TimbraServicio timbraServicio
	@Autowired
	CancelaServicio cancelaServicio

	@RequestMapping(method = RequestMethod.POST, value="/save")
	def createFacturaAndGenerateFolio() {
		println "WITH FOLIO"
	}

	@RequestMapping(method = RequestMethod.POST, value="/create")
	def createFacturaWithoutGeneratingFolio(FacturaCreateCommand command, HttpServletResponse response) {
		def file = facturaServicio."genera${command.format}DeFactura"(command.datosDeFacturacion, command.emisor, command.receptor, command.conceptos)

		def fis = new FileInputStream(file)
		response.setContentType("application/${command.format}")
		response.setContentLength(((int) file.size()))
		response.setHeader("Content-Disposition","attachment filename=\"" + file.name +"\"")
		FileCopyUtils.copy(fis, response.getOutputStream())

		timbraServicio.timbra(file)
		
		null
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/delete")
	def deleteFactura(FacturaDeleteCommand command) {
		cancelaServicio.cancelaFactura(command.uuid)
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
