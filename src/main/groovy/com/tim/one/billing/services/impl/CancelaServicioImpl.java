package com.tim.one.billing.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import views.core.soap.services.apps.CancelaCFDResult;

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
		CancelaCFDResult acuse = cancelaCollaborator.cancela(uuid, rfcContribuyente);
		if (acuse.getCodEstatus() != null) {
			throw new FacturaException(acuse.getCodEstatus().getValue());
		}
	}
}
