package com.neeraj.core.generics;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.neeraj.core.spring.AdvancedQueryBuilder;

public class GlobalDAO<T> {

	private SessionFactory sessionFactory;
	private Logger logger = LoggerFactory.getLogger(GlobalDAO.class);
	private String entityName;
	private Class<T> persistentClass;

	public GlobalDAO() {
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			this.persistentClass = (Class<T>) getClass().getGenericSuperclass();
		}

		logger.debug("Persistent Class found [" + this.persistentClass.getCanonicalName() + "]");
		this.entityName = this.persistentClass.getCanonicalName();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Object> search(Map<String, String[]> queryMap, List<Order> listOrder, List<Criterion> listRestriction) {
		logger.debug(queryMap.toString());
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(entityName);
		//add basic conditions found in a map
		for(Map.Entry<String, String[]> mapEntry : queryMap.entrySet())
		{
			Restrictions.eq(mapEntry.getKey(), mapEntry.getValue());
		}
		// add restrictions to session criteria
		if (listRestriction != null) {
			for (Criterion criterion : listRestriction) {
				criteria.add(criterion);
			}
		}
		// add order to the session criteria
		if (listOrder != null) {
			for (Order order : listOrder) {
				criteria.addOrder(order);
			}
		}
		
		return criteria.list();
	}

	public List<Object> update(T t) {
		logger.debug(t.toString());
		Session session = this.getSession();
		updateMandatoryFields(t, "U");
		session.update(t);
		session.flush();
		List<Object> list = new ArrayList<Object>();
		list.add(t);
		return list;

	}

	public List<Object> create(T t) {
		logger.debug(t.toString());
		Session session = this.getSession();
		updateMandatoryFields(t, "I");
		session.save(t);
		session.flush();
		List<Object> list = new ArrayList<Object>();
		list.add(t);
		return list;

	}

	/*
	 * Method to update and insert necessary audit fields just before insert and update --User Name updation will be added once spring security is
	 * implemented
	 */
	private void updateMandatoryFields(T t, String dmlType) {
		if (dmlType.equalsIgnoreCase("I")) {
			String insertDateMethodString = "setInsDttm";
			Method insertDateMethod = ReflectionUtils.findMethod(persistentClass, insertDateMethodString, Date.class);
			ReflectionUtils.invokeMethod(insertDateMethod, t, new Date());
			String insertUserMethodString = "setInsUsrCd";
			Method insertUserMethod = ReflectionUtils.findMethod(persistentClass, insertUserMethodString, String.class);
			ReflectionUtils.invokeMethod(insertUserMethod, t, "1");
		} else {
			String updateDateMethodString = "setUpdDttm";
			Method updateDateMethod = ReflectionUtils.findMethod(persistentClass, updateDateMethodString, Date.class);
			ReflectionUtils.invokeMethod(updateDateMethod, t, new Date());
			String updateUserMethodString = "setUpdUsrCd";
			Method updateUserMethod = ReflectionUtils.findMethod(persistentClass, updateUserMethodString, String.class);
			ReflectionUtils.invokeMethod(updateUserMethod, t, "1");
		}
	}

	/*
	 * as of now could not implement this, because object mapper is unable to convert hibernate object
	 */
	public List<Object> advancedSearch(AdvancedQueryBuilder queryBuilder) {
		logger.debug(queryBuilder.toString());
		Session session = this.getSession();

		Criteria criteria = session.createCriteria(persistentClass);
		if (queryBuilder != null) {

			// add all orders to the criteria
			List<Order> allOrders = queryBuilder.getListOrder();
			if (allOrders != null && allOrders.size() != 0) {
				for (Order order : allOrders) {
					criteria.addOrder(order);
				}
			}

			// add all criterion to the criteria
			List<Criterion> criterionsList = queryBuilder.getListCriteria();
			if (criterionsList != null && criterionsList.size() != 0) {
				for (Criterion criterion : criterionsList) {
					criteria.add(criterion);
				}
			}

			// add all projections to the criteria
			ProjectionList listProjections = queryBuilder.getListProjection();
			if (listProjections != null && listProjections.getLength() != 0) {
				criteria.setProjection(listProjections);
			}

			// set maximum results
			if (queryBuilder.getMaxResults() != 0) {
				criteria.setMaxResults(queryBuilder.getMaxResults());
			}

			// set first results
			if (queryBuilder.getFirstResults() != 0) {
				criteria.setFirstResult(queryBuilder.getFirstResults());
			}
		}
		return criteria.list();

	}

	public List<Object> delete(T t) {
		logger.debug(t.toString());
		Session session = this.getSession();

		session.delete(t);
		session.flush();
		List<Object> list = new ArrayList<Object>();
		list.add(t);
		return list;

	}

	// method to get the hibernate session
	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception e) {
			logger.debug("no current session found");
		}
		if (session == null) {
			session = sessionFactory.openSession();
			logger.debug("new session instantiated");
		}
		return session;
	}
}
