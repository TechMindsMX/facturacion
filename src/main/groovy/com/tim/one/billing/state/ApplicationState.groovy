package com.tim.one.billing.state

interface ApplicationState {
	
	static final String FINKOK_USERNAME = "finkok.username"
	static final String FINKOK_PASSWORD = "finkok.password"
	static final String FACTURA_TEMPLATE_LOGO = "factura.template.logo"
	static final String FACTURA_TEMPLATE_PDF = "factura.template.pdf"
	static final String FACTURA_TEMPLATE_XML = "factura.template.xml"
	static final String CADENA_ORIGINAL_PATH = "cadena.original.path"
	static final String FACTURA_HOME = "factura.file.path"
	static final String KEY_PATH = "key.path"
	static final String KEY_CERT = "key.cert"
	static final String KEY_PASSWORD = "key.password"
	static final String FILE_SEPARATOR = System.getProperty("file.separator")
	
}
