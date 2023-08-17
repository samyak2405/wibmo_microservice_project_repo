/**
 * 
 */
package com.wibmo.business;

import java.util.List;

import com.wibmo.bean.Admin;
import com.wibmo.bean.User;

/**
 * 
 */
public interface AdminOperation {
	//Approve Student Registration
 public void approveStudent();
 //Approve Student Course Registration
 public void approveCourseRegistration();
 public void addAdmin(Admin admin);
 public void assignCoursesProf();
 public void adminRegistration(Admin user);
public void approveStudentById();
}
