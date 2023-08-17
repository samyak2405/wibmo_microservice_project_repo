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
 public void addCourse(long courseId);
 public void dropCourse(long courseId);
 public boolean approveStudent();
 public void addAdmin(Admin admin);
 public void assignCoursesProf(int professorId,int courseId);
 public void adminRegistration(Admin user);
}
