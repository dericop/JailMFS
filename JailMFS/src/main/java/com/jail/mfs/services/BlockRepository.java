package com.jail.mfs.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jail.mfs.models.Block;

public interface BlockRepository extends JpaRepository<Block, Integer>{

}
