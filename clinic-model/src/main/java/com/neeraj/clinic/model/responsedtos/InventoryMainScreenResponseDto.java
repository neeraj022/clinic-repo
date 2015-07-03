package com.neeraj.clinic.model.responsedtos;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryMainScreenResponseDto {
	
	private String medName;
	private String medDesc;
	private String medDrugWt;
	private Date purchaseDttm;
	private Date expiryDttm;
	private Integer unit;
	private BigDecimal cost;
	private BigDecimal totalCost;
	private String place;
	private String roomNo;
	private String locDesc;
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public String getMedDesc() {
		return medDesc;
	}
	public void setMedDesc(String medDesc) {
		this.medDesc = medDesc;
	}
	public String getMedDrugWt() {
		return medDrugWt;
	}
	public void setMedDrugWt(String medDrugWt) {
		this.medDrugWt = medDrugWt;
	}
	public Date getPurchaseDttm() {
		return purchaseDttm;
	}
	public void setPurchaseDttm(Date purchaseDttm) {
		this.purchaseDttm = purchaseDttm;
	}
	public Date getExpiryDttm() {
		return expiryDttm;
	}
	public void setExpiryDttm(Date expiryDttm) {
		this.expiryDttm = expiryDttm;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	

}
