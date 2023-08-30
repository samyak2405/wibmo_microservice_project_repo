/**
 * 
 */
package com.wibmo.entity;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
/**
 * Bean class for Student entity
 */
@Entity
@Table(name="student")
public class Student extends User {
	
	
	@Column(name="isApproved")
	private int isapproved;
	
	/**
	 * @return the courseRegistrationStatus
	 */
	public int getCourseRegistrationStatus() {
		return courseRegistrationStatus;
	}

	/**
	 * @param courseRegistrationStatus the courseRegistrationStatus to set
	 */
	public void setCourseRegistrationStatus(int courseRegistrationStatus) {
		this.courseRegistrationStatus = courseRegistrationStatus;
	}

	@Column(name="courseRegistrationStatus")
	private int courseRegistrationStatus;

	
	public Student() {
		
	}
	
	public Student(Student student) {
		this.setUserId(student.getUserId());
		this.setUserName(student.getUserName());
		this.setUserEmail(student.getUserEmail());
		this.setUserPassword(student.getUserPassword());
		this.setUserPhonenumber(student.getUserPhonenumber());
		this.isapproved = student.isapproved;
	}
	public int getIsapproved() {
		return isapproved;
	}
	
	public void setIsapproved(int isapproved) {
		this.isapproved = isapproved;
	}

}
