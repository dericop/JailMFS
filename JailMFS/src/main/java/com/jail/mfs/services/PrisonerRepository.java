package com.jail.mfs.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jail.mfs.models.Prisoner;

public interface PrisonerRepository extends JpaRepository<Prisoner, Integer>{
	
	List<Prisoner> findByNameContaining(String name);	
	
	
}
