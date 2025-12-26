package com.mcquaids.actions.equipmentCategory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mcquaids.model.EquipmentCategory;
import com.mcquaids.model.lookup.CodeValues;
import com.mcquaids.service.EquipmentCategoryService;
import com.opensymphony.xwork2.ActionSupport;

	public class EquipmentCategoryBaseAction extends ActionSupport {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected CodeValues codeValues;
		
		protected EquipmentCategory equipmentCategory = new EquipmentCategory();

		protected Map<String, String> equipmentSubTypes = new HashMap<String, String>();		
		
		protected Integer equipmentType;
		
		protected String equipmentTypeText = "Equipment Category";

		protected String actionTypeText = "Edit";

		
		
		
		protected EquipmentCategoryService equipmentCategoryService = new EquipmentCategoryService();
        List<EquipmentCategory> equipmentCategories;

		protected Map<String, String> errors = new HashMap<>();	
		
		/**
		 * 
		 */
		public EquipmentCategoryBaseAction() {
			codeValues = new CodeValues();
		}

		
		/**
		 * 
		 */
		public void setEquipmentSubtypeSelect(Integer pEquipmentSubtype) {
			codeValues = new CodeValues();
			codeValues.setEquipmentSubTypes(pEquipmentSubtype );
		}

		
		
		/**
		 * @return the codeValues
		 */
		public CodeValues getCodeValues() {
			return codeValues;
		}

		/**
		 * @param codeValues the codeValues to set
		 */
		public void setCodeValues(CodeValues codeValues) {
			this.codeValues = codeValues;
		}

		/**
		 * @param errors the errors to set
		 */
		public void setErrors(Map<String, String> errors) {
			this.errors = errors;
		}



		public Map<String, String> getErrors() {
			return errors;
		}




		/**
		 * @return the equipmentCategory
		 */
		public EquipmentCategory getEquipmentCategory() {
			return equipmentCategory;
		}


		/**
		 * @param equipmentCategory the equipmentCategory to set
		 */
		public void setEquipmentCategory(EquipmentCategory equipmentCategory) {
			this.equipmentCategory = equipmentCategory;
		}


		/**
		 * @return the equipmentCategories
		 */
		public List<EquipmentCategory> getEquipmentCategories() {
			return equipmentCategories;
		}


		/**
		 * @param equipmentCategories the equipmentCategories to set
		 */
		public void setEquipmentCategories(List<EquipmentCategory> equipmentCategories) {
			this.equipmentCategories = equipmentCategories;
		}


		/**
		 * @return the equipmentType
		 */
		public Integer getEquipmentType() {
			return equipmentType;
		}


		/**
		 * @param equipmentType the equipmentType to set
		 */
		public void setEquipmentType(Integer equipmentType) {
			this.equipmentType = equipmentType;
		}


		/**
		 * @return the equipmentSubTypes
		 */
		public Map<String, String> getEquipmentSubTypes() {
			return equipmentSubTypes;
		}


		/**
		 * @param equipmentSubTypes the equipmentSubTypes to set
		 */
		public void setEquipmentSubTypes(Map<String, String> equipmentSubTypes) {
			this.equipmentSubTypes = equipmentSubTypes;
		}




		/**
		 * @return the equipmentTypeText
		 */
		public String getEquipmentTypeText() {
			return equipmentTypeText;
		}


		/**
		 * @param equipmentTypeText the equipmentTypeText to set
		 */
		public void setEquipmentTypeText(String equipmentType) {
			this.equipmentTypeText = equipmentType;
		}


		/**
		 * @return the actionTypeText
		 */
		public String getActionTypeText() {
			return actionTypeText;
		}


		/**
		 * @param actionTypeText the actionTypeText to set
		 */
		public void setActionTypeText(String actionTypeText) {
			this.actionTypeText = actionTypeText;
		}


		protected void setJSPTitle(String titlePrefix) {
			switch(this.equipmentCategory.getEquipmentType() ) {
			  case 1002:
				  equipmentTypeText= titlePrefix + " Trailer";
			    break;
			  case 1003:
				  equipmentTypeText= titlePrefix + " Flatbed";
			    break;
			  case 1004:
				  equipmentTypeText= titlePrefix + " Container";
			    break;
			  case 1005:
				  equipmentTypeText= titlePrefix + " Forklift";
			    break;
			  default:
				  equipmentTypeText= titlePrefix + " Equipment";
			}

			
		}





		

	}
