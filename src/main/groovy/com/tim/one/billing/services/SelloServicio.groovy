package com.tim.one.billing.services

import java.io.File;
import java.io.InputStream;

interface SelloServicio {
	
	String generaSello(String cadenaOriginal)
	File sella(File file)
	
}
