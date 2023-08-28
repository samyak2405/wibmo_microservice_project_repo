/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.CourseCatalog;
import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface CourseRepository extends CrudRepository<CourseCatalog,Integer>{

}
