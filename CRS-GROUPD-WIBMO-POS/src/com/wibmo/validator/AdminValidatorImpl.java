/**
 * 
 */
package com.wibmo.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * To validate admin details
 */
public class AdminValidatorImpl implements ValidatorInterface{

	@Override
	/**
	 * To validate admin using email
	 */
	public boolean emailValidator(String email) {
		// TODO Auto-generated method stub
		String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
}
