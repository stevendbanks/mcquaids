package com.mcquaids.model;

import java.util.Date;

public class LeasedEquipment {
    private String leaseID;
    private String equipmentNumber;
    private Date dateAddedToLease;
    private Date dateRemovedFromLease;
    private String notes;

    // Getters and Setters
    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String pLeaseID) {
        this.leaseID = pLeaseID;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public Date getDateAddedToLease() {
        return dateAddedToLease;
    }

    public void setDateAddedToLease(Date dateAddedToLease) {
        this.dateAddedToLease = dateAddedToLease;
    }

    public Date getDateRemovedFromLease() {
        return dateRemovedFromLease;
    }

    public void setDateRemovedFromLease(Date dateRemovedFromLease) {
        this.dateRemovedFromLease = dateRemovedFromLease;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

	@Override
	public String toString() {
		return "LeasedEquipment [leaseID=" + leaseID + ", equipmentNumber=" + equipmentNumber + ", dateAddedToLease="
				+ dateAddedToLease + ", dateRemovedFromLease=" + dateRemovedFromLease + ", notes=" + notes + "]";
	}
    
    
}
