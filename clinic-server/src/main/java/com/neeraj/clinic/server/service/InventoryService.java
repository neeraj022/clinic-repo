package com.neeraj.clinic.server.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neeraj.core.generics.GlobalService;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.clinic.model.gen.Inventory;
import com.neeraj.clinic.model.responsedtos.AddInventoryRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenResponseDto;
import com.neeraj.clinic.model.responsedtos.LotNoResponseDto;
import com.neeraj.clinic.server.commons.InventoryManipulation;
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

	@Transactional
	public MyResponse addInventory(AddInventoryRequestDto addInventoryRequestDto) {
		if (addInventoryRequestDto.isAutoGenerateLotNo()) {
			addInventoryRequestDto.setLotNo(String.valueOf(System.currentTimeMillis()));
			if (addInventoryRequestDto.getPurchaseDate() == null) {
				addInventoryRequestDto.setPurchaseDate(new Date());
			}
		}
		InventoryManipulation inventoryManipulation = new InventoryManipulation();
		inventoryManipulation.inventoryAddRemove(addInventoryRequestDto);
		MyResponse myResponse = new MyResponse();
		myResponse.setSuccess(true);
		myResponse.setData(null);
		return myResponse;
	}

}