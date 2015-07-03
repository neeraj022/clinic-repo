package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.StorageAttribsMs;
import com.neeraj.clinic.server.dao.StorageAttribsMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class StorageAttribsMsService extends GlobalService<StorageAttribsMs> {
	private static final Logger logger = Logger.getLogger(StorageAttribsMsService.class);

    public StorageAttribsMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private StorageAttribsMsDAO storageAttribsMsDAO;

    public void setStorageAttribsMsDAO(StorageAttribsMsDAO storageAttribsMsDAO) {
        this.storageAttribsMsDAO = storageAttribsMsDAO;
        super.setGlobalDAO(this.storageAttribsMsDAO);
    }
} 