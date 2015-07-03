package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.DistrictMs;
import com.neeraj.clinic.server.service.DistrictMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/districtms")
public class DistrictMsController extends GlobalController<DistrictMs>  {
	private static final Logger logger = Logger.getLogger(DistrictMsController.class);

	public DistrictMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private DistrictMsService districtMsService;

  	public void setDistrictMsService(DistrictMsService districtMsService) {
	   	this.districtMsService = districtMsService;
		super.setGlobalService(this.districtMsService);
	}
}