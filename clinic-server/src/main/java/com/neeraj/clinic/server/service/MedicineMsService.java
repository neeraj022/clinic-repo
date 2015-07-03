package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.MedicineMs;
import com.neeraj.clinic.server.dao.MedicineMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class MedicineMsService extends GlobalService<MedicineMs> {
	private static final Logger logger = Logger.getLogger(MedicineMsService.class);

    public MedicineMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private MedicineMsDAO medicineMsDAO;

    public void setMedicineMsDAO(MedicineMsDAO medicineMsDAO) {
        this.medicineMsDAO = medicineMsDAO;
        super.setGlobalDAO(this.medicineMsDAO);
    }
} 