package com.tim.one.billing.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.collaborator.CancelaCollaborator
import com.tim.one.billing.response.CancelResponse
import com.tim.one.billing.services.CancelaServicio


/**
 * @author josdem
 * @understands Service who know how to cancelar factura
 *
 */

@Service
class CancelaServicioImpl implements CancelaServicio {
	
	@Autowired
	CancelaCollaborator cancelaCollaborator

	@Override
	public CancelResponse cancelaFactura(String uuid, String rfcContribuyente) {
		def acuse = cancelaCollaborator.cancela(uuid, rfcContribuyente)
		def response = new CancelResponse()
		if (acuse.getCodEstatus() != null) {
			response.message = acuse.getCodEstatus() 
		} else {
			response.succeed = true
			response.message = "201"
		}
		return response
	}
	
}
