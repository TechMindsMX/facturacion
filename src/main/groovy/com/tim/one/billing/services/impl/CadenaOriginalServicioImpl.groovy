package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.CadenaOriginalServicio
import com.tim.one.billing.state.ApplicationState

/**
 * @author josdem
 * @understands Service who know how to generate cadenaOriginal string from factura
 *
 */

@Service
class CadenaOriginalServicioImpl implements CadenaOriginalServicio {
	
	@Autowired
	Properties properties
	
	String cadenaOriginalPath
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		cadenaOriginalPath = properties.getProperty(ApplicationState.CADENA_ORIGINAL_PATH)
	}
	
	@Override
	String generaCadenaOriginal(File file){
		StreamSource sourceXSL = new StreamSource(new File(cadenaOriginalPath))
		StreamSource sourceXML = new StreamSource(file)

		TransformerFactory tFactory = TransformerFactory.newInstance()
		Transformer transformer = tFactory.newTransformer(sourceXSL)

		OutputStream output = new ByteArrayOutputStream()
		transformer.transform(sourceXML, new StreamResult(output))
		def cadenaOriginal = new String(((ByteArrayOutputStream) output).toByteArray())
		log.info("cadenaOriginal: " + cadenaOriginal)
		return cadenaOriginal
	}
	
}
