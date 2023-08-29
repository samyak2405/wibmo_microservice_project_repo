/**
 * 
 */
package com.wibmo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.GradeCard;
import com.wibmo.entity.Student;

/**
 * 
 */
@Repository
public interface GradeCardRepository extends CrudRepository<GradeCard,Integer>{

	public GradeCard findByStudentAndCatalog(Student student, CourseCatalog catalog);
	
	public List<GradeCard>findByStudent(Student student);
}
