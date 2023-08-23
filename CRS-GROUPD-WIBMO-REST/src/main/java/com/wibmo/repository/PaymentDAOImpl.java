package com.wibmo.repository;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.wibmo.model.Payment;
import com.wibmo.constant.SQLConstants;
import com.wibmo.repository.StudentDAOImpl;
import com.wibmo.utils.DButils;
import com.wibmo.repository.PaymentDAO;
import com.wibmo.repository.PaymentDAOImpl;
//import com.wibmo.utils.DButils;
@Repository
public class PaymentDAOImpl implements PaymentDAO{

	public static volatile PaymentDAOImpl instance = null;
	Connection conn = DButils.getConnection();

	
	

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
