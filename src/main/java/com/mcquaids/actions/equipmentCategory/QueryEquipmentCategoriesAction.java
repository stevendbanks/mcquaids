package com.mcquaids.actions.equipmentCategory;

public class QueryEquipmentCategoriesAction extends EquipmentCategoryBaseAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
        equipmentCategories = equipmentCategoryService.queryEquipmentCategories();
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
