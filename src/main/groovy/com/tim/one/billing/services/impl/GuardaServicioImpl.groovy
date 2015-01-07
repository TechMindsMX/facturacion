package com.tim.one.billing.services.impl

import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import stamp.AcuseRecepcionCFDI

import com.tim.one.billing.services.GuardaServicio


/**
 * @author josdem
 * @understands Service who know how to store factura
 *
 */

@Service
class GuardaServicioImpl implements GuardaServicio {
	
	@Override
	File save(AcuseRecepcionCFDI acuse) {
		def uuid = acuse.uuid.value
		def facturaName = uuid?.replaceAll("-","")
		def factura = new File(facturaName,".xml")
		FileUtils.writeStringToFile(factura, acuse.getXml().getValue())
		return factura
	}
	
}
