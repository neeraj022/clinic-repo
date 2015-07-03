package com.neeraj.clinic.server.service;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.clinic.model.gen.BlockMs;
import com.neeraj.clinic.server.dao.BlockMsDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class BlockMsService extends GlobalService<BlockMs> {
	private static final Logger logger = Logger.getLogger(BlockMsService.class);

    public BlockMsService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName()
					+ "] hashCode [" + this.hashCode() + "]");
		}
    }

    private BlockMsDAO blockMsDAO;

    public void setBlockMsDAO(BlockMsDAO blockMsDAO) {
        this.blockMsDAO = blockMsDAO;
        super.setGlobalDAO(this.blockMsDAO);
    }
} 