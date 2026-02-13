package com.mcquaids.model;

public class Constants {

	public static final String TRAILER_STRING = "Trailer";
	public static final String FLATBED_STRING = "Forklift";
	public static final String CONTAINER_STRING = "Container";
	public static final String FORKLIFT_STRING = "Forklift";

	public static final int TRAILER = 2;
	public static final int FLATBED = 3;
	public static final int CONTAINER = 4;
	public static final int FORKLIFT = 5;

	public static final int EQUIPMENT_AVAILABILTY_CODE_TYPES = 1008;  // Code type for Equipment Availability values

	
	
//	SELECT CONCAT('public static String ', UPPER(CONCAT(ct.EnglishDescription, '_', cv.EnglishDescription)), ' = ',  cv.CodeValue) AS DESCRIPTION
//	FROM codetype AS ct
//	INNER JOIN codevalue AS cv ON ct.ID = cv.codetype;

	
	//  Equipment subTypes.  Except for provinces, 
	//  the subtype element is a EquipmentType +  "-" +  EquipmentSubType 
	public static String PROVINCE_PRINCE_EDWARD_ISLAND = "PE";
	public static String PROVINCE_NEW_BRUNSWICK = "NB";
	public static String PROVINCE_NOVA_SCOTIA = "NS";
	public static String PROVINCE_NEWFOUNDLAND_AND_LABRADOR = "NL";
	public static String PROVINCE_QUEBEC = "QC";
	public static String PROVINCE_ONTARIO = "ON";

	public static String TRAILER_GENERAL = "1002-01";
	public static final String TRAILER_FURNITURE = "1002-02";
	public static String TRAILER_OFFICE = "1002-03";
	public static String TRAILER_CONSTRUCTION = "1002-04";
	public static String TRAILER_EOL = "1002-05";

	public static String FLATBED_LOWBOY = "1003-01";
	public static String FLATBED_QUICKLOADS = "1003-02";
	public static String FLATBED_STANDARD = "1003-03";
	public static String FLATBED_LANDOLL = "1003-04";
	
	public static String CONTAINER_STANDARD = "1004-01";
	public static String CONTAINER_HIGH_CUBE = "1004-02";
	public static String CONTAINER_REEFER = "1004-03";
	public static String CONTAINER_HIGH_CUBE_REEFER = "1004-04";

	public static String CONTAINER_TYPE_NEW = "1019-01";
	public static String CONTAINER_TYPE_USED = "1019-02";
	public static String CONTAINER_TYPE_ICCL = "1019-03";
	public static String CONTAINER_TYPE_EOL = "1019-04";

	public static String FORKLIFT_48V = "1005-05";
	public static String FORKLIFT_24V = "1005-04";
	public static String FORKLIFT_36V = "1005-03";
	public static String FORKLIFT_LPG = "1005-02";
	public static String FORKLIFT_GAS = "1005-01";
	public static String FORKLIFT_DIESEL = "1005-06";

	public static String EQUIPMENT_AVAILABLE_TO_LEASE = "1008-01";
	public static String EQUIPMENT_AVAILABILITY_RENTED = "1008-02";
	
	public static String SAVE_ACTION_TYPE_ADD_NEW =   "AddNew";
	public static String SAVE_ACTION_TYPE_SAVE =   "Save";


}
