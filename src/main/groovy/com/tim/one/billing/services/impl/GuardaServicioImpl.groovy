package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import views.core.soap.services.apps.AcuseRecepcionCFDI

import com.tim.one.billing.services.GuardaServicio
import com.tim.one.billing.state.ApplicationState


/**
 * @author josdem
 * @understands Service who know how to store factura
 *
 */

@Service
class GuardaServicioImpl implements GuardaServicio {
	
	@Autowired
	Properties properties
	
	String facturaPath
	
	@PostConstruct
	public void initialize(){
		facturaPath = properties.getProperty(ApplicationState.FACTURA_FILES_HOME)
	}
	
	@Override
	File save(AcuseRecepcionCFDI acuse) {
		def uuid = acuse.uuid.value
		def facturaName = uuid?.replaceAll("-","")
		def factura = new File(facturaPath + ApplicationState.FILE_SEPARATOR + facturaName + ".xml")
		FileUtils.writeStringToFile(factura, acuse.getXml().getValue())
		return factura
	}
	
}




