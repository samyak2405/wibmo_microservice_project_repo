/**
 * 
 */
package com.wibmo.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	public int getIsapproved() {
		return isapproved;
	}
	
	public void setIsapproved(int isapproved) {
		this.isapproved = isapproved;
	}

}
