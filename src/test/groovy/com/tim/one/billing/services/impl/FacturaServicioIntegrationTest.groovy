package com.tim.one.billing.services.impl

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.TestPropertySource;

import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.IntegrationTest

import com.tim.one.billing.model.*
import com.tim.one.billing.Application
import com.tim.one.billing.services.FacturaServicio

import org.junit.Test
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(properties = ["factura.template.logo=/Users/sohjiro/.timone/factura/logo.png", "factura.template.pdf=/Users/sohjiro/.timone/factura/template.tof", "factura.template.xml=/Users/sohjiro/.timone/factura/template_xml.tof"])
@IntegrationTest
class FacturaServicioIntegrationTest {

  @Autowired
  FacturaServicio facturaServicio

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void deberiaCrearUnPdfConLosDatosDeLaFactura() {
    def numeroDeCuentaDePago = "0123456789"
    Contribuyente emisor = new Contribuyente()
    Contribuyente receptor = new Contribuyente(cuenta:numeroDeCuentaDePago)
    emisor.datosFiscales = new DatosFiscales(
      razonSocial: "INMOBILIARIA NUEVA SANTANDER SA DE CV",
      rfc: "INS8602287KA ",
      calle: "Lucas Aleman 83",
      colonia: "Obrera",
      delegacion: "Cuauhtemoc, Distrito Federal",
      codigoPostal: "06800",
      ciudad: "MÉXICO"
    )
    receptor.datosFiscales = new DatosFiscales(
      razonSocial: "Making Devs S.C.",
      rfc: "MDE130712JA6",
      calle: "Calzada Ermita Iztapalapa 278 Depto. 501",
      colonia: "Sinatel",
      delegacion: "Iztapalapa, Distrito Federal",
      codigoPostal: "09470",
      ciudad: "MÉXICO"
    )
    DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
      uuid: "UUID",
      formaDePago: "Pago en una sola exhibicion",
      tipoDeComprobante: "ingreso",
      metodoDePago: "efectivo",
      lugarDeExpedicion: "Mexico DF",
      condicionesDePago: "Contado",
      numeroDeCuentaDePago: numeroDeCuentaDePago,
      moneda: "MXN",
      tipoDeCambio: "1.00"
    )
    def conceptos = []
    conceptos << new Concepto(
      cantidad:1,
      descripcion:"descripcion1",
      unidad:"pieza",
      valorUnitario:100.00
    )

    def file = facturaServicio.generaPdfDeFactura(datosDeFacturacion, emisor, receptor, conceptos)

    assertNotNull file
    assertTrue(file instanceof File)
  }

  @Test
  public void deberiaCrearUnXmlConLosDatosDeLaFactura() {
    def numeroDeCuentaDePago = "0123456789"
    Contribuyente emisor = new Contribuyente()
    Contribuyente receptor = new Contribuyente(cuenta:numeroDeCuentaDePago)
    emisor.datosFiscales = new DatosFiscales(
      razonSocial: "INMOBILIARIA NUEVA SANTANDER SA DE CV",
      rfc: "INS8602287KA ",
      calle: "Lucas Aleman 83",
      colonia: "Obrera",
      delegacion: "Cuauhtemoc, Distrito Federal",
      codigoPostal: "06800",
      ciudad: "MÉXICO"
    )
    receptor.datosFiscales = new DatosFiscales(
      razonSocial: "Making Devs S.C.",
      rfc: "MDE130712JA6",
      calle: "Calzada Ermita Iztapalapa 278 Depto. 501",
      colonia: "Sinatel",
      delegacion: "Iztapalapa, Distrito Federal",
      codigoPostal: "09470",
      ciudad: "MÉXICO"
    )
    DatosDeFacturacion datosDeFacturacion = new DatosDeFacturacion(
      uuid: "UUID",
      formaDePago: "Pago en una sola exhibicion",
      tipoDeComprobante: "ingreso",
      metodoDePago: "efectivo",
      lugarDeExpedicion: "Mexico DF",
      condicionesDePago: "Contado",
      numeroDeCuentaDePago: numeroDeCuentaDePago,
      moneda: "MXN",
      tipoDeCambio: "1.00"
    )
    def conceptos = []
    conceptos << new Concepto(
      cantidad:1,
      descripcion:"descripcion1",
      unidad:"pieza",
      valorUnitario:100.00
    )

    def file = facturaServicio.generaXmlDeFactura(datosDeFacturacion, emisor, receptor, conceptos)

    assertNotNull file
    assertTrue(file instanceof File)
  }

}
