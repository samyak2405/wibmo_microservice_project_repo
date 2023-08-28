/**
 * 
 */
package com.wibmo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Bean class for User entity
 */
@Entity
@Table(name="admin")
public class Admin extends User{
	private int  isApproved;

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

}
