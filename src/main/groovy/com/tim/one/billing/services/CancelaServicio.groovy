package com.tim.one.billing.services

import com.tim.one.billing.response.CancelResponse

interface CancelaServicio {
	
	CancelResponse cancelaFactura(String uuid, String rfcContribuyente)
	
}
