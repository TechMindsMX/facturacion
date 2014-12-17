package com.tim.one.billing.services.impl

import static org.junit.Assert.assertTrue

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.tim.one.billing.services.SelloServicio


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["classpath:/services-appctx.xml", "classpath:/properties-appctx.xml"])
class SelloServicioIntegrationTest {

	@Autowired
	SelloServicio selloServicio

	@Test
	public void shouldCreateAValidSello() {
		def startSello = "wTQmFk0EsQ4zCs6z8W55VKDtGZcdP"  
		def endSello = "W0uLv8XhcI2QQIUNzu0yJQUrZ8="
		def cadenaOriginal = "||3.2|2014-12-17T15:42:33|ingreso|PAGO EN UNA SOLA EXHIBICION|100.00|116.0000|TRANSFERENCIA ELECTRONICA|D.F.,MEXICO|DESCONOCIDO|AAD990814BP7|EMPRESA DEMO|REFORMA|BENITO JUAREZ|DISTRITO FEDERAL|MEXICO|34343|PERSONA FISCA|EWOK8102049D4|PUBLICO EN GENERAL|INSURGENTES|MEXICO|1|No aplica|Cafe americano,PZA|100.00|100.00|IVA|16.00|16.0000||"
		
		def sello = selloServicio.generaSello(cadenaOriginal)
		
		assertTrue(sello.startsWith(startSello))
		assertTrue(sello.endsWith(endSello))
	}
	
}
