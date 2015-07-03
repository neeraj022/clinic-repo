package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.MedicineMs;
import com.neeraj.clinic.server.service.MedicineMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/medicinems")
public class MedicineMsController extends GlobalController<MedicineMs>  {
	private static final Logger logger = Logger.getLogger(MedicineMsController.class);

	public MedicineMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private MedicineMsService medicineMsService;

  	public void setMedicineMsService(MedicineMsService medicineMsService) {
	   	this.medicineMsService = medicineMsService;
		super.setGlobalService(this.medicineMsService);
	}
}