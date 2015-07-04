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
@Table(name = "event_log")
public class EventLog {
	
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
		
		@Column(name = "people_id")
		private Long peopleId;
		
		@Column(name = "medicine_id")
		private Long medicineId;
		
		@Column(name = "location_id")
		private Long locationId;
		
		@Column(name = "cost_per_unit")
		private Double costPerUnit;
		
		@Column(name = "units")
		private Integer units;
		
		@Column(name = "remarks")
		private String remarks;
		
		@Column(name = "mvmt_type")
		private String mvmtType;

}
