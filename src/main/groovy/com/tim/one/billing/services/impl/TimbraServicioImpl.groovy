package com.tim.one.billing.services.impl

import org.apache.commons.io.IOUtils

import views.core.soap.services.apps.Incidencia
import views.core.soap.services.apps.IncidenciaArray

import com.finkok.facturacion.stamp.StampSOAP
import com.tim.one.billing.services.TimbraServicio

class TimbraServicioImpl implements TimbraServicio {

	def stampSOAP = new StampSOAP()
	def application = stampSOAP.getApplication()

	@Override
	void timbra(InputStream inputStream) {
		byte[] factura = IOUtils.toByteArray(inputStream)
		def acuse = application.stamp(factura, "cepdi@cepdi.mx", "@C3pd1#,,")

		if (acuse.getXml() != null) {
			System.out.println(acuse.getXml().getValue())
		}
		if (acuse.getCodEstatus() != null) {
			System.out.println("Cod Status: " + acuse.getCodEstatus().getValue())
		}
		if (acuse.getUUID() != null) {
			System.out.println("UUID: " + acuse.getUUID().getValue())
		}
		if (acuse.getIncidencias() != null) {
			IncidenciaArray array = acuse.getIncidencias().getValue()
			for (Incidencia incidencia : array.getIncidencia()) {
				if (incidencia.getCodigoError() != null) {
					System.out.println("Codigo Error: " + incidencia.getCodigoError().getValue())
				}
				if (incidencia.getMensajeIncidencia() != null) {
					System.out.println("Mensaje de incidencia: " + incidencia.getMensajeIncidencia().getValue())
				}
				if (incidencia.getRfcEmisor() != null) {
					System.out.println("RFC Emisor: " + incidencia.getRfcEmisor().getValue())
				}
				if (incidencia.getWorkProcessId() != null) {
					System.out.println("Work Process Id: " + incidencia.getWorkProcessId().getValue())
				}
			}
		}
	}
}
