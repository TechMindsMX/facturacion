package com.tim.one.billing.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/facturacion")
class FacturacionController {


  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody def show() {
    "Showing factura"
  }
}

