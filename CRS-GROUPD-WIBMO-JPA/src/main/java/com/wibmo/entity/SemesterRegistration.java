/**
 * 
 */
package com.wibmo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="semesterRegistration")
public class SemesterRegistration {

	@Id
	@Column
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	int id;
	
	
	
}
