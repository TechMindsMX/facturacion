package com.tim.one.billing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim.one.billing.bean.Acuse;
import com.tim.one.billing.collaborator.CancelaCollaborator;
import com.tim.one.billing.exception.FacturaException;
import com.tim.one.billing.services.CancelaServicio;

/**
 * @author josdem
 * @understands Service who know how to cancelar factura
 *
 */

@Service
public class CancelaServicioImpl implements CancelaServicio {

	@Autowired
	private CancelaCollaborator cancelaCollaborator;

	@Override
	public void cancelaFactura(String uuid, String rfcContribuyente) {
		Acuse acuse = cancelaCollaborator.cancela(uuid, rfcContribuyente);
		if (!acuse.getSuccess()) {
			throw new FacturaException(acuse.getMessage());
		}
	}
}
