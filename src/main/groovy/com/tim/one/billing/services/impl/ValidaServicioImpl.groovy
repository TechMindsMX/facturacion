package com.tim.one.billing.services.impl

import java.io.File
import java.util.Properties

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.collaborator.ValidaCollaborator
import com.tim.one.billing.services.CadenaOriginalServicio
import com.tim.one.billing.services.CancelaServicio
import com.tim.one.billing.services.CfdiServicio
import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.services.TimbraServicio
import com.tim.one.billing.services.ValidaServicio
import com.tim.one.billing.state.ApplicationState


/**
 * @author josdem
 * @understands Service who know how to validar factura
 *
 */

@Service
class ValidaServicioImpl implements ValidaServicio {
	
	@Autowired
	ValidaCollaborator validaCollaborator
	
	@Autowired
	Properties properties
	
	String username
	String password
	String xmlPath
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		username = properties.getProperty(ApplicationState.FINKOK_USERNAME)
		password = properties.getProperty(ApplicationState.FINKOK_PASSWORD)
		xmlPath = properties.getProperty(ApplicationState.FACTURA_HOME)
	}

	@Override
	String valida(String xmlName) {
		def xmlNameFormatted = xmlName.replaceAll("-","")
		def path = xmlPath + ApplicationState.FILE_SEPARATOR + xmlNameFormatted + ".xml"
		def response = validaCollaborator.ValidaComprobante(username, password, path)
		return response
	}
	
}
