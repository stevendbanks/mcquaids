package com.mcquaids.model;

import org.json.JSONObject;

import com.mcquaids.utils.JsonUtils;

public class ShippingContainer extends Equipment {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 1L;
	
	// Used for Searching, but seems to be bad idea as EquipmentType is not actually in this class.
	public static final String EquipmentType = Constants.CONTAINER_STRING;
	
	private String floor;
    private int size;
    private String fuelType;
    private String  exteriorCondition;  // Will be a lookup value type 1007.

	/**
	 * 
	 */
	public ShippingContainer() {
		super();
	}

	// Constructor that takes an Equipment object
    public ShippingContainer(Equipment equipment) {
        super(equipment);
//        JsonUtils.setPropertiesFromJson(this, super.getProperties());

    }

	public String setFieldsToJson(ShippingContainer pContainer) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("floor", pContainer.floor);
		jsonObject.put("size", pContainer.size);
		jsonObject.put("fuelType", pContainer.fuelType);
		jsonObject.put("exteriorCondition", pContainer.exteriorCondition);
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


	/**
	 * @return the exteriorCondition
	 */
	public String getExteriorCondition() {
		return exteriorCondition;
	}


	/**
	 * @param exteriorCondition the exteriorCondition to set
	 */
	public void setExteriorCondition(String exteriorCondition) {
		this.exteriorCondition = exteriorCondition;
	}
	
	
	

}
