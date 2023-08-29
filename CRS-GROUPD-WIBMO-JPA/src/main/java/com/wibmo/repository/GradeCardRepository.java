/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.GradeCard;

/**
 * 
 */
@Repository
public interface GradeCardRepository extends CrudRepository<GradeCard,Integer>{

}
