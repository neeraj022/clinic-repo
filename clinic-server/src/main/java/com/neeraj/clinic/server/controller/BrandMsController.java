package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.BrandMs;
import com.neeraj.clinic.server.service.BrandMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/brandms")
public class BrandMsController extends GlobalController<BrandMs>  {
	private static final Logger logger = Logger.getLogger(BrandMsController.class);

	public BrandMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private BrandMsService brandMsService;

  	public void setBrandMsService(BrandMsService brandMsService) {
	   	this.brandMsService = brandMsService;
		super.setGlobalService(this.brandMsService);
	}
}