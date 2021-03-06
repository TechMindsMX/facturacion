package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.CfdiServicio
import com.tim.one.billing.services.TimbraServicio
import com.tim.one.billing.state.ApplicationState
import finkok.stamp.demo.AcuseRecepcionCFDI
import finkok.stamp.demo.Application
import finkok.stamp.demo.Incidencia
import finkok.stamp.demo.IncidenciaArray
import finkok.stamp.demo.StampSOAP


/**
 * @author josdem
 * @understands Service who know how to timbrar factura
 *
 */

@Service
class TimbraServicioImpl implements TimbraServicio {
	
	@Autowired
	private CfdiServicio cfdiServicio
	
	@Autowired
	Properties properties

	StampSOAP stampSOAP = new StampSOAP()
	Application application = stampSOAP.getApplication()
	
	String username
	String password
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		username = properties.getProperty(ApplicationState.FINKOK_USERNAME)
		password = properties.getProperty(ApplicationState.FINKOK_PASSWORD)
	}

	@Override
	AcuseRecepcionCFDI timbra(File file) {
		File fileSellado = cfdiServicio.sella(file)
		byte[] factura = fileSellado.getBytes()
		log.info "username: ${username}, password: ${password}"
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
		return acuse
	}
}
