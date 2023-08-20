package com.wibmo.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.wibmo.business.AdminOperationImpl;
import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;
import com.wibmo.constant.NotificationConstants;


public class CRSPaymentMenu {
	Scanner scan=new Scanner(System.in);
	private PaymentOperation payment=new PaymentOperationImpl() ;
	private NotificationOperation notification=new NotificationOperationImpl();
	static Logger log = Logger.getLogger(AdminOperationImpl.class.getName());
	
	private boolean status=false;
	
	public void online() {		
		log.info("1.UPI");
    	  log.info("2.CARDS");
    	  log.info("3.WALLET"); 
    	  log.info("4.EXIT");
        	  log.info("Enter Your Choice");
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
	  	  log.info("1.OFFLINE");
	  	  log.info("2.ONLINE");
	  	  log.info("3.EXIT");
	  	  boolean flag2=true;
	  	  
	  	  log.info("Enter Your Choice");
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
