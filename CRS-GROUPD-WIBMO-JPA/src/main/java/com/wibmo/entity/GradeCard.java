/**
 * 
 */
package com.wibmo.entity;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class representing Grade Card
 */
@Entity
@Table(name="gradecard")
public class GradeCard implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="courseId")
	private CourseCatalog catalog;
	
	@Column
	private String grade;
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public CourseCatalog getCatalog() {
		return catalog;
	}
	public void setCatalog(CourseCatalog catalog) {
		this.catalog = catalog;
	}
	


	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
