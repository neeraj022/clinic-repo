package com.neeraj.clinic.client.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.IntegerProperty;
import javafx.stage.Stage;

import com.neeraj.clinic.model.gen.MedicineMs;
import com.neeraj.clinic.model.gen.StorageAttribsMs;
import com.neeraj.clinic.model.responsedtos.LotNoResponseDto;
import com.neeraj.core.generics.GlobalInteractionClient;
import com.neeraj.core.generics.MyResponse;
import com.neeraj.core.spring.ApplicationContextProvider;
import com.neeraj.core.ui.CommonLookup;
import com.neeraj.core.ui.QueryBuilder;

public class CommonLookups {
	
	public static void medicineLookup( IntegerProperty num,MedicineMs returnDto)
	{
		// set Column Names and field Names of table to be set
		List<String> columnNames = new ArrayList<String>();
		List<String> fieldNames = new ArrayList<String>();
		columnNames.add("Name");
		columnNames.add("Type");
		columnNames.add("Drug Weight");
		columnNames.add("Category");
		columnNames.add("Description");
		fieldNames.add("name");
		fieldNames.add("type");
		fieldNames.add("drugWeight");
		fieldNames.add("category");
		fieldNames.add("description");

		// Get Data
		GlobalInteractionClient<?> client = (GlobalInteractionClient<?>) ApplicationContextProvider.getBean("globalInteractionClient");
		QueryBuilder builder = new QueryBuilder();
		builder.addCondition("delFlg", "N");
		MyResponse a = client.read("medicinems/", builder, MedicineMs.class);

		// Launch lookup
		CommonLookup commonLookup = new CommonLookup(new Stage(), columnNames, fieldNames, a.getData(), MedicineMs.class, returnDto, num);
		commonLookup.startLookup("Medicine Lookup");
	
	}
	
	public static void locationLookup( IntegerProperty num,StorageAttribsMs returnDto)
	{
		// set Column Names and field Names of table to be set
		List<String> columnNames = new ArrayList<String>();
		List<String> fieldNames = new ArrayList<String>();
		columnNames.add(ApplicationContextProvider.getMessage("label.locationLookup.place", null, Locale.US));
		columnNames.add(ApplicationContextProvider.getMessage("label.locationLookup.room", null, Locale.US));
		columnNames.add(ApplicationContextProvider.getMessage("label.locationLookup.description", null, Locale.US));
		columnNames.add(ApplicationContextProvider.getMessage("label.locationLookup.rowNo", null, Locale.US));
		columnNames.add(ApplicationContextProvider.getMessage("label.locationLookup.columnNo", null, Locale.US));
		fieldNames.add("place");
		fieldNames.add("roomNo");
		fieldNames.add("description");
		fieldNames.add("rowNo");
		fieldNames.add("columnNo");

		// Get Data
		GlobalInteractionClient<?> client = (GlobalInteractionClient<?>) ApplicationContextProvider.getBean("globalInteractionClient");
		QueryBuilder builder = new QueryBuilder();
		builder.addCondition("delFlg", "N");
		MyResponse a = client.read("storageattribsms/", builder, StorageAttribsMs.class);

		// Launch lookup
		CommonLookup commonLookup = new CommonLookup(new Stage(), columnNames, fieldNames, a.getData(), StorageAttribsMs.class, returnDto,
				num);
		commonLookup.startLookup("Location Lookup");
	}
	
	
	public static void LotNoLookup( IntegerProperty num,LotNoResponseDto returnDto)
	{
		// set Column Names and field Names of table to be set
		List<String> columnNames = new ArrayList<String>();
		List<String> fieldNames = new ArrayList<String>();
		columnNames.add("Lot No");
		columnNames.add("Purchase Date");
		fieldNames.add("lotNo");
		fieldNames.add("purchaseDttm");

		// Get Data
		GlobalInteractionClient<?> client = (GlobalInteractionClient<?>) ApplicationContextProvider.getBean("globalInteractionClient");
		QueryBuilder builder = new QueryBuilder();
		builder.addCondition("delFlg", "N");
		MyResponse a = client.read("inventory/getlotNo/", builder, LotNoResponseDto.class);

		// Launch lookup
		CommonLookup commonLookup = new CommonLookup(new Stage(), columnNames, fieldNames, a.getData(), LotNoResponseDto.class, returnDto, num);
		commonLookup.startLookup("Lot No Lookup");
	
	}

}
