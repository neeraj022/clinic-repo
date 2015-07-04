package com.neeraj.clinic.server.queries;

public class Queries {
	private final String inv_MainScreenQuerySQL = "select med.name,med.description,med.drug_weight,inv.purchase_dttm,inv.expiry_dttm,inv.unit,inv.cost,  "
			+ " strg.place,strg.room_no,strg.description from inventory inv,storage_attribs_ms strg, medicine_ms med"
			+ " where med.id=inv.medicine_id and inv.storage_id=strg.id ";
	public static final String inv_MainScreenQuery = "select med.name as medName, med.description as medDesc, med.drugWeight as medDrugWt, "
			+ " inv.purchaseDttm as purchaseDttm, inv.expiryDttm as expiryDttm, inv.unit as unit,inv.cost as cost, inv.unit*inv.cost as totalCost, "
			+ " strg.place as place, strg.roomNo as roomNo, strg.description as locDesc from Inventory inv,StorageAttribsMs strg, MedicineMs med "
			+" where med.id=inv.medicineId and inv.storageId=strg.id ";
	
	public final String inv_LotNoQuerySQL = "select distinct inv.lot_No,inv.purchase_Dttm from Inventory inv order by inv.purchase_Dttm desc";
	public static final String inv_LotNoQuery = "select distinct inv.lotNo as lotNo,inv.purchaseDttm as purchaseDttm from Inventory inv order by inv.purchaseDttm desc";

}
