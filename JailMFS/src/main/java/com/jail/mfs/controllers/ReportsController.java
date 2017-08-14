package com.jail.mfs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Block;
import com.jail.mfs.models.Cell;
import com.jail.mfs.models.Prisoner;
import com.jail.mfs.models.Sentence;
import com.jail.mfs.models.Visit;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.BlockRepository;
import com.jail.mfs.services.SentenceRepository;
import com.jail.mfs.services.PrisonerRepository;
import com.jail.mfs.services.VisitRepository;
import com.jail.mfs.services.VisitorRepository;

@Controller
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private VisitorRepository vRepository;
	
	@Autowired
	private SentenceRepository sRepository;
	
	@Autowired
	private PrisonerRepository prisonerRepo;
	
	@Autowired
	private VisitRepository visitRepo;
	
	@GetMapping 	
	public String reports() {
		return "reports";
	}
	
	@GetMapping("/fvisitors")
	public String freqvisitors(@RequestParam(required = false) Integer blockId, 
									  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
									  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model){
		List<Visitor> result;
		if(startDate == null) 
			startDate = new GregorianCalendar(0,0,1).getTime();
		if(endDate == null)
			endDate = new Date();
		if(blockId != null) {
			result = vRepository.getVisitorsOrderedByVisitsToBlockBetweenDates(blockId, startDate, endDate);
			for(Visitor v : result)
				v.setVisitsToBlockBetweenDates(blockId, startDate, endDate);
		} else { 
			result = vRepository.getVisitorsOrderedByVisitsBetweenDates(startDate, endDate);
			for(Visitor v : result)
				v.setVisitsToBlockBetweenDates(startDate, endDate);
		}
		model.addAttribute("blocks", blockRepository.findAll());
		model.addAttribute("visitors", result);
		return "reports_frequent_visitors";
	}
	
	@GetMapping("/prisoners-historic")
	public String prisonersHistoric(@RequestParam(required=false) Integer prisoner_id, Model model)
	{
		model.addAttribute("prisoners", prisonerRepo.findAll());
		List<Visit> result;
		if(prisoner_id != null)
			result = visitRepo.prisonerVisitHistoric(prisoner_id);
		else
			result = visitRepo.findAll();		
		model.addAttribute("visitsToPrisoner", result);
		return "reports_prisoners_historic";
	}
	
	@GetMapping("/prisoners-block")
	public String prisonersByBlock(Model model) {
		model.addAttribute("blocks", blockRepository.findAll());
		model.addAttribute("prisoners", prisonerRepo.findAll());
		return "reports_prisoners_block";
	}
	
	
	@GetMapping("/prisoners-block/getdata")
	public String prisonersByBlocks(Model model, @RequestParam(required=false) Integer id) {
		List<Prisoner> prisoners;
		if(id != null){
			prisoners = new ArrayList<Prisoner>();
			Block block = blockRepository.findOne(id);
			List<Cell> cells = block.getCells();
			
			for(Cell cell: cells) {
				prisoners.addAll(cell.getPrisoners());
			}
		} else {
			prisoners = prisonerRepo.findAll();
		}
		model.addAttribute("prisoners", prisoners);
		model.addAttribute("blocks", blockRepository.findAll());
		
		return "reports_prisoners_block";
	}
		
	@GetMapping("/convictions-fullfilled")
	public String convictionsFullFilled(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			  							@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model model) {
		List<Sentence> result;
		if(startDate == null) 
			startDate = new GregorianCalendar(0,0,1).getTime();
		if(endDate == null)
			endDate = new Date();
		result = sRepository.getSentencesFulfilledBetween(startDate, endDate);
		//return result;
		model.addAttribute("sentences", result);		
		return "reports_convictions_fulfilled";
	}
	
	
}
