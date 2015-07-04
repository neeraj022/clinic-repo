package com.neeraj.clinic.server.commons;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.neeraj.clinic.model.gen.Inventory;
import com.neeraj.clinic.model.responsedtos.AddInventoryRequestDto;
import com.neeraj.clinic.server.service.InventoryService;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.core.spring.AdvancedQueryBuilder;
import com.neeraj.core.spring.ApplicationContextProvider;

public class InventoryManipulation {

	private InventoryService inventoryService = (InventoryService) ApplicationContextProvider.getBean("inventoryService");

	public void inventoryAddRemove(AddInventoryRequestDto addInventoryRequestDto) {
		inventoryAddRemove(addInventoryRequestDto.getMedicineId(), addInventoryRequestDto.getLocationId(), addInventoryRequestDto.getLotNo(),
				addInventoryRequestDto.getPurchaseDate(), addInventoryRequestDto.getExpiryDate(), addInventoryRequestDto.getUnits(),
				addInventoryRequestDto.getCostPerUnit(), addInventoryRequestDto.getBarCode(), addInventoryRequestDto.getMvmtType(),
				addInventoryRequestDto.getInventoryId());
	}

	public void inventoryAddRemove(Long medicineId, Long locationId, String lotNo, Date purchaseDate, Date expiryDate, int units, BigDecimal costPerUnit,
			Long barCode, String mvmtType, Long inventoryId) {

		if (mvmtType.equals("IDN") || mvmtType.equals("IUE") || mvmtType.equals("IST")) {
			// Delete the existing inventory record
			inventoryService.delete(inventoryId);
		} else if (mvmtType.equals("IUG") || mvmtType.equals("IDY")) {
			// Update the record with new count
			// Check if new count becomes zero, if this is the case then delete the record
			AdvancedQueryBuilder queryBuilder = new AdvancedQueryBuilder();
			queryBuilder.addCondition(Restrictions.eq("id", inventoryId));
			List<Object> singleEntryList = inventoryService.advancedSearch(queryBuilder).getData();
			Inventory inventory = (Inventory) singleEntryList.get(0);
			inventory.setUnit(inventory.getUnit() - units);

			if (inventory.getUnit().equals(0)) {
				// delete the record
				inventoryService.delete(inventoryId);
			} else {
				// update the record
				inventoryService.update(inventory);
			}
		}

		else if (mvmtType.equals("IAN") || mvmtType.equals("IUE") || mvmtType.equals("IST")) {

			AdvancedQueryBuilder advancedQueryBuilder = new AdvancedQueryBuilder();
			advancedQueryBuilder.addCondition(Restrictions.eq("medicineId", medicineId));
			advancedQueryBuilder.addCondition(Restrictions.eq("storageId", locationId));
			advancedQueryBuilder.addCondition(Restrictions.eq("lotNo", lotNo));
			advancedQueryBuilder.addCondition(Restrictions.eq("purchaseDttm", purchaseDate));
			advancedQueryBuilder.addCondition(Restrictions.eq("expiryDttm", expiryDate));
			advancedQueryBuilder.addCondition(Restrictions.eq("cost", costPerUnit));
			if (barCode != null) {
				advancedQueryBuilder.addCondition(Restrictions.eq("barCode", barCode));
			}

			MyResponse myResponse = inventoryService.advancedSearch(advancedQueryBuilder);

			if (myResponse.getData() != null && myResponse.getData().size() != 0) {
				// update the existing inventory
				Inventory inventory = (Inventory) myResponse.getData().get(0);
				inventory.setUnit(inventory.getUnit()+units);
				
				inventoryService.update(inventory);
			}

			else {
				// create a new inventory
				Inventory inventory=new Inventory();
				inventory.setBarCode(barCode);
				inventory.setLotNo(lotNo);
				inventory.setDelFlg('N');
				inventory.setCost(costPerUnit);
				inventory.setUnit(units);
				inventory.setPurchaseDttm(purchaseDate);
				inventory.setExpiryDttm(expiryDate);
				inventory.setMedicineId(medicineId);
				inventory.setStorageId(locationId);
				
				inventoryService.create(inventory);
				
			}

		}
	}
}
