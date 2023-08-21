/**
 * 
 */
package com.wibmo.validator;

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
}
