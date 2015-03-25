package com.tim.one.billing.collaborator

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component;

import com.tim.one.billing.state.ApplicationState

/**
 *
 * @author AKMH 
 * anakaren.monroyh@gmail.com
 * 
 * Instalar openssl y configurar las variables de entorno / ambiente segun el sistema operativo
 * Cambiar rutas 
 * 
 */

@Component
class OpensslCollaborator {

	private static String claveCsd
	private static String key
	private static String cer
	private static String rutaDestino
	private static String claveFinkok
	
	@Autowired
	Properties properties
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		key = properties.getProperty(ApplicationState.KEY_PATH)
		cer = properties.getProperty(ApplicationState.KEY_CERT)
		rutaDestino = properties.getProperty(ApplicationState.FACTURA_HOME)
		claveCsd = properties.getProperty(ApplicationState.KEY_PASSWORD)
		claveFinkok = properties.getProperty(ApplicationState.FINKOK_PASSWORD)
	}

	public OpensslCollaborator() {
		super()
	}

	public static String getRutaDestino() {
		return rutaDestino
	}

	public static String leeArchivo(String ruta) {
		try {
			FileInputStream fstream = new FileInputStream(ruta)
			DataInputStream entrada = new DataInputStream(fstream)
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada))
			String strLinea
			StringBuffer sB = new StringBuffer()
			while ((strLinea = buffer.readLine()) != null) {
				sB.append(strLinea).append("\n")
			}
			entrada.close()
			return sB.toString()
		} catch (Exception e) {
			System.err.println("Ocurrio un error: " + e.getMessage())
		}
		return null
	}

}
