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
		def startSello = "In+tymRk3C9ML+EwBTM8FJ1rP3qVlou8kLkdCX11OzVVmBU1w/ACQQBdJRM+ZXMeZqskZ9YP6fJZ"  
		def endSello = "UlofATDRj9d+f7TL3iQ="
		def cadenaOriginal = "||3.2|2014-12-15T15:30:59|ingreso|Pago en una sola exhibición|5.0000|MXP|5.8000|no aplica|Toluca, Edo. de México|AAD990814BP7|ACCEM SERVICIOS EMPRESARIALES SC|Calle PRUEBA|1525|121|Colonia de PRUEBA|Ciudad de PRUEBA|ref|Delegacion dePRUEBA|Michoacan|Mexico|25364|Regimen de Actividades Agricolas, Ganaderas, Silvicolas y Pesqueras|AGU900725BL5|EL ARTE GUADALUPANO SADE CV|FRAY JUAN DE ZUMARRAGA SECC1|21|LOC22 Y 25 COLVILLA DE GUAD|GUSTAVO AM|avenida|GUSTAVO AM|Distrito Federal|MEXICO|07050|1.000000|Pza.|Concepto Prueba|5.0000|5.0000|IVA|16.00|0.8000|0.8000||"
		
		def sello = selloServicio.generaSello(cadenaOriginal)
		
		assertTrue(sello.startsWith(startSello))
		assertTrue(sello.endsWith(endSello))
	}
	
}
