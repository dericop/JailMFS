package com.jail.mfs.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jail.mfs.models.Authorization;
import com.jail.mfs.models.Interview;
import java.util.List;

import javax.transaction.Transactional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Integer>{
	
	List<Authorization> findByInterview(Interview interview);
	
}
