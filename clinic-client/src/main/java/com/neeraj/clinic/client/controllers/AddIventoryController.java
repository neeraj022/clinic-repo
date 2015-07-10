package com.neeraj.clinic.client.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;

import com.neeraj.clinic.client.commons.CommonLookups;
import com.neeraj.clinic.model.gen.MedicineMs;
import com.neeraj.clinic.model.gen.StorageAttribsMs;
import com.neeraj.clinic.model.responsedtos.AddInventoryRequestDto;
import com.neeraj.clinic.model.responsedtos.InventoryMainScreenResponseDto;
import com.neeraj.clinic.model.responsedtos.LotNoResponseDto;
import com.neeraj.core.generics.GlobalInteractionClient;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.core.spring.ApplicationContextProvider;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class AddIventoryController implements Initializable {
	Logger logger = Logger.getLogger(AddIventoryController.class);
	@FXML
	private Button medicineB, lotNoB, locationB, clear, save;
	@FXML
	private Label barcodeL, purchaseDateL, costL, unitL, expiryDateL, locationL, medicineL, lotNoL;
	@FXML
	private DatePicker purchaseD, expiryD;
	@FXML
	private TextField locationT, lotNoT, unitT, barcodeT, costT, medicineT, expiryT, purchaseT;
	@FXML
	private CheckBox lotNoC;

	private Long medicineId, locationId;
	private String lotNo;
	private Date purchaseDate, expiryDate;

	public AddIventoryController() {
		logger.debug("Add Inventory Controller created");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		logger.debug("Intialize method of add inventory controller called");
		setDefaults();
		setNames();
		setActions();

	}

	private void setNames() {
		save.setText(ApplicationContextProvider.getMessage("label.global.save", null, null, Locale.US));
		clear.setText(ApplicationContextProvider.getMessage("label.global.clear", null, null, Locale.US));
		expiryDateL.setText(ApplicationContextProvider.getMessage("label.global.expiryDate", null, null, Locale.US));
		medicineL.setText(ApplicationContextProvider.getMessage("label.global.medicine", null, null, Locale.US));
		purchaseDateL.setText(ApplicationContextProvider.getMessage("label.global.purchaseDate", null, null, Locale.US));
		locationL.setText(ApplicationContextProvider.getMessage("label.global.location", null, null, Locale.US));
		lotNoL.setText(ApplicationContextProvider.getMessage("label.addinventory.lotNo", null, null, Locale.US));
		lotNoC.setText(ApplicationContextProvider.getMessage("label.addinventory.autoGenLotNo", null, Locale.US));
		unitL.setText(ApplicationContextProvider.getMessage("label.addinventory.units", null, null, Locale.US));
		costL.setText(ApplicationContextProvider.getMessage("label.addinventory.perUnitCost", null, null, Locale.US));
		barcodeL.setText(ApplicationContextProvider.getMessage("label.addinventory.barcode", null, null, Locale.US));
		purchaseD.getEditor().setPromptText(ApplicationContextProvider.getMessage("label.global.dateFormatString", null, null, Locale.US));
		expiryD.getEditor().setPromptText(ApplicationContextProvider.getMessage("label.global.dateFormatString", null, null, Locale.US));

	}

	private void setDefaults() {
		purchaseT.setPromptText("Mins");
		expiryT.setPromptText("Mins");
		barcodeT.setDisable(true);
		purchaseD.setDisable(true);
		purchaseT.setDisable(true);
		lotNoC.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					lotNoB.setDisable(true);
					lotNoT.setDisable(true);
					purchaseD.setDisable(false);
					purchaseT.setDisable(false);
				} else {
					purchaseD.setDisable(true);
					purchaseT.setDisable(true);
					lotNoB.setDisable(false);
					lotNoT.setDisable(false);
				}
			}
		});
	}

	private void setActions() {
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				logger.debug("Clear Fired");
				lotNoT.clear();
				medicineT.clear();
				locationT.clear();
				purchaseD.getEditor().clear();
				purchaseT.clear();
				expiryD.getEditor().clear();
				expiryT.clear();
				unitT.clear();
				costT.clear();
				barcodeT.clear();
				lotNoC.setSelected(false);
				locationId = null;
				medicineId = null;
				purchaseDate = null;
				expiryDate = null;
			}
		});
		medicineB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				logger.debug("Medicine Lookup fired");
				IntegerProperty num = new SimpleIntegerProperty();
				final MedicineMs returnDto = new MedicineMs();
				CommonLookups.medicineLookup(num, returnDto);

				num.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						medicineT.setText(returnDto.getName() == null ? "" : returnDto.getName());
						medicineId = returnDto.getId();

					}
				});
			}
		});

		locationB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				logger.debug("Location Lookup fired");
				final StorageAttribsMs returnDto = new StorageAttribsMs();
				IntegerProperty num = new SimpleIntegerProperty();
				CommonLookups.locationLookup(num, returnDto);

				num.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						locationT.setText(returnDto.getPlace() + " : " + returnDto.getRoomNo());
						locationId = returnDto.getId();
					}
				});
			}
		});

		lotNoB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				logger.debug("Lot No Lookup fired");
				IntegerProperty num = new SimpleIntegerProperty();
				final LotNoResponseDto returnDto = new LotNoResponseDto();
				CommonLookups.LotNoLookup(num, returnDto);

				num.addListener(new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						lotNoT.setText(returnDto.getLotNo() == null ? "" : returnDto.getLotNo());
						lotNo = returnDto.getLotNo();
						purchaseD.setValue(returnDto.getPurchaseDttm().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
						purchaseT.setText(String.valueOf(returnDto.getPurchaseDttm().getMinutes()));
					}
				});
			}
		});

		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (validateBeforeSave()) {
					GlobalInteractionClient<AddInventoryRequestDto> client = (GlobalInteractionClient<AddInventoryRequestDto>) ApplicationContextProvider
							.getBean("globalInteractionClient");
					AddInventoryRequestDto addInventoryRequestDto = new AddInventoryRequestDto();
					setValues(addInventoryRequestDto);
					client.write("inventory/addinventory/", HttpMethod.POST, addInventoryRequestDto, String.class);
					clear.fire();

				}

			}
		});
		expiryD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (expiryD.getValue() != null) {
					// expiryDatePicker.getEditor().setText(expiryDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
					expiryDate = Date.from(expiryD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				}
			}
		});

		purchaseD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (purchaseD.getValue() != null) {
					// purchaseDatePicker.getEditor().setText(purchaseDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
					purchaseDate = Date.from(purchaseD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

				}
			}
		});
	}

	private boolean validateBeforeSave() {
		if (lotNo == null && !lotNoC.isSelected()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.lotNo", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		if (medicineId == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.medicine", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		if (locationId == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.location", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		/*if (purchaseDate == null && lotNoC.isSelected()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.purchaseDttm", null, Locale.US));
			alert.showAndWait();
			return false;
		}*/
		if (lotNoC.isSelected() && purchaseT.getText() != null &&!purchaseT.getText().trim().isEmpty()) {
			try {
				Integer.parseInt(purchaseT.getText().trim());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
				alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.unit", null, Locale.US));
				alert.showAndWait();
				return false;
			}
			if(Integer.parseInt(purchaseT.getText().trim())>59||Integer.parseInt(purchaseT.getText().trim())<0)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
				alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.time", null, Locale.US));
			}
		}
		if (expiryDate == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.expiryDttm", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		
		if (expiryT.getText() != null&&!expiryT.getText().trim().isEmpty()) {
			try {
				Integer.parseInt(expiryT.getText().trim());
			} catch (NumberFormatException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
				alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.unit", null, Locale.US));
				alert.showAndWait();
				return false;
			}
			if(Integer.parseInt(expiryT.getText().trim())>59||Integer.parseInt(expiryT.getText().trim())<0)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
				alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.time", null, Locale.US));
			}
		}
		if (unitT.getText() == null || unitT.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.unit", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		try {
			Integer.parseInt(unitT.getText().trim());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.unit", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		if (costT.getText() == null || costT.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.mandatoryValidation", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.mandatory.error.costPerUnit", null, Locale.US));
			alert.showAndWait();
			return false;
		}

		try {
			Double.parseDouble(unitT.getText().trim());
		} catch (ParseException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ApplicationContextProvider.getMessage("label.global.correctFormat", null, Locale.US));
			alert.setContentText(ApplicationContextProvider.getMessage("text.correctFormat.error.costPerUnit", null, Locale.US));
			alert.showAndWait();
			return false;
		}
		return true;
	}

	private void setValues(AddInventoryRequestDto addInventoryRequestDto) {
		if(expiryT.getText()!=null&&!expiryT.getText().trim().isEmpty())
		{
			expiryDate.setMinutes(Integer.parseInt(expiryT.getText().trim()));
		}
		if(purchaseT.getText()!=null&&!purchaseT.getText().trim().isEmpty()&&lotNoC.isSelected())
		{
			purchaseDate.setMinutes(Integer.parseInt(purchaseT.getText().trim()));
		}
		addInventoryRequestDto.setExpiryDate(expiryDate);
		addInventoryRequestDto.setPurchaseDate(purchaseDate == null ? null : purchaseDate);
		addInventoryRequestDto.setLocationId(locationId);
		addInventoryRequestDto.setMedicineId(medicineId);
		addInventoryRequestDto.setLotNo(lotNo == null ? null : lotNo);
		if (lotNoC.isSelected()) {
			addInventoryRequestDto.setAutoGenerateLotNo(true);
		} else {
			addInventoryRequestDto.setAutoGenerateLotNo(false);
		}
		addInventoryRequestDto.setUnits(Integer.parseInt(unitT.getText().trim()));
		addInventoryRequestDto.setCostPerUnit(BigDecimal.valueOf(Double.parseDouble((costT.getText().trim()))));
		addInventoryRequestDto.setMvmtType("IAN");

	}
}
