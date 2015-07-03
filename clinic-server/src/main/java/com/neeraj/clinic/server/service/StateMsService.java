package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.StateMs;
import com.neeraj.clinic.server.dao.StateMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class StateMsService extends GlobalService<StateMs> {
	private static final Logger logger = Logger.getLogger(StateMsService.class);

    public StateMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private StateMsDAO stateMsDAO;

    public void setStateMsDAO(StateMsDAO stateMsDAO) {
        this.stateMsDAO = stateMsDAO;
        super.setGlobalDAO(this.stateMsDAO);
    }
} 