package com.tim.one.billing.services.impl

import java.security.PrivateKey
import java.security.Signature

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.apache.commons.ssl.PKCS8Key
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.state.ApplicationState

import java.nio.file.Files
import org.apache.commons.ssl.Base64

/**
 * @author josdem
 * @understands Service who know how to generate sello from factura
 *
 */

@Service
class SelloServicioImpl implements SelloServicio {
	
	@Autowired
	Properties properties
	
	String keyPath
	String password

	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		keyPath = properties.getProperty(ApplicationState.KEY_PATH)
		password = properties.getProperty(ApplicationState.KEY_PASSWORD)
	}
	
	@Override
	String generaSello(String cadenaOriginal) {
		log.info("keyPath: " + keyPath);
		log.info("password: " + password);
		
    File KeyPath = new File(keyPath);
    byte[] key = Files.readAllBytes(KeyPath.toPath());

    PKCS8Key pkcs8 = new PKCS8Key(key, password.toCharArray());
    PrivateKey pk = pkcs8.getPrivateKey();

    Signature firma = Signature.getInstance("SHA1withRSA");
    firma.initSign(pk);
    firma.update(cadenaOriginal.getBytes("UTF-8"));
    def selloDigital = new String (Base64.encodeBase64(firma.sign()),"UTF-8");
		
		log.info("sello:" + selloDigital)
		return selloDigital
	}
	
}
