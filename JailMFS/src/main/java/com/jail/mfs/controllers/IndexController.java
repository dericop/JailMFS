package com.jail.mfs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jail.mfs.services.CellRepository;

@Controller
//@RequestMapping("/visitors")
public class IndexController {
	
	@Autowired
	CellRepository cRepo;
	
	@RequestMapping("/")
	public String admin() {
		return "index";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		//PrisonerRedistributor.distribute(cRepo.findAll());
		return "admin_view_base";
	}
	
	@RequestMapping("/blocks")
	public String blocks() {
		return "blocks";
	}
	
	@RequestMapping("/block_detail")
	public String block_detail() {
		return "block_detail";
	}
	
	@RequestMapping("/prisoner_detail")
	public String prisoner_detail() {
		return "prisoner_detail";
	}

	@RequestMapping("/cells")
	public String cells() {
		return "cells";
	}
	
}
