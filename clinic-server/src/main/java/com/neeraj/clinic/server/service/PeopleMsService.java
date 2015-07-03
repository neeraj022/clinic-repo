package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.PeopleMs;
import com.neeraj.clinic.server.dao.PeopleMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class PeopleMsService extends GlobalService<PeopleMs> {
	private static final Logger logger = Logger.getLogger(PeopleMsService.class);

    public PeopleMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private PeopleMsDAO peopleMsDAO;

    public void setPeopleMsDAO(PeopleMsDAO peopleMsDAO) {
        this.peopleMsDAO = peopleMsDAO;
        super.setGlobalDAO(this.peopleMsDAO);
    }
} 