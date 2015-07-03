package com.neeraj.core.generics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.ReflectionUtils;

import com.neeraj.core.date.DateUtil;
import com.neeraj.core.spring.AdvancedQueryBuilder;

public class GlobalService<T> {

	private GlobalDAO<T> globalDAO;
	// variable which stores model on which this service is running
	private Class<T> persistentClass;
	private Logger logger = Logger.getLogger(GlobalService.class);

	public GlobalService() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			this.persistentClass = (Class<T>) getClass().getGenericSuperclass();
		}
	}

	public GlobalDAO<T> getGlobalDAO() {
		return globalDAO;
	}

	public void setGlobalDAO(GlobalDAO<T> globalDAO) {
		this.globalDAO = globalDAO;
	}

	public MyResponse search(HttpServletRequest httpServletRequest) {
		MyResponse myResponse = new MyResponse();
		Map<String, String[]> queryMapNonChangeable = httpServletRequest.getParameterMap();
		Map<String, String[]> queryMap = new HashMap<String, String[]>(queryMapNonChangeable);
		List<Object> result = globalDAO.search(httpServletRequest, analyseOrderParameters(queryMap), analyseRestrictionParameters(queryMap));

		if (null == result) {
			myResponse.setSuccess(false);
			myResponse.setData(null);
		} else {
			myResponse.setSuccess(true);
			myResponse.setData(result);
		}

		return myResponse;
	}

	// method to analyse parameters for applying orders
	private List<Order> analyseOrderParameters(Map<String, String[]> queryMap) {
		if (queryMap != null && queryMap.size() != 0) {
			List<Order> orderList = null;
			String ascendingString[] = queryMap.get("orderAsc");
			String descendingString[] = queryMap.get("orderDsc");
			if (ascendingString != null && ascendingString.length != 0) {
				if (orderList == null) {
					orderList = new ArrayList<Order>();
				}
				for (String ascFields : ascendingString) {
					orderList.add(Order.asc(ascFields));
				}
				queryMap.remove("orderAsc");
			}
			if (descendingString != null && descendingString.length != 0) {
				if (orderList == null) {
					orderList = new ArrayList<Order>();
				}
				for (String descFields : descendingString) {
					orderList.add(Order.desc(descFields));
				}
				queryMap.remove("orderDsc");
			}
			return orderList;
		}
		return null;
	}

	// method to analyse parameters for applying restriction
	private List<Criterion> analyseRestrictionParameters(Map<String, String[]> queryMap) {
		List<Criterion> listCriterion = null;
		if (queryMap != null && queryMap.size() != 0) {
			// analyse null, not null conditions
			String isNullString[] = queryMap.get("isNull");
			String isNotNullString[] = queryMap.get("isNotNull");

			// add null conditions to main list
			if (isNullString != null && isNullString.length != 0) {
				if (listCriterion == null) {
					listCriterion = new ArrayList<Criterion>();
				}
				for (String nullFields : isNullString) {
					listCriterion.add(Restrictions.isNull(nullFields));
				}
				queryMap.remove("isNull");
			}

			// add not null conditions to main list
			if (isNotNullString != null && isNotNullString.length != 0) {
				if (listCriterion == null) {
					listCriterion = new ArrayList<Criterion>();
				}
				for (String isNotnullFields : isNotNullString) {
					listCriterion.add(Restrictions.isNotNull(isNotnullFields));
				}
				queryMap.remove("isNotNull");
			}

			// add all other conditions
			if (listCriterion == null) {
				listCriterion = new ArrayList<Criterion>();
			}
			for (Map.Entry<String, String[]> mapEntry : queryMap.entrySet()) {
				Criterion criterion = setProperDataTypeForSearchField(mapEntry.getKey(), mapEntry.getValue() == null ? "" : mapEntry.getValue()[0]);
				if (criterion != null) {
					listCriterion.add(criterion);
				}
			}

			return listCriterion;
		}
		return null;
	}

	private Criterion setProperDataTypeForSearchField(String name, String value) {
		Criterion criterion = null;

		Field field = ReflectionUtils.findField(persistentClass, name);
		ReflectionUtils.makeAccessible(field);
		Class dataTypeClass = field.getType();
		String dataType = dataTypeClass.getName();
		if (dataType.trim().contains("String")) {
			criterion = Restrictions.eq(name, value);
		} else if ((dataType.trim().contains("char")) || (dataType.trim().contains("Character"))) {
			criterion = Restrictions.eq(name, value.charAt(0));
		} else if ((dataType.trim().contains("int")) || (dataType.trim().contains("Integer"))) {
			try {
				Integer in = Integer.parseInt(value);
				criterion = Restrictions.eq(name, in);
			} catch (Exception e) {

				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);

			}
		} else if ((dataType.trim().contains("byte")) || (dataType.trim().contains("Byte"))) {
			try {
				Byte by = Byte.parseByte(value);
				criterion = Restrictions.eq(name, by);
			} catch (Exception e) {
				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);
			}
		} else if ((dataType.trim().contains("long")) || (dataType.trim().contains("Long"))) {
			try {
				Long ln = Long.parseLong(value);
				criterion = Restrictions.eq(name, ln);
			} catch (Exception e) {
				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);
			}
		} else if ((dataType.trim().contains("short")) || (dataType.trim().contains("Short"))) {
			try {
				Short sh = Short.parseShort(value);
				criterion = Restrictions.eq(name, sh);
			} catch (Exception e) {
				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);
			}
		} else if (dataType.trim().contains("BigDecimal")) {
			try {
				BigDecimal bd = new BigDecimal(value);
				criterion = Restrictions.eq(name, bd);
			} catch (Exception e) {
				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);
			}
		} else if (dataType.trim().contains("Date")) {
			try {
				Date fromDate = DateUtil.parseDate(value, DateUtil.FORMAT_DDMMYYYY_HH24MISS);

				// Originally, was using between fromDate and fromDate+1
				// Problem: It was returning records of both days - fromDate and fromDate+1
				// Solution: Modified clause to between fromDate and fromDate
				criterion = Restrictions.between(name, fromDate, fromDate);
			} catch (Exception e) {
				logger.error("Couldn't parse " + value + " into" + dataType + " for the field " + name);
			}
		}

		return criterion;
	}

	public MyResponse update(T t) {

		List<Object> result = globalDAO.update(t);

		return finalizeResult(result);
	}

	public MyResponse create(T t) {
		List<Object> result = globalDAO.create(t);

		return finalizeResult(result);
	}

	public MyResponse advancedSearch(AdvancedQueryBuilder queryBuilder) {

		List<Object> result = globalDAO.advancedSearch(queryBuilder);

		return finalizeResult(result);
	}

	public MyResponse delete(T t) {

		List<Object> result = globalDAO.delete(t);

		return finalizeResult(result);
	}

	public MyResponse finalizeResult(Object result) {
		MyResponse myResponse = new MyResponse();
		if (null == result) {
			myResponse.setSuccess(false);
			myResponse.setData(null);
		} else {
			myResponse.setSuccess(true);
			myResponse.setData((List<Object>) result);
		}

		return myResponse;
	}

}
