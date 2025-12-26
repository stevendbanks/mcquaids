/**
 * 
 */
package com.mcquaids.model;

import java.util.Date;

public class Lease {
    private String customerID;
    private String leaseID;
    private Date leaseSignDate;
    private Date leaseTerminationDate;
    private String leaseTerminationReasonCode;
    private Date leaseStartDate;
    private Date leaseEndDate;
    private String leaseStatusCode;
    private String instructions;

    // Getters and Setters
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String pCustomerID) {
        this.customerID = pCustomerID;
    }

    public String getLeaseID() {
        return leaseID;
    }

    public void setLeaseID(String pLeaseID) {
        this.leaseID = pLeaseID;
    }

    public Date getLeaseSignDate() {
        return leaseSignDate;
    }

    public void setLeaseSignDate(Date leaseSignDate) {
        this.leaseSignDate = leaseSignDate;
    }

    public Date getLeaseTerminationDate() {
        return leaseTerminationDate;
    }

    public void setLeaseTerminationDate(Date leaseTerminationDate) {
        this.leaseTerminationDate = leaseTerminationDate;
    }

    public String getLeaseTerminationReasonCode() {
        return leaseTerminationReasonCode;
    }

    public void setLeaseTerminationReasonCode(String leaseTerminationReasonCode) {
        this.leaseTerminationReasonCode = leaseTerminationReasonCode;
    }

    public Date getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getLeaseStatusCode() {
        return leaseStatusCode;
    }

    public void setLeaseStatusCode(String leaseStatus) {
        this.leaseStatusCode = leaseStatus;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}

