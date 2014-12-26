package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import views.core.soap.services.apps.Incidencia
import views.core.soap.services.apps.IncidenciaArray

import com.finkok.facturacion.stamp.StampSOAP
import com.tim.one.billing.services.CadenaOriginalServicio
import com.tim.one.billing.services.CancelaServicio
import com.tim.one.billing.services.CfdiServicio;
import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.services.TimbraServicio
import com.tim.one.billing.state.ApplicationState


/**
 * @author josdem
 * @understands Service who know how to cancelar factura
 *
 */

@Service
class CancelaServicioImpl implements CancelaServicio {
	
	@Autowired
	CfdiServicio cfdiServicio

	@Override
	public void cancelaFactura(String uuid) {
		cfdiServicio.cancela(uuid)
	}
	
}
