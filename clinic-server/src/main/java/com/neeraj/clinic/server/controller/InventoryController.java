package com.neeraj.clinic.server.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neeraj.core.generics.GlobalController;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.clinic.model.gen.Inventory;
import com.neeraj.clinic.model.responsedtos.AddInventoryRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenRequestDto;
import com.neeraj.clinic.server.service.InventoryService;

//Generated {05 Jun,2015 17:22:27} by Neeraj

@Controller
@RequestMapping("/inventory")
public class InventoryController extends GlobalController<Inventory> {
	private static final Logger logger = Logger.getLogger(InventoryController.class);

	public InventoryController() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName() + "] hashCode [" + this.hashCode() + "]");
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/invmainscreen", produces = { "application/json" })
	@ResponseBody
	public MyResponse getInventoryMainScreenData(@RequestBody InventoryMainScreenRequestDto requestDto) {
		return inventoryService.getInventoryMainScreenData(requestDto);

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getlotNo", produces = { "application/json" })
	@ResponseBody
	public MyResponse getLotNo() {
		return inventoryService.getLotNo();

	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/addinventory", produces = { "application/json" })
	@ResponseBody
	public String addInventory(@RequestBody AddInventoryRequestDto addInventoryRequestDto) {
		return inventoryService.addInventory(addInventoryRequestDto);

	}

	private InventoryService inventoryService;

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
		super.setGlobalService(this.inventoryService);
	}
}