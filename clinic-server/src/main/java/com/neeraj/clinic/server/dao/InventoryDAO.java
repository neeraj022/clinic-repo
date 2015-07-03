package com.neeraj.clinic.server.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.neeraj.core.generics.GlobalDAO;
import com.neeraj.clinic.model.gen.Inventory;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenResponseDto;
import com.neeraj.clinic.model.responsedtos.LotNoResponseDto;
import com.neeraj.clinic.server.queries.Queries;

//Generated {05 Jun,2015 17:22:27} by Neeraj

public class InventoryDAO extends GlobalDAO<Inventory> {
	private static final Logger logger = Logger.getLogger(InventoryDAO.class);

	public InventoryDAO() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initialized class [" + this.getClass().getCanonicalName() + "] hashCode [" + this.hashCode() + "]");
		}
	}

	public List<InventoryMainScreenResponseDto> getInventoryMainScreenData(InventoryMainScreenRequestDto requestDto) {
		Session session = this.getSession();
		List<InventoryMainScreenResponseDto> result = null;

		String query = Queries.inv_MainScreenQuery;

		// Adding conditions
		if (requestDto.getMedicineId() != null) {
			query = query + " and med.id =:medId";
		}
		if (requestDto.getExpiryDate() != null) {
			if (requestDto.isExpiryDateGtSelected()) {
				query = query + " and inv.expiryDttm >= :expiryDttm";
			} else {
				query = query + " and inv.expiryDttm <= :expiryDttm";
			}
		}

		if (requestDto.getPurchaseDate() != null) {
			if (requestDto.isPurchaseDateGtSelected()) {
				query = query + " and inv.purchaseDttm >= :purchaseDttm";
			} else {
				query = query + " and inv.purchaseDttm <= :purchaseDttm";
			}
		}

		if (requestDto.getLocationId() != null) {
			switch (requestDto.getLocationSliderValue()) {
			case 0:
				query = query + " and strg.place = :placeOrRoom";
				break;
			case 1:
				query = query + " and strg.roomNo = :placeOrRoom";
				break;
			case 2:
				query = query + " and strg.id = :strgId";
				break;
			default:
				query = query + " and strg.id = :strgId";
				break;
			}
		}

		Query queryFinal = session.createQuery(query);

		// Adding parameter values
		if (requestDto.getMedicineId() != null) {
			queryFinal.setLong("medId", requestDto.getMedicineId());
		}
		if (requestDto.getExpiryDate() != null) {
			queryFinal.setDate("expiryDttm", requestDto.getExpiryDate());
		}
		if (requestDto.getPurchaseDate() != null) {
			queryFinal.setDate("purchaseDttm", requestDto.getPurchaseDate());
		}
		if (requestDto.getLocationId() != null) {
			if (requestDto.getLocationSliderValue() == 0) {
				queryFinal.setString("placeOrRoom", requestDto.getPlace());
			} else if (requestDto.getLocationSliderValue() == 1) {
				queryFinal.setString("placeOrRoom", requestDto.getRoomNo());
			} else {
				queryFinal.setLong("strgId", requestDto.getLocationId());
			}
		}
		result = queryFinal.setResultTransformer(Transformers.aliasToBean(InventoryMainScreenResponseDto.class)).list();

		return result;
	}
	
	
	public List<LotNoResponseDto> getLotNo() {
		Session session = this.getSession();
		List<LotNoResponseDto> result = null;

		String query = Queries.inv_LotNoQuery;

		

		Query queryFinal = session.createQuery(query);

		
		result = queryFinal.setResultTransformer(Transformers.aliasToBean(LotNoResponseDto.class)).list();

		return result;
	}
}