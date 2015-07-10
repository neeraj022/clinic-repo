package com.neeraj.core.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

import com.neeraj.core.spring.ApplicationContextProvider;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CommonLookup {
	private Stage lookupStage;
	private List<String> columnNames;
	private List<String> fieldNames;
	private List<Object> dataList;
	private Class objectClassName;
	private TableView lookupTable;
	private TextField searchField;
	private ObservableList tableData = FXCollections.observableArrayList();
	private ObservableList filterData = FXCollections.observableArrayList();
	private Button ok;
	private Button cancel;
	private Object returnDto;
	private IntegerProperty num;
	private MyTable myTable;
	private Logger logger = Logger.getLogger(CommonLookup.class);

	public CommonLookup(Stage lookupStage, List<String> columnNames, List<String> fieldNames, List<Object> dataList, Class objectClassName,
			Object returnDto, IntegerProperty num) {
		this.lookupStage = lookupStage;
		this.columnNames = columnNames;
		this.fieldNames = fieldNames;
		this.dataList = dataList;
		this.objectClassName = objectClassName;
		this.num = num;
		this.returnDto = returnDto;
	}

	public void startLookup(String title) {
		this.setStructure(title);
		this.setNames();
		myTable = new MyTable(lookupTable, objectClassName);
		
		if(columnNames==null)
		{
			columnNames=new ArrayList<String>();
			fieldNames=new ArrayList<String>();
			Field fieldArray[]= objectClassName.getDeclaredFields();
			for(Field field:fieldArray)
			{
				fieldNames.add(field.getName());
				columnNames.add(field.getName());
			}
		}

		for (int i = 0; i < columnNames.size(); i++) {
			myTable.addColumn(columnNames.get(i), fieldNames.get(i), 80d);

		}
		for (Object object : dataList) {
			tableData.add(object);
		}
		myTable.setItems(tableData);
		
		//search field actions
		searchFilter();
		

	}

	private void setStructure(String title) {
		BorderPane borderPane = new BorderPane();
		lookupTable = new TableView();
		searchField = new TextField();
		searchField.setLayoutX(200d);
		searchField.setLayoutY(20d);
		HBox searchHbox = new HBox();
		searchHbox.setSpacing(5d);
		searchHbox.setPadding(new Insets(5));
		searchHbox.getChildren().add(searchField);
		searchHbox.setAlignment(Pos.CENTER);
		borderPane.setTop(searchHbox);
		borderPane.setCenter(lookupTable);
		HBox hbox = new HBox();
		ok = new Button();
		cancel = new Button();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(5d);
		hbox.setPadding(new Insets(5));
		hbox.getChildren().addAll(ok, cancel);
		borderPane.setBottom(hbox);
		Scene scene = new Scene(borderPane, 600, 400);
		scene.getStylesheets().add(ApplicationContextProvider.getMessage("defaultCssPath", null, null));
		lookupStage.setScene(scene);
		lookupStage.setTitle(title != null ? title : "");
		lookupStage.show();

		setActions();
	}

	private void setNames() {
		ok.setText("ok");
		ok.setId("ok-button");

		cancel.setText("cancel");
		cancel.setId("cancel-button");
	}

	private void setActions() {
		ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (lookupTable.getSelectionModel().getSelectedIndex() != -1) {
					returnDto = lookupTable.getSelectionModel().getSelectedItem();

					num.setValue(1);
					lookupStage.close();
				}
			}
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				lookupStage.close();
			}
		});

		lookupTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() > 1) {
					setFinalDto(lookupTable.getSelectionModel().getSelectedItem(), returnDto);
					num.setValue(1);
					lookupStage.close();
				}
			}
		});

		
	}

	private void setFinalDto(Object clickDto, Object finalDto) {
		Field fields[] = clickDto.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String getMethodString = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			String setMethodString = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = ReflectionUtils.findMethod(clickDto.getClass(), getMethodString);
			Method setMethod = ReflectionUtils.findMethod(clickDto.getClass(), setMethodString, field.getType());
			ReflectionUtils.invokeMethod(setMethod, finalDto, ReflectionUtils.invokeMethod(getMethod, clickDto));
		}
	}

	private void searchFilter() {
		CommonlyUsedMethods commonlyUsedMethods=new CommonlyUsedMethods();
		commonlyUsedMethods.setTextFieldAsSearchField(myTable, searchField, tableData, filterData, dataList, objectClassName, fieldNames);
		/*searchField.textProperty().addListener(new ChangeListener<String>() {

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
		});*/

	}
}
