/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wibmo.entity.GradeCard;

/**
 * Repository related to GradeCard CRUD operations
 */
@Repository
public interface GradeCardRepository extends CrudRepository<GradeCard, Integer> {

}
