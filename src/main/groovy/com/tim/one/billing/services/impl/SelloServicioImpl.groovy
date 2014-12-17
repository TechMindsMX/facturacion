package com.tim.one.billing.services.impl

import java.security.PrivateKey
import java.security.Signature

import org.apache.commons.io.IOUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.apache.commons.ssl.PKCS8Key
import org.springframework.stereotype.Service

import sun.misc.BASE64Encoder

import com.tim.one.billing.services.SelloServicio

/**
 * @author josdem
 * @understands Service who know how to generate sello from factura
 *
 */

@Service
class SelloServicioImpl implements SelloServicio {
	
	Log log = LogFactory.getLog(getClass())
	
	@Override
	String generaSello(InputStream archivoClavePrivada, String password, String cadenaOriginal) {
		byte[] clavePrivada = IOUtils.toByteArray(archivoClavePrivada)
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
		
		log.info(selloDigital)
		return selloDigital
	}
	
}
