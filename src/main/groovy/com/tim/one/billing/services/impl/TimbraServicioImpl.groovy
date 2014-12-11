package com.tim.one.billing.services.impl

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

	def stampSOAP = new StampSOAP()
	def application = stampSOAP.getApplication()

	@Override
	void timbra(File file) {
		byte[] factura = file.getBytes()
		def acuse = application.stamp(factura, "cepdi@cepdi.mx", "@C3pd1#,,")

		if (acuse.getXml() != null) {
			println acuse.getXml().getValue()
		}
		if (acuse.getCodEstatus() != null) {
			println "Cod Status: " + acuse.getCodEstatus().getValue()
		}
		if (acuse.getUUID() != null) {
			println "UUID: " + acuse.getUUID().getValue()
		}
		if (acuse.getIncidencias() != null) {
			IncidenciaArray array = acuse.getIncidencias().getValue()
			for (Incidencia incidencia : array.getIncidencia()) {
				if (incidencia.getCodigoError() != null) {
					println "Codigo Error: " + incidencia.getCodigoError().getValue()
				}
				if (incidencia.getMensajeIncidencia() != null) {
					println "Mensaje de incidencia: " + incidencia.getMensajeIncidencia().getValue()
				}
				if (incidencia.getRfcEmisor() != null) {
					println "RFC Emisor: " + incidencia.getRfcEmisor().getValue()
				}
				if (incidencia.getWorkProcessId() != null) {
					println "Work Process Id: " + incidencia.getWorkProcessId().getValue()
				}
			}
		}
	}
}
