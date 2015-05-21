package com.tim.one.billing.services.impl;

import static org.mockito.Mockito.when;
import groovy.lang.GroovyRuntimeException;

import javax.xml.bind.JAXBElement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tim.one.billing.bean.Acuse;
import com.tim.one.billing.collaborator.CancelaCollaborator;

public class TestCancelaServicio {
	
	@InjectMocks
	private CancelaServicioImpl servicio = new CancelaServicioImpl();
	
	@Mock
	private CancelaCollaborator cancelaCollaborator;
	@Mock
	private JAXBElement<String> value;

	private Acuse acuse = new Acuse();
	
	private String uuid = "uuid";
	private String rfc = "rfc";


	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCancelFactura() throws Exception {
		when(cancelaCollaborator.cancela(uuid, rfc)).thenReturn(acuse);
		acuse.setSuccess(true);
		servicio.cancelaFactura(uuid, rfc);
	}
	
	@Test(expected=GroovyRuntimeException.class)
	public void shouldNotCancelFactura() throws Exception {
		when(cancelaCollaborator.cancela(uuid, rfc)).thenReturn(acuse);
		acuse.setSuccess(false);
		servicio.cancelaFactura(uuid, rfc);
	}

}
