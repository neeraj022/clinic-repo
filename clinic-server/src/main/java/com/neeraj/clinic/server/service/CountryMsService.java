package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neeraj.clinic.model.gen.CountryMs;
import com.neeraj.clinic.server.dao.CountryMsDAO;
import com.neeraj.core.generics.GlobalService;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class CountryMsService extends GlobalService<CountryMs> {
	private static final Logger logger = Logger.getLogger(CountryMsService.class);

    public CountryMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private CountryMsDAO countryMsDAO;

    public void setCountryMsDAO(CountryMsDAO countryMsDAO) {
        this.countryMsDAO = countryMsDAO;
        super.setGlobalDAO(this.countryMsDAO);
    }
} 