package com.tim.one.billing.services.impl

import java.security.PrivateKey
import java.security.Signature

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.apache.commons.ssl.PKCS8Key
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import sun.misc.BASE64Encoder

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
	
	String keyPemPath
	String password

	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		keyPemPath = properties.getProperty(ApplicationState.KEY_PEM_PATH)
		password = properties.getProperty(ApplicationState.KEY_PEM_PASSWORD)
	}
	
	@Override
	String generaSello(String cadenaOriginal) {
		File file = new File(keyPemPath);
		byte[] clavePrivada = file.getBytes()
		PKCS8Key pkcs8 = new PKCS8Key(clavePrivada, password.toCharArray())
		PrivateKey pk = pkcs8.getPrivateKey()
		Signature firma = Signature.getInstance("MD5withRSA")
		firma.initSign(pk)
		
		String selloDigital = null
		try {
			firma.update(cadenaOriginal.getBytes("UTF-8"))
			BASE64Encoder b64 = new BASE64Encoder()
			selloDigital = b64.encode(firma.sign())
		} catch (UnsupportedEncodingException uee) {
			log.warn(uee,uee)
		}
		
		log.info("sello:" + selloDigital)
		return selloDigital
	}

	@Override
	public File sella(File file) {
		def cadenaOriginal = cadenaOriginalServicio.generaCadenaOriginal(file)
		def sello = selloServicio.generaSello(cadenaOriginal)
		String content = FileUtils.readFileToString(file)
		FileUtils.writeStringToFile(file, content.replaceAll("sello=", "sello=" + sello))
		return file
	}
	
}
