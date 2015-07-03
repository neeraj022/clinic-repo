package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.AreaMs;
import com.neeraj.clinic.server.dao.AreaMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class AreaMsService extends GlobalService<AreaMs> {
	private static final Logger logger = Logger.getLogger(AreaMsService.class);

    public AreaMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private AreaMsDAO areaMsDAO;

    public void setAreaMsDAO(AreaMsDAO areaMsDAO) {
        this.areaMsDAO = areaMsDAO;
        super.setGlobalDAO(this.areaMsDAO);
    }
} 