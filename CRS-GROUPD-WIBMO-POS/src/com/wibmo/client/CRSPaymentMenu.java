package com.wibmo.client;

import java.util.Scanner;

import com.wibmo.bean.Payment;
import com.wibmo.business.NotificationOperation;
import com.wibmo.business.NotificationOperationImpl;
import com.wibmo.business.PaymentOperation;
import com.wibmo.business.PaymentOperationImpl;
import com.wibmo.constant.NotificationConstants;



/**
 * Payment menu class.
 */

public class CRSPaymentMenu {
	Scanner scan=new Scanner(System.in);
	private PaymentOperation payment=new PaymentOperationImpl() ;
	private NotificationOperation notification=new NotificationOperationImpl();
	private Payment paymentBean=null;
	
	

	private boolean status=false;
	
	/**
	 * To display online Payment menu.
	 * @param studentId
	 * @return True if payment is successful else returns false.
	 */
	public Payment online(long studentId) {		
		System.out.println("1.UPI");
    	  System.out.println("2.CARDS");
    	  System.out.println("3.WALLET"); 
    	  System.out.println("4.EXIT");
        	  System.out.println("Enter Your Choice");
        	  int choice1=scan.nextInt();
        	  switch(choice1) {
        	  case 1:
        		  status=payment.UPI(studentId);
        		  paymentBean=payment.recordPayment(studentId, status);
        	  break;
        	  case 2:
        		  status=payment.cards(studentId);
        		  paymentBean=payment.recordPayment(studentId, status);
            	  break;
        	  case 3:
        		  status=payment.wallet(studentId);
        		  paymentBean=payment.recordPayment(studentId, status);
            	  break;
        	  case 4:
            	  break;
        	  }
        	  return paymentBean;
	}
	
	/**
	 * To Display the payment menu.
	 * @param studentId
	 */
	public void payfee(long studentId) 
	{
		PaymentOperation payment=new PaymentOperationImpl() ;
		  System.out.println("Amout to be paid is: "+payment.getAmount(studentId));
	  	  System.out.println("1.OFFLINE");
	  	  System.out.println("2.ONLINE");
	  	  System.out.println("3.EXIT");
	  	  boolean flag2=true;
	  	  
	  	  System.out.println("Enter Your Choice");
	  	  int choice=scan.nextInt();
	  	  switch(choice) {
	  	  case 1:
	  		  status=payment.offline(studentId);
	  		  payment.recordPayment(studentId, status);
	  	  break;
	  	  case 2:
	  		paymentBean=online(studentId);
		   case 3:
	  		     flag2=false;
	  	   break;
	  	  }	  	  
	  	  if(status==true)
	  	  {
	  		  notification.sendNotification(NotificationConstants.PAYMENT_SUCCESS_NOTIFICATION, studentId);
	  		  System.out.println("Payment Successful");
	  		  System.out.println("Your TransactionId"+paymentBean.getTransactionId());
	  	  }
	  	  	
	  	  else
	  	  {
				  notification.sendNotification(NotificationConstants.PAYMENT_REJECTED_NOTIFICATION, studentId);
		  		  System.out.println("Payment Failed");
	  	  }
	  	  
	}

}
