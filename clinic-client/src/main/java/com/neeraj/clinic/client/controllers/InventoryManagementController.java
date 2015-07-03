package com.neeraj.clinic.client.controllers;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import com.neeraj.clinic.client.commons.CommonLookups;
import com.neeraj.clinic.model.gen.MedicineMs;
import com.neeraj.clinic.model.gen.StorageAttribsMs;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenResponseDto;
import com.neeraj.core.date.DateUtil;
import com.neeraj.core.generics.GlobalInteractionClient;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.core.spring.ApplicationContextProvider;
import com.neeraj.ui.CommonCallingBase;
import com.neeraj.ui.CommonLookup;
import com.neeraj.ui.MyTable;
import com.neeraj.ui.QueryBuilder;

public class InventoryManagementController implements Initializable {
	Logger logger = Logger.getLogger(InventoryManagementController.class);

	@FXML
	private Button add, clear, expiryDateGtB, expiryDateLtB, medicineB, purchaseDateGtB, purchaseDateLtB, retrieve, storageB;

	@FXML
	private Label expiryDateL, medicineL, purchaseDateL, storageL, searchL;

	@FXML
	private TextField storageT, tableSearch, medicineT;

	@FXML
	private TableView<InventoryMainScreenResponseDto> invDtls;

	@FXML
	private Slider locationSlider;

	@FXML
	private TitledPane queryPanel, detailsPanel;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private HBox queryPanelHBox, expiryHBox, purchaseHBox;
	private DatePicker purchaseDatePicker, expiryDatePicker;

	// QueryParameters to be send to service side
	private boolean purchaseDateGtSelected = true;
	private boolean expiryDateGtSelected = true;
	private Date purchaseDate;
	private Date expiryDate;
	private int locationSliderValue;
	private Long medicineId;
	private Long locationId;
	private String place;
	private String roomNo;

	private MyTable<InventoryMainScreenResponseDto> myTable;
	private ObservableList<InventoryMainScreenResponseDto> mainScreenObservableList = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {
		setNames();
		setActions();
		setDefaults();
		setTable();

	}

	/*
	 * set names of butttons,labels,heading etc..
	 */
	private void setNames() {
		add.setText(ApplicationContextProvider.getMessage("label.global.add", null, null, Locale.US));
		clear.setText(ApplicationContextProvider.getMessage("label.global.clear", null, null, Locale.US));
		retrieve.setText(ApplicationContextProvider.getMessage("label.global.retrieve", null, null, Locale.US));
		expiryDateL.setText(ApplicationContextProvider.getMessage("label.global.expiryDate", null, null, Locale.US));
		medicineL.setText(ApplicationContextProvider.getMessage("label.global.medicine", null, null, Locale.US));
		purchaseDateL.setText(ApplicationContextProvider.getMessage("label.global.purchaseDate", null, null, Locale.US));
		storageL.setText(ApplicationContextProvider.getMessage("label.global.location", null, null, Locale.US));
		searchL.setText(ApplicationContextProvider.getMessage("label.global.search", null, null, Locale.US));
		queryPanel.setText(ApplicationContextProvider.getMessage("label.global.queryPanel", null, null, Locale.US));
		detailsPanel.setText(ApplicationContextProvider.getMessage("label.global.detailsPanel", null, null, Locale.US));
	}

	/*
	 * set button clicks, table clicks etc
	 */

	private void setActions() {

		retrieve.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				getData();

			}
		});

		medicineB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				IntegerProperty num=new SimpleIntegerProperty();
				final MedicineMs returnDto=new MedicineMs();
				CommonLookups.medicineLookup(num,returnDto);
				

				num.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						medicineT.setText(returnDto.getName() == null ? "" : returnDto.getName());
						medicineId = returnDto.getId();

					}
				});
			}
		});

		storageB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				final StorageAttribsMs returnDto = new StorageAttribsMs();
				IntegerProperty num = new SimpleIntegerProperty();
				CommonLookups.locationLookup(num, returnDto);

				num.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						storageT.setText(returnDto.getPlace() + " : " + returnDto.getRoomNo());
						locationId = returnDto.getId();
						place = returnDto.getPlace();
						roomNo = returnDto.getRoomNo();
					}
				});
			}
		});

		queryPanel.expandedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean newValue, Boolean oldValue) {
				if (newValue) {
					// expand the details panel
					detailsPanel.setPrefHeight(1000d);
					invDtls.setPrefHeight(1000d);
					
				} else {
					// revert the details panel to original height
					detailsPanel.setPrefHeight(460d);
					invDtls.setPrefHeight(464d);
				}
			}
		});

		purchaseDateGtB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				purchaseDateGtSelected = true;
				freezeAsPressed(purchaseDateGtB);
				freezeAsNonPressed(purchaseDateLtB);

			}
		});
		purchaseDateLtB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				purchaseDateGtSelected = false;
				freezeAsPressed(purchaseDateLtB);
				freezeAsNonPressed(purchaseDateGtB);

			}
		});
		expiryDateGtB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				expiryDateGtSelected = true;
				freezeAsPressed(expiryDateGtB);
				freezeAsNonPressed(expiryDateLtB);

			}
		});
		expiryDateLtB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				expiryDateGtSelected = false;
				freezeAsPressed(expiryDateLtB);
				freezeAsNonPressed(expiryDateGtB);
			}
		});

		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				medicineT.clear();
				storageT.clear();
				locationSlider.setValue(0d);
				expiryDatePicker.getEditor().clear();
				purchaseDatePicker.getEditor().clear();
				mainScreenObservableList.clear();
				tableSearch.clear();
				purchaseDateGtB.fire();
				expiryDateGtB.fire();
				locationId = null;
				medicineId = null;
				expiryDate = null;
				purchaseDate = null;
				place = null;
				roomNo = null;

			}
		});

		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				logger.debug("Opening Add Inventory Screen");
				AddIventoryController addIventoryController = new AddIventoryController();
				CommonCallingBase callingBase = new CommonCallingBase(new Stage(), addIventoryController, "addInventory.fxml",
						"heading.addInventoryScreen", null);
			}
		});
	}

	private void setTable() {
		myTable = new MyTable<InventoryMainScreenResponseDto>(invDtls, InventoryMainScreenResponseDto.class);
		myTable.addColumn("Medicine Name", "medName", 40d);
		myTable.addColumn("Description", "medDesc", 40d);
		myTable.addColumn("Drug Weight", "medDrugWt", 40d);
		myTable.addColumn("Purchase Date", "purchaseDttm", 40d);
		myTable.addColoredColumnOnDate("Expiry Date", "expiryDttm", 40d);
		myTable.addColumn("Place", "place", 40d);
		myTable.addColumn("Room No", "roomNo", 40d);
		myTable.addColumn("Location Description", "locDesc", 40d);
		myTable.addColumn("Units", "unit", 40d);
		myTable.addColumn("Cost", "cost", 40d);
		myTable.addColumn("Total Cost", "totalCost", 40d);

	}

	private void setDefaults() {
		purchaseDatePicker = new DatePicker();
		purchaseHBox.getChildren().add(purchaseDatePicker);
		expiryDatePicker = new DatePicker();
		expiryHBox.getChildren().add(expiryDatePicker);

		// actions of date picker
		expiryDatePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (expiryDatePicker.getValue() != null) {
					//expiryDatePicker.getEditor().setText(expiryDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
					expiryDate = Date.from(expiryDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				}
			}
		});

		purchaseDatePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (purchaseDatePicker.getValue() != null) {
					//purchaseDatePicker.getEditor().setText(purchaseDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
					purchaseDate = Date.from(purchaseDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

				}
			}
		});
		freezeAsPressed(purchaseDateGtB);
		freezeAsPressed(expiryDateGtB);

		locationSlider.setMin(0);
		locationSlider.setMax(2);
		locationSlider.setValue(1);
		locationSlider.setMinorTickCount(0);
		locationSlider.setMajorTickUnit(1);
		locationSlider.setSnapToTicks(true);
		locationSlider.setShowTickMarks(true);
		locationSlider.setShowTickLabels(true);
		locationSlider.setValue(0d);

		// set values for slider
		locationSlider.setLabelFormatter(new StringConverter<Double>() {

			@Override
			public String toString(Double n) {
				if (n == 0)
					return "Place";
				if (n == 1)
					return "Room";
				if (n == 2)
					return "Exact";
				return "Place";
			}

			@Override
			public Double fromString(String value) {
				switch (value) {
				case "Place":
					return 0d;
				case "Room":
					return 1d;
				case "Exact":
					return 2d;

				default:
					return 0d;
				}
			}
		});
		locationSlider.valueProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue arg0, Object arg1, Object arg2) {

				if ((Double) arg2 % 1 == 0) {
					locationSliderValue = ((Double) arg2).intValue();

				}
			}
		});
	}

	private void getData() {
		mainScreenObservableList.clear();
		tableSearch.clear();
		GlobalInteractionClient<InventoryMainScreenRequestDto> client = (GlobalInteractionClient<InventoryMainScreenRequestDto>) ApplicationContextProvider
				.getBean("globalInteractionClient");
		InventoryMainScreenRequestDto inventoryMainScreenRequestDto = new InventoryMainScreenRequestDto();
		if (validationsBeforeSearch(inventoryMainScreenRequestDto)) {
			MyResponse a = client.write("inventory/invmainscreen/", HttpMethod.POST, inventoryMainScreenRequestDto,
					InventoryMainScreenResponseDto.class);
			if (a.getData() != null) {
				for (Object o : a.getData()) {
					mainScreenObservableList.add((InventoryMainScreenResponseDto) o);
				}
				myTable.setItems(mainScreenObservableList);
			}
		} /*else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Validations not proper");
			alert.showAndWait();
		}*/
	}

	private void freezeAsPressed(Button node) {
		node.getStyleClass().remove("non-pressed-button");
		if (!node.getStyleClass().contains("pressed-button")) {
			node.getStyleClass().add("pressed-button");
		}

	}

	private void freezeAsNonPressed(Button node) {
		node.getStyleClass().remove("pressed-button");
		if (!node.getStyleClass().contains("non-pressed-button")) {
			node.getStyleClass().add("non-pressed-button");
		}

	}

	private boolean validationsBeforeSearch(InventoryMainScreenRequestDto inventoryMainScreenRequestDto) {
		inventoryMainScreenRequestDto.setMedicineId(medicineId);
		inventoryMainScreenRequestDto.setLocationId(locationId);
		inventoryMainScreenRequestDto.setExpiryDate(expiryDate);
		inventoryMainScreenRequestDto.setPurchaseDate(purchaseDate);
		inventoryMainScreenRequestDto.setPurchaseDateGtSelected(purchaseDateGtSelected);
		inventoryMainScreenRequestDto.setExpiryDateGtSelected(expiryDateGtSelected);
		inventoryMainScreenRequestDto.setLocationSliderValue(locationSliderValue);
		inventoryMainScreenRequestDto.setPlace(place);
		inventoryMainScreenRequestDto.setRoomNo(roomNo);
		return true;
	}

}
