package com.tim.one.billing.collaborator

import javax.xml.bind.JAXBElement

import org.springframework.stereotype.Component

import views.core.soap.services.apps.CancelaCFDResult
import views.core.soap.services.apps.Folio
import views.core.soap.services.apps.FolioArray
import views.core.soap.services.apps.UUIDS

import com.finkok.facturacion.cancel.Application
import com.finkok.facturacion.cancel.CancelSOAP
import com.finkok.facturacion.cancel.ObjectFactory
import com.finkok.facturacion.cancellation.StringArray

/**
 * @author josdem
 * @understands Service who know how to cancel a factura
 *
 */

@Component
class CancelaCollaborator {
	CancelSOAP cancelSOAP = new CancelSOAP()
	Application application = cancelSOAP.getApplication()

	void cancela(String uuid){
		ObjectFactory ob = new ObjectFactory();

		UUIDS uuids = ob.createUUIDS();
		StringArray sA = ob.createStringArray();
		sA.getString().add("UUID");

		JAXBElement<StringArray> array = ob.createUUIDSUuids(sA);
		uuids.setUuids(array);

		Openssl.creaCerPem();
		Openssl.creaKeyPem();
		Openssl.creaKeyEncriptado();

		byte[] cer = null;
		byte[] key = null;
		try {
			cer = Openssl.leeArchivo(Openssl.getRutaDestino() + "cer.pem").getBytes("UTF-8");
			key = Openssl.leeArchivo(Openssl.getRutaDestino() + "key.enc").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

		Openssl.deleteFiles();

		CancelaCFDResult acuse = application.cancel(uuids, "usernameFinkok", "passwordFinkok", "RFCContribuyente", cer, key, true);

		if (acuse.getAcuse() != null) {
			System.out.println(acuse.getAcuse().getValue());
		}
		if (acuse.getCodEstatus() != null) {
			System.out.println("Codigo Estatus: " + acuse.getCodEstatus().getValue());
		}
		if (acuse.getFolios() != null) {
			FolioArray folioArray = acuse.getFolios().getValue();
			for (Folio f : folioArray.getFolio()) {
				if (f.getUUID() != null) {
					System.out.println("UUID: " + f.getUUID().getValue());
				}
				if (f.getEstatusUUID() != null) {
					System.out.println("Estatus UUID: " + f.getEstatusUUID().getValue());
				}
			}
		}
	}
}
