package com.tim.one.billing.services.impl

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

import views.core.soap.services.apps.Incidencia
import views.core.soap.services.apps.IncidenciaArray

import com.finkok.facturacion.stamp.StampSOAP
import com.tim.one.billing.services.TimbraServicio

/**
 * @author josdem
 * @understands Service who know how to timbrar factura
 *
 */

@Service
class TimbraServicioImpl implements TimbraServicio {
	
	@Autowired
	Properties properties

	def stampSOAP = new StampSOAP()
	def application = stampSOAP.getApplication()
	
	String username
	String password
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		username = properties.getProperty("finkok.username");
		password = properties.getProperty("finkok.password");
	}

	@Override
	void timbra(File file) {
		byte[] factura = file.getBytes()
		def acuse = application.stamp(factura, username, password)

		if (acuse.getXml() != null) {
			log.info(acuse.getXml().getValue())
		}
		if (acuse.getCodEstatus() != null) {
			log.info("Cod Status: " + acuse.getCodEstatus().getValue())
		}
		if (acuse.getUUID() != null) {
			log.info("UUID: " + acuse.getUUID().getValue())
		}
		if (acuse.getIncidencias() != null) {
			IncidenciaArray array = acuse.getIncidencias().getValue()
			for (Incidencia incidencia : array.getIncidencia()) {
				if (incidencia.getCodigoError() != null) {
					log.info("Codigo Error: " + incidencia.getCodigoError().getValue())
				}
				if (incidencia.getMensajeIncidencia() != null) {
					log.info("Mensaje de incidencia: " + incidencia.getMensajeIncidencia().getValue())
				}
				if (incidencia.getRfcEmisor() != null) {
					log.info("RFC Emisor: " + incidencia.getRfcEmisor().getValue())
				}
				if (incidencia.getWorkProcessId() != null) {
					log.info("Work Process Id: " + incidencia.getWorkProcessId().getValue())
				}
			}
		}
	}
}
