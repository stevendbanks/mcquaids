/**
 * 
 */
package com.mcquaids.model.lookup;

import java.io.Serializable;

/**
 * 
 */
public class CodeValue  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codeValue;
	private String englishDescription;

	
	
	/**
	 * 
	 */
	public CodeValue() {
	}
	/**
	 * @return the codeValue
	 */
	public String getCodeValue() {
		return codeValue;
	}
	/**
	 * @param codeValue the codeValue to set
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	/**
	 * @return the englishDescription
	 */
	public String getEnglishDescription() {
		return englishDescription;
	}
	/**
	 * @param englishDescription the englishDescription to set
	 */
	public void setEnglishDescription(String englishDescription) {
		this.englishDescription = englishDescription;
	}





}
