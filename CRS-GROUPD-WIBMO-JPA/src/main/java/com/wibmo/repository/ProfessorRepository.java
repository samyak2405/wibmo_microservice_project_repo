/**
 * 
 */
package com.wibmo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wibmo.entity.User;

/**
 * 
 */
@Repository
public interface ProfessorRepository extends CrudRepository<User,Integer>{
  @Modifying
  @Query("SELECT COUNT(*) FROM crs.professor WHERE professoremail=?")
  public int searchProfessor(@Param("professoremail") String professoremail);
}
