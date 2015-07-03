package com.neeraj.ui;

import java.util.HashMap;
import java.util.Map;

public class QueryBuilder {
	private Map<String,String> queryBuilderMap;
	
	public QueryBuilder()
	{
		queryBuilderMap=new HashMap<String,String>();
	}
	
	public void addCondition(String field,String value)
	{
		queryBuilderMap.put(field, value);
	}

	public Map<String, String> getQueryBuilderMap() {
		return queryBuilderMap;
	}

	public void setQueryBuilderMap(Map<String, String> queryBuilderMap) {
		this.queryBuilderMap = queryBuilderMap;
	}
}
