/**
 * 
 */
package com.mcquaids.model;

/**
 * 
 */
public class EquipmentQueryDTO  extends Equipment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
 
	private String equipmentTypeText;
	private String availabilityStatusText;
	private String conditionStatusText;
	private String maintenanceStatusText;
	private String cleaningStatusText;
//	private String leaseStatusText;
    private String bookingStatusCodeText;
    private String leasedEquipmentNotes;
	/**
	 * @return the equipmentTypeText
	 */
	public String getEquipmentTypeText() {
		return equipmentTypeText;
	}
	/**
	 * @param equipmentTypeText the equipmentTypeText to set
	 */
	public void setEquipmentTypeText(String equipmentTypeText) {
		this.equipmentTypeText = equipmentTypeText;
	}
	/**
	 * @return the availabilityStatusText
	 */
	public String getAvailabilityStatusText() {
		return availabilityStatusText;
	}
	/**
	 * @param availabilityStatusText the availabilityStatusText to set
	 */
	public void setAvailabilityStatusText(String availabilityStatusText) {
		this.availabilityStatusText = availabilityStatusText;
	}
	/**
	 * @return the conditionStatusText
	 */
	public String getConditionStatusText() {
		return conditionStatusText;
	}
	/**
	 * @param conditionStatusText the conditionStatusText to set
	 */
	public void setConditionStatusText(String conditionStatusText) {
		this.conditionStatusText = conditionStatusText;
	}
	/**
	 * @return the maintenanceStatusText
	 */
	public String getMaintenanceStatusText() {
		return maintenanceStatusText;
	}
	/**
	 * @param maintenanceStatusText the maintenanceStatusText to set
	 */
	public void setMaintenanceStatusText(String maintenanceStatusText) {
		this.maintenanceStatusText = maintenanceStatusText;
	}
	/**
	 * @return the cleaningStatusTest
	 */
	public String getCleaningStatusText() {
		return cleaningStatusText;
	}
	/**
	 * @param cleaningStatusTest the cleaningStatusTest to set
	 */
	public void setCleaningStatusText(String cleaningStatusTest) {
		this.cleaningStatusText = cleaningStatusTest;
	}
//	/**
//	 * @return the leaseStatusText
//	 */
//	public String getLeaseStatusText() {
//		return leaseStatusText;
//	}
//	/**
//	 * @param leaseStatusText the leaseStatusText to set
//	 */
//	public void setLeaseStatusText(String leaseStatusText) {
//		this.leaseStatusText = leaseStatusText;
//	}
	/**
	 * @return the bookingStatusCodeText
	 */
	public String getBookingStatusCodeText() {
		return bookingStatusCodeText;
	}
	/**
	 * @param bookingStatusCodeText the bookingStatusCodeText to set
	 */
	public void setBookingStatusCodeText(String bookingStatusCodeText) {
		this.bookingStatusCodeText = bookingStatusCodeText;
	}
	/**
	 * @return the leasedEquipmentNotes
	 */
	public String getLeasedEquipmentNotes() {
		return leasedEquipmentNotes;
	}
	/**
	 * @param leasedEquipmentNotes the leasedEquipmentNotes to set
	 */
	public void setLeasedEquipmentNotes(String leasedEquipmentNotes) {
		this.leasedEquipmentNotes = leasedEquipmentNotes;
	}
}
