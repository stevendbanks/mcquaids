package com.mcquaids.service;

import java.util.List;

import com.mcquaids.dao.CodeValueDAO;
import com.mcquaids.dao.DaoDataSource;
import com.mcquaids.model.lookup.CodeValue;

public class CodeValueService extends DaoDataSource {

	private CodeValueDAO  codeValueDAO;

	
	public CodeValueService() {
		super();
        this.codeValueDAO = new CodeValueDAO(DaoDataSource.jdbcTemplate);

	}
	
	
	public List<CodeValue> queryLookupTable(String pCodeTypeTableName) {

	      return codeValueDAO.queryLookupTable( pCodeTypeTableName);
	}
	
	
	public List<CodeValue> findCodeValues(String pCodeTypeTableEnglishDescription) {

	      return codeValueDAO.queryCodeValues(pCodeTypeTableEnglishDescription);
	}
	
	public List<CodeValue> findCodeTypes(String pIsTypeOfEquipment) {

	      return codeValueDAO.findCodeTableTypes(pIsTypeOfEquipment);
	}

	
	public CodeValue findCodeValue(int pCodeType, String pCodeValue) {

	      return codeValueDAO.findCodeValue(pCodeType, pCodeValue); 
	}
	
	


}
 