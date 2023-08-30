/**
 * 
 */
package com.wibmo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean class for Student entity
 */
@Entity
@Table(name = "student")
public class Student extends User {

	@Column(name = "isApproved")
	private int isapproved;

	public Student() {

	}

	/**
	 * parameterized constructor which sets the student object properties
	 * @param student
	 */
	public Student(Student student) {
		this.setUserId(student.getUserId());
		this.setUserName(student.getUserName());
		this.setUserEmail(student.getUserEmail());
		this.setUserPassword(student.getUserPassword());
		this.setUserPhonenumber(student.getUserPhonenumber());
		this.isapproved = student.isapproved;
	}

	/**
	 * returns the approval status
	 * 
	 * @return isApproved
	 */
	public int getIsapproved() {
		return isapproved;
	}

	/**
	 * sets the approval status
	 * 
	 * @param isapproved
	 */
	public void setIsapproved(int isapproved) {
		this.isapproved = isapproved;
	}

}
