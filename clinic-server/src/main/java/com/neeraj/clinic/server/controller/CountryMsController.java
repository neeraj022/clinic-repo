package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.CountryMs;
import com.neeraj.clinic.server.service.CountryMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/countryms")
public class CountryMsController extends GlobalController<CountryMs>  {
	private static final Logger logger = Logger.getLogger(CountryMsController.class);

	public CountryMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private CountryMsService countryMsService;

  	public void setCountryMsService(CountryMsService countryMsService) {
	   	this.countryMsService = countryMsService;
		super.setGlobalService(this.countryMsService);
	}
}