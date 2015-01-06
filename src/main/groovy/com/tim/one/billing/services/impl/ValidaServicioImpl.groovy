package com.tim.one.billing.services.impl

import java.io.File;

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.collaborator.ValidaCollaborator;
import com.tim.one.billing.services.CadenaOriginalServicio
import com.tim.one.billing.services.CancelaServicio
import com.tim.one.billing.services.CfdiServicio;
import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.services.TimbraServicio
import com.tim.one.billing.services.ValidaServicio;
import com.tim.one.billing.state.ApplicationState


/**
 * @author josdem
 * @understands Service who know how to validar factura
 *
 */

@Service
class ValidaServicioImpl implements ValidaServicio {
	
	@Autowired
	ValidaCollaborator validaCollaborator

	@Override
	public void valida(File file) {
		validaCollaborator.valida()
	}
	
}
