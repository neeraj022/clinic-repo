package com.neeraj.clinic.model.responsedtos;

import java.util.Date;

public class InventoryMainScreenRequestDto {
	
	private boolean purchaseDateGtSelected;
	private boolean expiryDateGtSelected;
	private Date purchaseDate;
	private Date expiryDate;
	private int locationSliderValue;
	private Long medicineId;
	private Long locationId;
	private String place;
	private String roomNo;
	public boolean isPurchaseDateGtSelected() {
		return purchaseDateGtSelected;
	}
	public void setPurchaseDateGtSelected(boolean purchaseDateGtSelected) {
		this.purchaseDateGtSelected = purchaseDateGtSelected;
	}
	public boolean isExpiryDateGtSelected() {
		return expiryDateGtSelected;
	}
	public void setExpiryDateGtSelected(boolean expiryDateGtSelected) {
		this.expiryDateGtSelected = expiryDateGtSelected;
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
	public int getLocationSliderValue() {
		return locationSliderValue;
	}
	public void setLocationSliderValue(int locationSliderValue) {
		this.locationSliderValue = locationSliderValue;
	}
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
	

}
