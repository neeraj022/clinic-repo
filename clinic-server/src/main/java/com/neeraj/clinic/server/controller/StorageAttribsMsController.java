package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.StorageAttribsMs;
import com.neeraj.clinic.server.service.StorageAttribsMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/storageattribsms")
public class StorageAttribsMsController extends GlobalController<StorageAttribsMs>  {
	private static final Logger logger = Logger.getLogger(StorageAttribsMsController.class);

	public StorageAttribsMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private StorageAttribsMsService storageAttribsMsService;

  	public void setStorageAttribsMsService(StorageAttribsMsService storageAttribsMsService) {
	   	this.storageAttribsMsService = storageAttribsMsService;
		super.setGlobalService(this.storageAttribsMsService);
	}
}