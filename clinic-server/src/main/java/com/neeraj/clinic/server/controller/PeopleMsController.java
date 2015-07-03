package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.PeopleMs;
import com.neeraj.clinic.server.service.PeopleMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/peoplems")
public class PeopleMsController extends GlobalController<PeopleMs>  {
	private static final Logger logger = Logger.getLogger(PeopleMsController.class);

	public PeopleMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private PeopleMsService peopleMsService;

  	public void setPeopleMsService(PeopleMsService peopleMsService) {
	   	this.peopleMsService = peopleMsService;
		super.setGlobalService(this.peopleMsService);
	}
}