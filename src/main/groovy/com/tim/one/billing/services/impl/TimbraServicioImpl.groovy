package com.tim.one.billing.services.impl

import org.apache.commons.io.IOUtils
import com.tim.one.billing.services.TimbraServicio

class TimbraServicioImpl implements TimbraServicio {

	@Override
	void timbra(InputStream inputStream) {
		IOUtils.toByteArray(inputStream)
	}

}
