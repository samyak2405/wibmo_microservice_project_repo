/**
 * 
 */
package com.wibmo.validator;

import java.util.List;

/**
 * 
 */
public interface ValidatorInterface {
	public boolean emailValidator(String email);
	public List<List<Integer>> sortByCoursePref(List<List<Integer>> list);
	public void courseRegistrationValidator();
	public void assignCourseValidator() ;
}
