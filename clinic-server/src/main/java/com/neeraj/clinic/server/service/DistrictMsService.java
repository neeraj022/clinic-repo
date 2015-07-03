package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.DistrictMs;
import com.neeraj.clinic.server.dao.DistrictMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class DistrictMsService extends GlobalService<DistrictMs> {
	private static final Logger logger = Logger.getLogger(DistrictMsService.class);

    public DistrictMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private DistrictMsDAO districtMsDAO;

    public void setDistrictMsDAO(DistrictMsDAO districtMsDAO) {
        this.districtMsDAO = districtMsDAO;
        super.setGlobalDAO(this.districtMsDAO);
    }
} 