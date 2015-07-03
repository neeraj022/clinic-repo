package com.neeraj.clinic.server.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.clinic.model.gen.Inventory;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenResponseDto;
import com.neeraj.clinic.server.dao.InventoryDAO;

//Generated {05 Jun,2015 17:27:41} by Neeraj

@Service
public class InventoryService extends GlobalService<Inventory> {
	private static final Logger logger = Logger.getLogger(InventoryService.class);

	public InventoryService() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName() + "] hashCode [" + this.hashCode() + "]");
		}
	}

	private InventoryDAO inventoryDAO;

	public void setInventoryDAO(InventoryDAO inventoryDAO) {
		this.inventoryDAO = inventoryDAO;
		super.setGlobalDAO(this.inventoryDAO);
	}

	public MyResponse getInventoryMainScreenData(InventoryMainScreenRequestDto requestDto) {
		return finalizeResult(inventoryDAO.getInventoryMainScreenData(requestDto));
	}
	
	public MyResponse getLotNo() {
		return finalizeResult(inventoryDAO.getLotNo());
	}

}