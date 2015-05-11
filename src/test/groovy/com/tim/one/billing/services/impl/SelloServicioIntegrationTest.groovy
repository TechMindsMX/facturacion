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
		def startSello = "yyF1LiQYCb9r1fcf6T1Afs1VdTehEik8oqbEJyPP966ZU4"  
		def endSello = "JcOmRVklNPPpKfGXfaNodnk034II="
		def cadenaOriginal = "||3.2|2014-12-17T15:42:33|ingreso|PAGO EN UNA SOLA EXHIBICION|100.00|116.0000|TRANSFERENCIA ELECTRONICA|D.F.,MEXICO|DESCONOCIDO|AAD990814BP7|EMPRESA prod|REFORMA|BENITO JUAREZ|DISTRITO FEDERAL|MEXICO|34343|PERSONA FISCA|EWOK8102049D4|PUBLICO EN GENERAL|INSURGENTES|MEXICO|1|No aplica|Cafe americano,PZA|100.00|100.00|IVA|16.00|16.0000||"
		
		def sello = selloServicio.generaSello(cadenaOriginal)
		
		assertTrue(sello.startsWith(startSello))
		assertTrue(sello.endsWith(endSello))
	}
	
}
