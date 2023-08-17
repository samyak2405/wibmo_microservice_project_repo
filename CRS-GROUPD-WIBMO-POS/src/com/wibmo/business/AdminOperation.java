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
 public void approveStudent();
 public void addAdmin(Admin admin);
 public void assignCoursesProf();
 public void adminRegistration(Admin user);
}
