package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.clinic.model.gen.BlockMs;
import com.neeraj.clinic.server.service.BlockMsService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/blockms")
public class BlockMsController extends GlobalController<BlockMs>  {
	private static final Logger logger = Logger.getLogger(BlockMsController.class);

	public BlockMsController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
	}

	private BlockMsService blockMsService;

  	public void setBlockMsService(BlockMsService blockMsService) {
	   	this.blockMsService = blockMsService;
		super.setGlobalService(this.blockMsService);
	}
}