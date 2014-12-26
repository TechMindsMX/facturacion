package com.tim.one.billing.services

import java.io.File
import java.io.InputStream

interface CfdiServicio {
	
	File sella(File file)
	void cancela(String uuid)
	
}
