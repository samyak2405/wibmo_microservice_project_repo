/**
 * 
 */
package com.wibmo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wibmo.bean.Payment;
import com.wibmo.constant.SQLConstants;
import com.wibmo.utils.DButils;

/**
 * 
 */
public class paymentDAOImpl implements paymentDAO{

	public static volatile paymentDAOImpl instance = null;
	Connection conn = DButils.getConnection();

	
	private paymentDAOImpl() {
		
	}
	
	public static paymentDAOImpl getInstance() {
		if(instance==null)
		{
			synchronized(StudentDAOImpl.class) {
				instance = new paymentDAOImpl();
			}
		}
		return instance;
	}

	@Override
	public void setPaymentRecord(Payment payment) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		
		try {
			
			stmt = conn.prepareStatement(SQLConstants.RECORD_PAYMENT);
			
			stmt.setLong(1, payment.getUserId());
			stmt.setInt(2,payment.getPaymentStatus());
			stmt.setLong(3,payment.getTransactionId());
			stmt.setInt(4,payment.getAmount());
			
			stmt.executeUpdate();
		}catch(SQLException se){
		      //Handle errors for JDBC
			
		      se.printStackTrace();
		   }
	}
	

}
