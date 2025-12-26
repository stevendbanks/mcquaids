package com.mcquaids.actions.equipment;

import java.util.List;
import java.util.Map;

import com.mcquaids.service.EquipmentService;
import com.opensymphony.xwork2.ActionSupport;

public class EquipmentAvailableReportAction extends ActionSupport {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EquipmentService equipmentService = new EquipmentService();

    private List<Map<String, Object>> report;

    public String execute() {
    	try {
        report = equipmentService.getEquipmentReport();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
        return SUCCESS;
    }

    public List<Map<String, Object>> getReport() {
        return report;
    }
}
