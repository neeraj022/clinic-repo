package com.neeraj.clinic.model.responsedtos;

import java.util.Date;

public class LotNoResponseDto {
	private String lotNo;
	private Date purchaseDttm;

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Date getPurchaseDttm() {
		return purchaseDttm;
	}

	public void setPurchaseDttm(Date purchaseDttm) {
		this.purchaseDttm = purchaseDttm;
	}

}
