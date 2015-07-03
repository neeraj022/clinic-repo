package com.neeraj.clinic.model.gen;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/*
 * To store the details of inventory location names like room no, row number.
 */
@Entity
@Table(name = "storage_attribs_ms")
public class StorageAttribsMs {

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

	@Column(name = "place")
	private String place;

	@Column(name = "room_no")
	private String roomNo;

	@Column(name = "row_no")
	private String rowNo;

	@Column(name = "column_no")
	private String columnNo;

	@Column(name = "description")
	private String description;

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

	/*
	 * place like home, flat, clinic etc.
	 */
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

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(String columnNo) {
		this.columnNo = columnNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
