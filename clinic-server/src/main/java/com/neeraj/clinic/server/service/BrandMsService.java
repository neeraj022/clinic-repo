package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.BrandMs;
import com.neeraj.clinic.server.dao.BrandMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class BrandMsService extends GlobalService<BrandMs> {
	private static final Logger logger = Logger.getLogger(BrandMsService.class);

    public BrandMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private BrandMsDAO brandMsDAO;

    public void setBrandMsDAO(BrandMsDAO brandMsDAO) {
        this.brandMsDAO = brandMsDAO;
        super.setGlobalDAO(this.brandMsDAO);
    }
} 