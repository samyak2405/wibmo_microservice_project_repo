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
 * Repository related to GradeCard CRUD operations
 */
@Repository
public interface GradeCardRepository extends CrudRepository<GradeCard, Integer> {

}
