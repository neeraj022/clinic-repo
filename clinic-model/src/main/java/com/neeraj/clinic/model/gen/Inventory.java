package com.neeraj.clinic.model.gen;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "inventory")
public class Inventory {

	// Common fields to be included in every model file
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "ins_dttm")
	private Date insDttm;

	@Column(name = "upd_dttm")
	private Date updDttm;

	@Column(name = "ins_usr_cd")
	private String insUsrCd;

	@Column(name = "upd_usr_cd")
	private String updUsrCd;

	@Column(name = "del_flg")
	private Character delFlg;

	@Version
	private int version;

	@Column(name = "medicine_id")
	private Long medicineId;

	@Column(name = "storage_id")
	private Long storageId;

	@Column(name = "lot_no")
	private String lotNo;

	@Column(name = "purchase_dttm")
	private Date purchaseDttm;

	@Column(name = "expiry_dttm")
	private Date expiryDttm;

	@Column(name = "unit")
	private Integer unit;

	@Column(name = "cost")
	private BigDecimal cost;

	@Column(name = "bar_code")
	private Long barCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInsDttm() {
		return insDttm;
	}

	public void setInsDttm(Date insDttm) {
		this.insDttm = insDttm;
	}

	public Date getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(Date updDttm) {
		this.updDttm = updDttm;
	}

	public String getInsUsrCd() {
		return insUsrCd;
	}

	public void setInsUsrCd(String insUsrCd) {
		this.insUsrCd = insUsrCd;
	}

	public String getUpdUsrCd() {
		return updUsrCd;
	}

	public void setUpdUsrCd(String updUsrCd) {
		this.updUsrCd = updUsrCd;
	}

	public Character getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Character delFlg) {
		this.delFlg = delFlg;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

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

	public Long getBarCode() {
		return barCode;
	}

	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}

}
