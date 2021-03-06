package com.tim.one.billing.state

interface ApplicationState {
	
	static final String FINKOK_USERNAME = "finkok.username"
	static final String FINKOK_PASSWORD = "finkok.password"
	static final String FACTURA_TEMPLATE_LOGO = "factura.template.logo"
	static final String FACTURA_TEMPLATE_PDF = "factura.template.pdf"
	static final String FACTURA_TEMPLATE_XML = "factura.template.xml"
	static final String CADENA_ORIGINAL_PATH = "cadena.original.path"
	static final String FACTURA_HOME = "factura.home"
	static final String FACTURA_FILES_HOME = "factura.file.path"
	static final String KEY_PATH = "key.path"
	static final String KEY_CERT = "key.cert"
	static final String KEY_PASSWORD = "key.password"
	static final String WHITE_LIST = "white.list"
	static final String FILE_SEPARATOR = System.getProperty("file.separator")
	
	/**
	 * Oauth2
	 */
	
	static final String INTEGRA_USERNAME = "integra.username";
	static final String INTEGRA_PASSWORD = "integra.password";
	static final String KPMG_USERNAME = "kpmg.username";
	static final String KPMG_PASSWORD = "kpmg.password";
	
}
