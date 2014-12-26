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

	public static void creaCerPem() {
		Runtime runtime = Runtime.getRuntime()
		Process exec = null
		try {
			exec =
					runtime.exec("openssl x509 -inform DER -outform PEM -in " + cer + " -pubkey -out " + rutaDestino
					+ "cer.pem")
			exec.waitFor()
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
	}

	public static void creaKeyPem() {
		Runtime runtime = Runtime.getRuntime()
		Process exec = null
		try {
			exec =
					runtime.exec("openssl pkcs8 -inform DER -in " + key + " -passin pass:" + claveCsd + " -out "
					+ rutaDestino + "key.pem")
			exec.waitFor()
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
	}

	public static void creaKeyEncriptado() {
		Runtime runtime = Runtime.getRuntime()
		Process exec = null
		try {
			exec =
					runtime.exec("openssl rsa -in " + rutaDestino + "key.pem -des3 -out " + rutaDestino
					+ "key.enc -passout pass:" + claveFinkok)
			exec.waitFor()
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
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

	public static void deleteFiles() {
		File cerPem = new File(rutaDestino + "cer.pem")
		cerPem.delete()
		File keyPem = new File(rutaDestino + "key.pem")
		keyPem.delete()
		File keyEnc = new File(rutaDestino + "key.enc")
		keyEnc.delete()
	}
}
