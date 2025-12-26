package com.mcquaids.actions.equipmentCategory;

public class SaveNewEquipmentCategoryAction extends EquipmentCategoryBaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
        equipmentCategoryService.saveNewEquipmentCategory(equipmentCategory);
        errors = equipmentCategoryService.getErrors();
        if (errors != null && !errors.isEmpty()) {
            for (String error : errors.values()) {
                addActionError(error);
            }
            return INPUT;
        }
        return SUCCESS;
    }
}
