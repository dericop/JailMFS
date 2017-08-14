package com.jail.mfs.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jail.mfs.models.Sentence;

public interface SentenceRepository extends JpaRepository<Sentence, Integer>{
		
	@Query(value = "select * from sentence s where s.end_date between :sdt and :edt", nativeQuery = true)
	public List<Sentence> getSentencesFulfilledBetween(@Param("sdt") Date sdt, @Param("edt") Date edt);
	
}
