package com.wibmo.client;

import java.util.Scanner;

import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;


public class CRSPaymentMenu {
	Scanner scan=new Scanner(System.in);
	public void online() {
		PaymentOperation payment=new PaymentOperationImpl() ;
		System.out.println("1.UPI");
    	  System.out.println("2.CARDS");
    	  System.out.println("3.WALLET"); 
    	  System.out.println("4.EXIT");
    	  boolean flag3=false;
    	  while(true) {
        	  System.out.println("Enter Your Choice");
        	  int choice1=scan.nextInt();
        	  switch(choice1) {
        	  case 1:
        		  payment.UPI();
        	  break;
        	  case 2:
        		  payment.cards();
            	  break;
        	  case 3:
        		  payment.wallet();
            	  break;
        	  case 4:
        		  flag3=true;
            	  break;
        	  }
        	  if(flag3)
        		  break;
        	  }
	}
	
	
	public void payfee() {
		PaymentOperation payment=new PaymentOperationImpl() ;
	  	  System.out.println("1.OFFLINE");
	  	  System.out.println("2.ONLINE");
	  	  System.out.println("3.EXIT");
	  	  boolean flag2=true;
	  	  while(true) {
	  	  System.out.println("Enter Your Choice");
	  	  int choice=scan.nextInt();
	  	  switch(choice) {
	  	  case 1:
	  		  payment.offline();
	  	  break;
	  	  case 2:
	  		  online();
		   case 3:
	  		     flag2=false;
	  	   break;
	  	  }
	  	  if(flag2)
	  		  break;
	  	  }
		}

}
