package com.neeraj.core.ui;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.neeraj.core.date.DateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class MyTable<E> {
	private TableView<E> tableView;
	private Class<E> persistentClass;
	private Logger logger=Logger.getLogger(MyTable.class);

	public MyTable(TableView<E> tableView, Class<E> parameterType) {
		this.tableView = tableView;
		tableView.getColumns().clear();
		this.persistentClass = parameterType;
	}

	/*
	 * Add a column by supplying column and field Name
	 */
	public void addColumn(String columnName, String fieldName, Double minWidth) {

		Field field = ReflectionUtils.findField(persistentClass, fieldName);
		ReflectionUtils.makeAccessible(field);
		Class dataTypeClass = field.getType();
		String dataType = dataTypeClass.toString();
		if (dataType.trim().contains("String")) {
			TableColumn<E, String> tableColumn = new TableColumn<E, String>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, String>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if ((dataType.trim().contains("char")) || (dataType.trim().contains("Character"))) {
			TableColumn<E, Character> tableColumn = new TableColumn<E, Character>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Character>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if ((dataType.trim().contains("int")) || (dataType.trim().contains("Integer"))) {
			TableColumn<E, Integer> tableColumn = new TableColumn<E, Integer>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Integer>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if ((dataType.trim().contains("byte")) || (dataType.trim().contains("Byte"))) {
			TableColumn<E, Byte> tableColumn = new TableColumn<E, Byte>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Byte>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if ((dataType.trim().contains("long")) || (dataType.trim().contains("Long"))) {
			TableColumn<E, Long> tableColumn = new TableColumn<E, Long>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Long>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if ((dataType.trim().contains("short")) || (dataType.trim().contains("Short"))) {
			TableColumn<E, Short> tableColumn = new TableColumn<E, Short>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Short>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if (dataType.trim().contains("BigDecimal")) {
			TableColumn<E, BigDecimal> tableColumn = new TableColumn<E, BigDecimal>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, BigDecimal>(fieldName));
			tableView.getColumns().add(tableColumn);
		} else if (dataType.trim().contains("Date")) {
			TableColumn<E, Date> tableColumn = new TableColumn<E, Date>(columnName);
			tableColumn.setCellValueFactory(new PropertyValueFactory<E, Date>(fieldName));
			tableView.getColumns().add(tableColumn);
		}

	}

	/*
	 * Column addition for ready made columns
	 */
	public void addColumn(TableColumn<E, ?> tableColumn) {

		tableView.getColumns().add(tableColumn);

	}

	/*
	 * Column addition for colored row depending on the date value
	 */

	public void addColoredColumnOnDate(String columnName, String fieldName, Double minWidth) {
		TableColumn<E, Date> tableColumn = new TableColumn<E, Date>(columnName);
		tableColumn.setCellValueFactory(new PropertyValueFactory<E, Date>(fieldName));
		tableColumn.setCellFactory(new Callback<TableColumn<E, Date>, TableCell<E, Date>>() {

			@Override
			public TableCell<E, Date> call(TableColumn<E, Date> arg0) {
				
				return new TableCell<E, Date>() {
					@Override
					public void updateItem(final Date value, final boolean empty) {
						super.updateItem(value, empty);
						if (value != null) {
							int noOfDays = DateUtil.getDaysDiff(value, new Date());
							if (this.getTableRow() != null) {
								if (noOfDays <= 0) {
									this.getTableRow().getStyleClass().add("expired-date");
								} else if (noOfDays > 0 && noOfDays <= 15) {
									this.getTableRow().getStyleClass().add("almost-expired-date");
								} else if (noOfDays > 15 & noOfDays <= 60) {
									this.getTableRow().getStyleClass().add("about-to-expired-date");
								}
							}

							this.setText(value.toString());
						}
						else
						{
							this.setText(null);
							this.getTableRow().getStyleClass().remove("expired-date");
							this.getTableRow().getStyleClass().remove("almost-expired-date");
							this.getTableRow().getStyleClass().remove("about-to-expired-date");
						}
					}
				};
			}
		});
		tableView.getColumns().add(tableColumn);
	}

	public void setItems(ObservableList<E> list) {
		tableView.setItems(list);
	}
	
	public void setItems(List<E> list) {
		ObservableList<E> dataList=FXCollections.observableArrayList();
		for(E e:list)
		{
			dataList.add(e);
		}
		tableView.setItems(dataList);
	}
}
