package com.mcquaids.actions.equipmentCategory;

public class EditEquipmentCategoryAction extends EquipmentCategoryBaseAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int categoryId;

    public String execute() {
        equipmentCategory = equipmentCategoryService.edit(categoryId);
        errors = equipmentCategoryService.getErrors();
        if (errors != null && !errors.isEmpty()) {
            for (String error : errors.values()) {
                addActionError(error);
            }
            return INPUT;
        }
        return SUCCESS;
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
