package com.tim.one.billing.services.impl

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.tim.one.billing.services.SelloServicio
import com.tim.one.billing.state.ApplicationState

/**
 * @author josdem
 * @understands Service who know how to generate sello from factura
 *
 */

@Service
class SelloServicioImpl implements SelloServicio {
	
	@Override
	String generaSello(String cadenaOriginal){
		return null
	}
	
}
