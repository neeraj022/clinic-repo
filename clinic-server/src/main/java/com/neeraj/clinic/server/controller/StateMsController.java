package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.StateMs;
import com.neeraj.clinic.server.service.StateMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/statems")
public class StateMsController extends GlobalController<StateMs>  {
	private static final Logger logger = Logger.getLogger(StateMsController.class);

	public StateMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private StateMsService stateMsService;

  	public void setStateMsService(StateMsService stateMsService) {
	   	this.stateMsService = stateMsService;
		super.setGlobalService(this.stateMsService);
	}
}