package com.tim.one.billing.collaborator

import javax.annotation.PostConstruct
import javax.xml.bind.JAXBElement

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service

import com.tim.one.billing.bean.Acuse
import com.tim.one.billing.state.ApplicationState

import finkok.cancel.demo.Application
import finkok.cancel.demo.CancelSOAP
import finkok.cancel.demo.CancelaCFDResult
import finkok.cancel.demo.Folio
import finkok.cancel.demo.FolioArray
import finkok.cancel.demo.ObjectFactory
import finkok.cancel.demo.StringArray
import finkok.cancel.demo.UUIDS

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

	Acuse cancela(String uuid, String rfcContribuyente){
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
		Acuse result = new Acuse()
		result.success = false

		if (acuse.getAcuse() != null) {
			log.info(acuse.getAcuse().getValue())
		}
		if (acuse.getCodEstatus() != null) {
			log.info("Codigo Estatus: " + acuse.getCodEstatus().getValue())
			result.message = "Codigo Estatus: " + acuse.getCodEstatus().getValue()
		}
		if (acuse.getFolios() != null) {
			FolioArray folioArray = acuse.getFolios().getValue()
			for (Folio f : folioArray.getFolio()) {
				if (f.getUUID() != null) {
					log.info("UUID: " + f.getUUID().getValue())
				}
				if (f.getEstatusUUID() != null) {
					log.info("Estatus UUID: " + f.getEstatusUUID().getValue())
					if(f.getEstatusUUID().getValue().trim().equals("201")){
						result.success = true
					}
					result.message = "Estatus UUID: " + f.getEstatusUUID().getValue()
				}
			}
		}
		
		return result
	}
}
