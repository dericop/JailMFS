package com.jail.mfs.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jail.mfs.models.Visit;

public interface VisitRepository extends JpaRepository<Visit, Integer> {
	
	@Query(value="select * from visit v where v.prisoner_id = :prisoner_id", nativeQuery=true)
	List<Visit> prisonerVisitHistoric(@Param("prisoner_id") int prisoner_id);
}
