package com.mcquaids.actions.equipmentCategory;

public class UpdateEquipmentCategoryAction extends EquipmentCategoryBaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
        equipmentCategoryService.updateEquipmentCategory(equipmentCategory);
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
