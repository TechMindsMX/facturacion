package com.tim.one.billing.services

import java.io.InputStream;

interface SelloServicio {
	
	String generaSello(InputStream archivoClavePrivada, String password, String cadenaOriginal)
	
}
