package com.mcquaids.model;

import org.json.JSONObject;

import com.mcquaids.model.interfaces.ITrailer;
import com.mcquaids.utils.JsonUtils;

public class Trailer extends Equipment implements ITrailer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String EquipmentType = Constants.TRAILER_STRING;
	
	private int length;
	private String doorLocation;
	private String doorType;
	private String floor;
	private String axel;
	private String colour;
	private boolean insulated;
	private boolean tieDown;


	/**
	 * 
	 */
	public Trailer() {
		super();
	}


    // Constructor that takes an Equipment object
    public Trailer(Equipment equipment) {
        super(equipment);        
        //  Copy the json properties into the trailer variables.
//        JsonUtils.setPropertiesFromJson(this, super.getProperties());

    }

	public String setFieldsToJson(Trailer pTrailer) {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("length", pTrailer.length);
//		jsonObject.put("doorLocation", pTrailer.doorLocation);
//		jsonObject.put("doorType", pTrailer.doorType);
//		jsonObject.put("floor", pTrailer.floor);
//		jsonObject.put("axel", pTrailer.axel);
//		jsonObject.put("colour", pTrailer.colour);
//		jsonObject.put("insulated", pTrailer.insulated);
//		jsonObject.put("tieDown", pTrailer.tieDown);
//
//		return jsonObject.toString();
		
		return null;
	}
		
	/**
	 * @return the doorLocation
	 */
	public String getDoorLocation() {
		return doorLocation;
	}

	/**
	 * @param doorLocation the doorLocation to set
	 */
	public void setDoorLocation(String doorLocation) {
		this.doorLocation = doorLocation;
	}

	/**
	 * @return the doorType
	 */
	public String getDoorType() {
		return doorType;
	}


	/**
	 * @param doorType the doorType to set
	 */
	public void setDoorType(String doorType) {
		this.doorType = doorType;
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
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the equipmenttype
	 */
	public static String getEquipmenttype() {
		return EquipmentType;
	}



	/**
	 * @return the axel
	 */
	public String getAxel() {
		return axel;
	}

	/**
	 * @param axel the axel to set
	 */
	public void setAxel(String axel) {
		this.axel = axel;
	}

	/**
	 * @return the insulated
	 */
	public boolean isInsulated() {
		return insulated;
	}

	/**
	 * @param insulated the insulated to set
	 */
	public void setInsulated(boolean insulated) {
		this.insulated = insulated;
	}

	/**
	 * @return the tieDown
	 */
	public boolean isTieDown() {
		return tieDown;
	}

	/**
	 * @param tieDown the tieDown to set
	 */
	public void setTieDown(boolean tieDown) {
		this.tieDown = tieDown;
	}








	
	
	

}
