/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;

import javax.persistence.Table;

/**
 * Mapping class for professor and Courses
 */
@Entity
@Table(name="professorcoursemapping")
public class ProfessorCourseMap implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
<<<<<<< HEAD
	@ManyToOne
	@JoinColumn(name="userId")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseCatalog courseCatalog;
=======
//	@OneToMany
//	@JoinColumn(name="userId")
//	private List<Professor> professor;
//	
//	@ManyToOne
//	@JoinColumn(name="courseId")
//	private List<CourseCatalog> courseCatalog;
>>>>>>> 0597778d140a697fcdb7ecd4a68f9cd0331109ee
	
	@Column
	int isApproved;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	public void setCourseCatalog(CourseCatalog courseCatalog) {
		this.courseCatalog = courseCatalog;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}


	
	


}
