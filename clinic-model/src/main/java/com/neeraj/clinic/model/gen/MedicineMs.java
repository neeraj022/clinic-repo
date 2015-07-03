package com.neeraj.clinic.model.gen;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "medicine_ms")
public class MedicineMs {

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

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "drug_weight")
	private String drugWeight;

	@Column(name = "category")
	private String category;

	@Column(name = "description")
	private String description;

	@Column(name = "brand_id")
	private Long brandId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * type (capsule, tablet, syringe, gel ...)
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDrugWeight() {
		return drugWeight;
	}

	public void setDrugWeight(String drugWeight) {
		this.drugWeight = drugWeight;
	}

	/*
	 * category (paracetamol, pain killer....)
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
