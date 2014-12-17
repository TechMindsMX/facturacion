package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.CadenaOriginalServicio;
import com.tim.one.billing.services.CfdiServicio
import com.tim.one.billing.services.SelloServicio;
import com.tim.one.billing.state.ApplicationState

/**
 * @author josdem
 * @understands Service who know how to sellar factura
 *
 */

@Service
class CfdiServicioImpl implements CfdiServicio {
	
	@Autowired
	CadenaOriginalServicio cadenaOriginalServicio
	@Autowired
	SelloServicio selloServicio
	
	@Override
	public File sella(File file) {
		def cadenaOriginal = cadenaOriginalServicio.generaCadenaOriginal(file)
		def sello = selloServicio.generaSello(cadenaOriginal)
		String content = FileUtils.readFileToString(file)
		FileUtils.writeStringToFile(file, content.replaceAll("sello=", "sello=" + sello))
		return file
	}
	
}
