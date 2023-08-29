/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private long id;
	
//	@OneToMany
//	@JoinColumn(name="userId")
//	private List<Professor> professor;
//	
//	@ManyToOne
//	@JoinColumn(name="courseId")
//	private List<CourseCatalog> courseCatalog;
	
	@Column
	int isApproved;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



//	public List<Professor> getProfessor() {
//		return professor;
//	}
//
//	public void setProfessor(List<Professor> professor) {
//		this.professor = professor;
//	}
//
//	public List<CourseCatalog> getCourseCatalog() {
//		return courseCatalog;
//	}
//
//	public void setCourseCatalog(List<CourseCatalog> courseCatalog) {
//		this.courseCatalog = courseCatalog;
//	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}


	
	


}
