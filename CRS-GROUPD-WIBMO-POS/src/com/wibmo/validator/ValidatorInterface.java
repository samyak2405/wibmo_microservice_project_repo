/**
 * 
 */
package com.wibmo.validator;

import java.util.List;

/**
 * Interface to validate a user
 */
public interface ValidatorInterface {
	
	/**
	 * To validate a user using email
	 * @param email
	 * @return
	 */
	public boolean emailValidator(String email);
	public List<List<Integer>> sortByCoursePref(List<List<Integer>> list);
	public void courseRegistrationValidator();
	public void assignCourseValidator() ;
}
