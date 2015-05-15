package com.tim.one.billing.collaborator

import javax.annotation.PostConstruct
import javax.xml.bind.JAXBElement

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service

import com.tim.one.billing.state.ApplicationState

import finkok.cancel.prod.Application
import finkok.cancel.prod.CancelSOAP
import finkok.cancel.prod.CancelaCFDResult
import finkok.cancel.prod.Folio
import finkok.cancel.prod.FolioArray
import finkok.cancel.prod.ObjectFactory
import finkok.cancel.prod.StringArray
import finkok.cancel.prod.UUIDS

/**
 * @author josdem
 * @understands Service who know how to cancel a factura
 *
 */

@Component
class CancelaCollaborator {
	
	private static String usernameFinkok
	private static String passwordFinkok
	
	CancelSOAP cancelSOAP = new CancelSOAP()
	Application application = cancelSOAP.getApplication()
	
	@Autowired
	Properties properties
	
	Log log = LogFactory.getLog(getClass())
	
	@PostConstruct
	public void initialize(){
		usernameFinkok = properties.getProperty(ApplicationState.FINKOK_USERNAME)
		passwordFinkok = properties.getProperty(ApplicationState.FINKOK_PASSWORD)
	}

	CancelaCFDResult cancela(String uuid, String rfcContribuyente){
		ObjectFactory ob = new ObjectFactory()
		
		UUIDS uuids = ob.createUUIDS();
    StringArray sA = ob.createStringArray();
    sA.getString().add(uuid);

    JAXBElement<StringArray> array = ob.createUUIDSUuids(sA);
    uuids.setUuids(array);

		byte[] cer = null
		byte[] key = null
		try {
			cer = OpensslCollaborator.leeArchivo(OpensslCollaborator.getRutaDestino() + "key.pem").getBytes("UTF-8")
			key = OpensslCollaborator.leeArchivo(OpensslCollaborator.getRutaDestino() + "key.enc").getBytes("UTF-8")
		} catch (UnsupportedEncodingException e) {
		  log.error(e,e)
		}

		CancelaCFDResult acuse = application.cancel(uuids, usernameFinkok, passwordFinkok, rfcContribuyente, cer, key, true)

		if (acuse.getAcuse() != null) {
			log.info(acuse.getAcuse().getValue())
		}
		if (acuse.getCodEstatus() != null) {
			log.info("Codigo Estatus: " + acuse.getCodEstatus().getValue())
		}
		if (acuse.getFolios() != null) {
			FolioArray folioArray = acuse.getFolios().getValue()
			for (Folio f : folioArray.getFolio()) {
				if (f.getUUID() != null) {
					log.info("UUID: " + f.getUUID().getValue())
				}
				if (f.getEstatusUUID() != null) {
					log.info("Estatus UUID: " + f.getEstatusUUID().getValue())
				}
			}
		}
		
		return acuse
	}
}
