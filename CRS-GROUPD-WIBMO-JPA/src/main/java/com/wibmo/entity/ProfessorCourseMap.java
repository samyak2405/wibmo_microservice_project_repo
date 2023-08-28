/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
import javax.persistence.OneToMany;
=======
>>>>>>> c9f88bff8401d451186ec476f71418accc47a9ec
import javax.persistence.Table;

/**
 * Mapping class for professor and Courses
 */
@Entity
@Table(name="professorcoursemapping")
public class ProfessorCourseMap implements Serializable {

	@Id
	private long id;
	
	@OneToMany
	@JoinColumn(name="userId")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseCatalog courseCatalog;
	
	@Column
	private int isApproved;
	
	
}
