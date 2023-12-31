/**
 * 
 */
package com.wibmo.dto;

/**
 * Data Transfer Object for sending course details
 */
public class SendCourseDto {
	int userId;
	String courseId;
	int courseCategory;
	int isRegister;

	/**
	 * 
	 */
	public SendCourseDto() {
		isRegister = 0;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseCategory
	 */
	public int getCourseCategory() {
		return courseCategory;
	}

	/**
	 * @param courseCategory the courseCategory to set
	 */
	public void setCourseCategory(int courseCategory) {
		this.courseCategory = courseCategory;
	}

	/**
	 * @return the isRegister
	 */
	public int getIsRegister() {
		return isRegister;
	}

	/**
	 * @param isRegister the isRegister to set
	 */
	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}

}
