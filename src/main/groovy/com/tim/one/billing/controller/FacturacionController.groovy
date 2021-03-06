package com.tim.one.billing.controller

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.util.FileCopyUtils
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus

import com.google.gson.Gson
import com.tim.one.billing.command.FacturaCancelCommand
import com.tim.one.billing.command.FacturaCreateCommand
import com.tim.one.billing.command.FacturaShowCommand
import com.tim.one.billing.command.FacturaValidCommand
import com.tim.one.billing.response.CancelResponse
import com.tim.one.billing.services.CancelaServicio
import com.tim.one.billing.services.FacturaServicio
import com.tim.one.billing.services.GuardaServicio
import com.tim.one.billing.services.TimbraServicio
import com.tim.one.billing.services.ValidaServicio
import com.google.gson.JsonSyntaxException


/**
 * @author sohjiro
 * @author josdem
 * @understands A class who can create, show and timbrar an factura.
 *
 */

@Controller
@RequestMapping("/services/*")
class FacturacionController {

	@Autowired
	FacturaServicio facturaServicio
	@Autowired
	TimbraServicio timbraServicio
	@Autowired
	CancelaServicio cancelaServicio
	@Autowired
	ValidaServicio validaServicio
	@Autowired
	GuardaServicio guardaServicio
	
	Log log = LogFactory.getLog(getClass())
	
	@RequestMapping(method = RequestMethod.POST, value="/create")
	def createFacturaWithoutGeneratingFolio(@RequestBody String json, HttpServletResponse response) {
		log.info("GENERATING factura")
		log.info("json: " + json);
		
		try{
			FacturaCreateCommand command = new Gson().fromJson(json, FacturaCreateCommand.class)
		  log.info("command: " + command.dump())
					
			def file = facturaServicio.generaXmlDeFactura(command.datosDeFacturacion, command.emisor, command.receptor, command.conceptos, command.impuestos, command.totales)
					
			if(command.getTimbra()){
			  def acuse = timbraServicio.timbra(file)
				file = guardaServicio.save(acuse)
			}
			
			def fis = new FileInputStream(file)
			response.setContentType("application/xml")
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(((int) file.size()))
			response.setHeader("Content-Disposition","attachment filename=\"" + file.name +"\"")
			
			FileCopyUtils.copy(fis, response.getOutputStream())
		}catch (JsonSyntaxException jse){
    	log.warn(jse.getMessage())
      return new ResponseEntity<String>(jse.getMessage(), HttpStatus.BAD_REQUEST)
    }
		null
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/cancel")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	def cancelFactura(@RequestBody String json) {
		FacturaCancelCommand command = new Gson().fromJson(json, FacturaCancelCommand.class)
		log.info("CANCELING factura")
		log.info("command: " + command.dump())
		try{
			cancelaServicio.cancelaFactura(command.uuid, command.rfcContribuyente)
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		} catch (RuntimeException re){
			return new ResponseEntity<String>(re.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/validate")
	def validaFactura(@RequestBody String json) {
		FacturaValidCommand command = new Gson().fromJson(json, FacturaValidCommand.class)
		log.info("VALIDATING factura")
		log.info("command: " + command.dump())
		
		try{
		  def response = validaServicio.valida(command.xmlName)
		  log.info("response: " + response)
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} catch (RuntimeException re){
			return new ResponseEntity<String>(re.getMessage(), HttpStatus.BAD_REQUEST);
	  }
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
