/**
 * 
 */
package com.wibmo.validator;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Interface to validate a user
 */
@Component
public interface ValidatorInterface {
	
	/**
	 * To validate a user using email
	 * @param email
	 * @return
	 */
	public boolean emailValidator(String email);
	public List<List<Integer>> sortByCoursePref(List<List<Integer>> list);
	public List<Boolean> courseRegistrationValidator();
}
