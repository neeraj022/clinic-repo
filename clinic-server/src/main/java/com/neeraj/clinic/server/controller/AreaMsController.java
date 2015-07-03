package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.AreaMs;
import com.neeraj.clinic.server.service.AreaMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/areams")
public class AreaMsController extends GlobalController<AreaMs>  {
	private static final Logger logger = Logger.getLogger(AreaMsController.class);

	public AreaMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private AreaMsService areaMsService;

  	public void setAreaMsService(AreaMsService areaMsService) {
	   	this.areaMsService = areaMsService;
		super.setGlobalService(this.areaMsService);
	}
}