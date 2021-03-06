package com.tim.one.billing.services.impl

import static org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.tim.one.billing.services.CadenaOriginalServicio

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["classpath:/services-appctx.xml", "classpath:/properties-appctx.xml"])
class CadenaOriginalServicioIntegrationTest {

	@Autowired
	CadenaOriginalServicio cadenaOriginalServicio

	@Test
	public void shouldCreateAValidSello() {
		def expectedCadenaOriginal = "||3.2|2014-12-15T15:30:59|ingreso|Pago en una sola exhibición|5.0000|MXP|5.8000|no aplica|Toluca, Edo. de México|AAD990814BP7|ACCEM SERVICIOS EMPRESARIALES SC|Calle PRUEBA|1525|121|Colonia de PRUEBA|Ciudad de PRUEBA|ref|Delegacion dePRUEBA|Michoacan|Mexico|25364|Regimen de Actividades Agricolas, Ganaderas, Silvicolas y Pesqueras|AGU900725BL5|EL ARTE GUADALUPANO SADE CV|FRAY JUAN DE ZUMARRAGA SECC1|21|LOC22 Y 25 COLVILLA DE GUAD|GUSTAVO AM|avenida|GUSTAVO AM|Distrito Federal|MEXICO|07050|1.000000|Pza.|Concepto Prueba|5.0000|5.0000|IVA|16.00|0.8000|0.8000||"
		def file = new File("src/test/resources/prueba.xml")
		def cadenaOriginal = cadenaOriginalServicio.generaCadenaOriginal(file)
		assertEquals(expectedCadenaOriginal, cadenaOriginal)
	}
	
}
