package com.wibmo.client;

import java.util.Scanner;

import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;
import com.wibmo.constant.NotificationConstants;


public class CRSPaymentMenu {
	Scanner scan=new Scanner(System.in);
	private PaymentOperation payment=new PaymentOperationImpl() ;
	private NotificationOperation notification=new NotificationOperationImpl();
	
	private boolean status=false;
	
	public void online() {		
		System.out.println();

        System.out.println("===================================================================================");
		System.out.println("1.UPI");
    	  System.out.println("2.CARDS");
    	  System.out.println("3.WALLET"); 
    	  System.out.println("4.EXIT");
    	  System.out.println();

          System.out.println("===================================================================================");
        	  System.out.println("Enter Your Choice");
        	  int choice1=scan.nextInt();
        	  switch(choice1) {
        	  case 1:
        		  status=payment.UPI();
        	  break;
        	  case 2:
        		  status=payment.cards();
            	  break;
        	  case 3:
        		  status=payment.wallet();
            	  break;
        	  case 4:
            	  break;
        	  }
	}
	
	public void payfee(int studentId) 
	{
		PaymentOperation payment=new PaymentOperationImpl() ;
		System.out.println();

        System.out.println("===================================================================================");
	  	  System.out.println("1.OFFLINE");
	  	  System.out.println("2.ONLINE");
	  	  System.out.println("3.EXIT");
	  	System.out.println();

        System.out.println("===================================================================================");
	  	  boolean flag2=true;
	  	  
	  	  System.out.println("Enter Your Choice");
	  	  int choice=scan.nextInt();
	  	  switch(choice) {
	  	  case 1:
	  		  status=payment.offline();
	  	  break;
	  	  case 2:
	  		  online();
		   case 3:
	  		     flag2=false;
	  	   break;
	  	  }	  	  
	  	  if(status==true)
	  		  notification.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, studentId);
	  	  
	  	  else
	  	  {
				  notification.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, studentId);
	  	  }
	}

}
