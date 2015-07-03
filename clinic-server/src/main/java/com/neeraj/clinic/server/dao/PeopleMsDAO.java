package com.neeraj.clinic.server.dao;

import org.apache.log4j.Logger;
import com.neeraj.core.generics.GlobalDAO;
import com.neeraj.clinic.model.gen.PeopleMs;

//Generated {05 Jun,2015 17:22:27} by Neeraj

public class PeopleMsDAO extends GlobalDAO<PeopleMs>  {
	private static final Logger logger = Logger.getLogger(PeopleMsDAO.class);

	public PeopleMsDAO() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }
}