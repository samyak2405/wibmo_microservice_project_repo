package com.wibmo.validator;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ClientValidatorImpl {
	Scanner scan = new Scanner(System.in);
	public String passwordValidator() {
		while(true) {
			System.out.print("\nEnter New Password: ");
    		String passwordOne = scan.next();
    		System.out.print("\nEnter Password Again: ");
    		String passwordAgain = scan.next();
    		if(passwordOne.equals(passwordAgain))
    		   {
    			return passwordOne;
    		   }
    		else
    			System.out.println("Password does not match");
		}
	}
}
