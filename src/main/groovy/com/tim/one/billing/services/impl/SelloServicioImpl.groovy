package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.state.ApplicationState

/**
 * @author josdem
 * @understands Service who know how to generate sello from factura
 *
 */

@Service
class SelloServicioImpl implements SelloServicio {
	
	@Autowired
	Properties properties
	
	String cadenaOriginalPath
	
	@PostConstruct
	public void initialize(){
		cadenaOriginalPath = properties.getProperty(ApplicationState.CADENA_ORIGINAL_PATH)
	}
	
	@Override
	String generaSello(File file){
		StreamSource sourceXSL = new StreamSource(new File(cadenaOriginalPath))
		StreamSource sourceXML = new StreamSource(file)

		TransformerFactory tFactory = TransformerFactory.newInstance()
		Transformer transformer = tFactory.newTransformer(sourceXSL)

		OutputStream output = new ByteArrayOutputStream()
		transformer.transform(sourceXML, new StreamResult(output))
		return new String(((ByteArrayOutputStream) output).toByteArray())
	}
	
}
