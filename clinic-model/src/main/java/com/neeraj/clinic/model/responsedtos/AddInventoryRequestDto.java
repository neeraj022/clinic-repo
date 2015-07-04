package com.neeraj.clinic.model.responsedtos;

import java.math.BigDecimal;
import java.util.Date;

public class AddInventoryRequestDto {
	
	private Long medicineId, locationId;
	private String lotNo,mvmtType;
	private Date purchaseDate, expiryDate;
	private int units;
	private BigDecimal costPerUnit;
	private boolean autoGenerateLotNo;
	private Long barCode;
	private Long inventoryId;
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public boolean isAutoGenerateLotNo() {
		return autoGenerateLotNo;
	}
	public void setAutoGenerateLotNo(boolean autoGenerateLotNo) {
		this.autoGenerateLotNo = autoGenerateLotNo;
	}
	public String getMvmtType() {
		return mvmtType;
	}
	public void setMvmtType(String mvmtType) {
		this.mvmtType = mvmtType;
	}
	public Long getBarCode() {
		return barCode;
	}
	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}
	public Long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
	public BigDecimal getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(BigDecimal costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
}
