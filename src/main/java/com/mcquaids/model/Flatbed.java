package com.mcquaids.model;

import org.json.JSONObject;

import com.mcquaids.utils.JsonUtils;

public class Flatbed extends Equipment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Used for Searching, but seems to be bad idea as EquipmentType is not actually in this class.
	public static final String EquipmentType = Constants.FLATBED_STRING;
	
	private String floor;
	private String manufacturer;
    private int size;
    private String fuelType;

	/**
	 * 
	 */
	public Flatbed() {
		super();
	}


    // Constructor that takes an Equipment object
    public Flatbed(Equipment equipment) {
        super(equipment);
          //  Copy the json properties into the trailer variables.
//          JsonUtils.setPropertiesFromJson(this, super.getProperties());

      }

	public String setFieldsToJson(Flatbed pFlatbed) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("floor", pFlatbed.floor);
		jsonObject.put("size", pFlatbed.size);
		jsonObject.put("fuelType", pFlatbed.fuelType);
		return jsonObject.toString();
	}	
	
	
	
	
	/**
	 * @return the floor
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * @param floor the floor to set
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the fuelType
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * @param fuelType the fuelType to set
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}


	/**
	 * @return the equipmenttype
	 */
	public static String getEquipmenttype() {
		return EquipmentType;
	}
	
	
	

}
