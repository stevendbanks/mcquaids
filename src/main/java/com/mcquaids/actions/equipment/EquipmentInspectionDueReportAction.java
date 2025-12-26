package com.mcquaids.actions.equipment;

import java.util.List;
import java.util.Map;

import com.mcquaids.service.EquipmentService;
import com.opensymphony.xwork2.ActionSupport;

public class EquipmentInspectionDueReportAction extends ActionSupport {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EquipmentService equipmentService = new EquipmentService();

    private List<Map<String, Object>> report;
    
    // Put in the number of days to check if the asset's inspection is coming up. 
    private int daysToExpiry = 30;

    public String execute() {
    	try {
        report = equipmentService.getEquipmentInspectionReport(daysToExpiry);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
        return SUCCESS;
    }

    public List<Map<String, Object>> getReport() {
        return report;
    }

	/**
	 * @return the daysToExpiry
	 */
	public int getDaysToExpiry() {
		return daysToExpiry;
	}

	/**
	 * @param daysToExpiry the daysToExpiry to set
	 */
	public void setDaysToExpiry(int daysToExpiry) {
		this.daysToExpiry = daysToExpiry;
	}
}
