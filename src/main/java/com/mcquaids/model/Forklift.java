package com.mcquaids.model;

import org.json.JSONObject;

import com.mcquaids.model.interfaces.Iforklift;
import com.mcquaids.utils.JsonUtils;

public class Forklift extends Equipment implements Iforklift {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Used for Searching, but seems to be bad idea as EquipmentType is not actually in this class.
	public static final String EquipmentType = Constants.FORKLIFT_STRING;
	
	private String floor;
    private int size;
    private String fuelType;

	/**
	 * 
	 */
	public Forklift() {
		super();
	}


    // Constructor that takes an Equipment object
    public Forklift(Equipment equipment) {
        super(equipment);
          //  Copy the json properties into the trailer variables.
//          JsonUtils.setPropertiesFromJson(this, super.getProperties());

      }

	public String setFieldsToJson(Forklift pForklift) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("floor", pForklift.floor);
		jsonObject.put("size", pForklift.size);
		jsonObject.put("fuelType", pForklift.fuelType);
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
