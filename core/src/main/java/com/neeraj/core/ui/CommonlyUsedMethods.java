package com.neeraj.core.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

public class CommonlyUsedMethods {
	private Logger logger = Logger.getLogger(CommonlyUsedMethods.class);
	
	public void setTextFieldAsSearchField(final MyTable myTable,final TextField searchField,final ObservableList tableData,final ObservableList filterData,final List<Object> dataList,final Class objectClassName)
	{
		List<String> fieldNames=new ArrayList<String>();
			fieldNames=new ArrayList<String>();
			Field fieldArray[]= objectClassName.getDeclaredFields();
			for(Field field:fieldArray)
			{
				fieldNames.add(field.getName());
			}
			setTextFieldAsSearchField(myTable, searchField, tableData, filterData, dataList, objectClassName, fieldNames);
	}
	
	public void setTextFieldAsSearchField(final MyTable myTable,final TextField searchField,final ObservableList tableData,final ObservableList filterData,final List<Object> dataList,final Class objectClassName,final List<String> fieldNames)
	{
		
		
		searchField.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (searchField.getText().length() > 2) {
					String toBeSearched = searchField.getText();
					filterData.clear();
					for (String fieldName : fieldNames) {
						Field field = ReflectionUtils.findField(objectClassName, fieldName);
						ReflectionUtils.makeAccessible(field);
						for (Object object : dataList) {
							try {
								Object fieldValue = field.get(object);
								if (fieldValue != null) {
									if (fieldValue.toString().toUpperCase().contains(toBeSearched.toUpperCase())) {
										
										if(!filterData.contains(object))
										{
											filterData.add(object);
										}
									}
								}
							} catch (IllegalArgumentException e) {
								logger.error(e.toString());
							} catch (IllegalAccessException e) {
								logger.error(e.toString());
							}
						}
					}
					myTable.setItems(filterData);
				}

				else {
					myTable.setItems(tableData);
				}

			}
		});
	}

}
