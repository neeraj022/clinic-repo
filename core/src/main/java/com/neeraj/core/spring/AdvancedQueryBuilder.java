package com.neeraj.core.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

/**
 * This class to build the query which will be analysed on the server side 
 * and apply these criteria to the session
 * 
 * 
 * @author Neeraj
 *
 */
public class AdvancedQueryBuilder {
	/**
	 * For applying restriction api of hibernate
	 */
	private List<Criterion> listCriteria;
	/**
	 * For imposing order by 
	 */
	private List<Order> listOrder;
	/**
	 * To restict maximum number of results by the value of maxResults parameter
	 */
	private ProjectionList listProjection;
	
	private int maxResults;
/**
 * To ignore the first n results
 */
	private int firstResults;
	
	
	
	public void addCondition(Criterion criterion)
	{
		if(listCriteria==null)
		{
			listCriteria=new ArrayList<Criterion>();
		}
		
		listCriteria.add(criterion);
	}
	
	public void addCondition(Order order)
	{
		if(listOrder==null)
		{
			listOrder=new ArrayList<Order>();
		}
		listOrder.add(order);
	}
	
	public void addCondition(Projection projection)
	{
		if(listProjection==null)
		{
			listProjection=Projections.projectionList();
		}
		listProjection.add(projection);
	}

	public List<Criterion> getListCriteria() {
		return listCriteria;
	}

	public void setListCriteria(List<Criterion> listCriteria) {
		this.listCriteria = listCriteria;
	}

	public List<Order> getListOrder() {
		return listOrder;
	}

	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getFirstResults() {
		return firstResults;
	}

	public void setFirstResults(int firstResults) {
		this.firstResults = firstResults;
	}

	public ProjectionList getListProjection() {
		return listProjection;
	}

	public void setListProjection(ProjectionList listProjection) {
		this.listProjection = listProjection;
	}

	@Override
	public String toString() {
		return "QueryBuilder [listCriteria=" + listCriteria + ", listOrder=" + listOrder + ", listProjection=" + listProjection + ", maxResults="
				+ maxResults + ", firstResults=" + firstResults + "]";
	}

	
	
	

}
