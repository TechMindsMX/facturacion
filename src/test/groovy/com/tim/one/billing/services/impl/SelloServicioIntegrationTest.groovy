package com.tim.one.billing.services.impl

import static org.junit.Assert.assertEquals

import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.tim.one.billing.model.Concepto
import com.tim.one.billing.model.Contribuyente
import com.tim.one.billing.model.DatosDeFacturacion
import com.tim.one.billing.model.DatosFiscales
import com.tim.one.billing.services.SelloServicio

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations=["classpath:/services-appctx.xml", "classpath:/properties-appctx.xml"])
class SelloServicioIntegrationTest {

  @Autowired
  SelloServicio selloServicio

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void shouldCreateAValidSello() {
		def file = new File("src/test/resources/prueba.xml")
    def sello = selloServicio.generaSello(file)
		assertEquals("string", sello)
  }

}
