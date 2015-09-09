package com.tim.one.billing.collaborator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.io.IOUtils;
import org.apache.commons.ssl.Base64;

public class CertificadoCalculator {
	
	public String getNumeroDeCertificado() throws CertificateException, FileNotFoundException {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		
		File file = new File("integradora.cer");
		
		InputStream in = new FileInputStream(file);
		X509Certificate cert = (X509Certificate) certFactory.generateCertificate(in);
		byte bytenumber[] = cert.getSerialNumber().toByteArray();
		return new String(bytenumber);
	}
	
	public String getCertificadoBase64() throws IOException{
		File file = new File("integradora.cer");		
		InputStream in = new FileInputStream(file);
		return new String(Base64.encodeBase64(IOUtils.toByteArray(in)));
	}

}
