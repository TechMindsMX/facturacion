package com.tim.one.billing.collaborator

import javax.annotation.PostConstruct

import javax.xml.bind.JAXBElement

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.tim.one.billing.state.ApplicationState
import finkok.valida.demo.AcuseValidacion
import finkok.valida.demo.Application
import finkok.valida.demo.Observacion
import finkok.valida.demo.ObservacionArray
import finkok.valida.demo.ValidacionService

/**
 * @author VictorManuel finkok
 * @understands Service who know how to validar factura
 *
 */

@Component
class ValidaCollaborator {
	
	public String ValidaComprobante(String Usuario, String Password, String RutaXML) {
		String Respuesta = ""
		try {
			byte[] XML = GetXMLContent(RutaXML).getBytes("UTF-8")//Leer archivo XML y obtner bytes del mismo.
			Respuesta = valida(Usuario, Password, XML)
		} catch (Exception e) {
			e.printStackTrace()
		}
		return Respuesta
	}

	public String GetXMLContent(String Ruta) {
		try {
			FileInputStream fstream = new FileInputStream(Ruta)
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
			return ""
		}
	}

	private String valida(String Usuario, String Password, byte[] XML) {

		String Respuesta = ""
		String Observaciones = "11111111"//Esta es la cadena con la que se elige que validar, 1: Validar, 0: No validar
		/*Tomando en cuenta las siguientes 8 posiciones:
		 -Serie y Folio
		 -Sello XML
		 -Cadena Original
		 -Validacion XSD
		 -Leyenda Pagos
		 -Importes Impuestos Trasladados
		 -Importes Impuestos Retenidos
		 -Importes Subtotales
		 Por ejemplo 011 Validaria solamente Sello XMl y Cadena Original
		 */

		ValidacionService Soap = new ValidacionService()//Serivicio
		Application application = Soap.getApplication()//Preparar Peticion

		AcuseValidacion acuse = application.valida(XML, Observaciones, Usuario, Password)//Consumir Metodo de Validacion

		if (acuse.getIncidencia() != null) {

			Respuesta = "Código Incidencia: " + acuse.getIncidencia().getValue().getCodigo().getValue()

			if (acuse.getIncidencia().getValue().getMensaje() != null) {
				Respuesta += ":" + acuse.getIncidencia().getValue().getMensaje().getValue() + "\n\n"
			} else {
				Respuesta += "\n\n"
			}
		}
		if (acuse.getObservaciones() != null) {

			//Se pueden consultar los datos de la validacion en el objeto "Datos"
			Respuesta += "DATOS:\n"
			Respuesta += "RFC EMISOR:" + acuse.getDatos().getValue().getRfcemisor().getValue() + "\n"
			Respuesta += "EMISOR: " + acuse.getDatos().getValue().getNombreemisor().getValue() + "\n"
			Respuesta += "RFC RECEPTOR:" + acuse.getDatos().getValue().getRfcreceptor().getValue() + "\n"
			Respuesta += "RECEPTOR: " + acuse.getDatos().getValue().getNombrereceptor().getValue() + "\n"
			Respuesta += "UUID:" + acuse.getDatos().getValue().getUuid().getValue() + "\n"
			Respuesta += "TOTAL:" + acuse.getDatos().getValue().getTotal().getValue() + "\n"
			Respuesta += "EFECTO: " + acuse.getDatos().getValue().getEfecto().getValue() + "\n"
			Respuesta += "ESTADO: " + acuse.getDatos().getValue().getEstado().getValue() + "\n"
			Respuesta += "FECHA CERTIFICACION: " + acuse.getDatos().getValue().getFechacertificacion().getValue() + "\n"
			Respuesta += "FECHA EXPEDICION: " + acuse.getDatos().getValue().getFechaexpedicion().getValue() + "\n\n"
			Respuesta += "OBSERVACIONES:\n"

			//Las observaciones contienen mas datos sobre la validacion (Sello,Validacion XSD, etc).
			ObservacionArray observaciones = acuse.getObservaciones().getValue()

			for (Observacion obs : observaciones.getObservacion()) {
				Respuesta += obs.getElemento().getValue() + " : " + obs.getMensaje().getValue() + "\n"
			}

		}
		return Respuesta
	}
}
