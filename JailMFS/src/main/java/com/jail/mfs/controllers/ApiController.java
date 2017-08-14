package com.jail.mfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Authorization;
import com.jail.mfs.models.Block;
import com.jail.mfs.models.Cell;
import com.jail.mfs.models.Interview;
import com.jail.mfs.models.Prisoner;
import com.jail.mfs.models.Sentence;
import com.jail.mfs.models.Visit;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.AuthorizationRepository;
import com.jail.mfs.services.BlockRepository;
import com.jail.mfs.services.CellRepository;
import com.jail.mfs.services.InterviewRepository;
import com.jail.mfs.services.PrisonerRepository;
import com.jail.mfs.services.SentenceRepository;
import com.jail.mfs.services.VisitRepository;
import com.jail.mfs.services.VisitorRepository;

@Controller
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private PrisonerRepository prisonerRepo;
	
	@Autowired
	private SentenceRepository sentenceRepo;
	
	@Autowired
	private CellRepository cellRepo;
	
	@Autowired
	private BlockRepository blockRepo;
	
	@Autowired
	private InterviewRepository interviewRepo;
	
	@Autowired
	private AuthorizationRepository authRepo;
	
	@Autowired
	private VisitorRepository visitorRepo;
	
	@Autowired
	private VisitRepository visitRepo;
	
	@GetMapping
	public String API(Model model)
	{
		model.addAttribute("prisoners", prisonerRepo.findAll());
		model.addAttribute("cells", cellRepo.findAll());
		model.addAttribute("blocks", blockRepo.findAll());
		model.addAttribute("visitors", visitorRepo.findAll());
		return "api";
	}

	@GetMapping("/prisoners")	
	@ResponseBody
	public List<Prisoner> prisoners()
	{
		return  prisonerRepo.findAll();
	}
	
	@GetMapping("/prisoners/{id}")	
	@ResponseBody
	public Prisoner prisoner(@RequestParam int prisoner_id)
	{
		return  prisonerRepo.findOne(prisoner_id);
	}
	
	@GetMapping("/sentences")
	@ResponseBody
	public List<Sentence> sentences()
	{
		return sentenceRepo.findAll();
	}
	
	@GetMapping("/cells")
	@ResponseBody
	public List<Cell> cells()
	{
		return cellRepo.findAll();
	}
	
	@GetMapping("/blocks")
	@ResponseBody
	public List<Block> blocks()
	{
		return blockRepo.findAll();
	}
	
	@GetMapping("/block/{id}")	
	@ResponseBody
	public Block block(@RequestParam int block_id)
	{
		return  blockRepo.findOne(block_id);
	}
	
	@GetMapping("/interviews")
	@ResponseBody
	public List<Interview> interviews()
	{
		return interviewRepo.findAll();
	}
	
	@GetMapping("/authorizations")
	@ResponseBody
	public List<Authorization> authorizations()
	{
		return authRepo.findAll();
	}
	
	@GetMapping("/visitors")
	@ResponseBody
	public List<Visitor> visitors()
	{
		return visitorRepo.findAll();
	}
	
	@GetMapping("/visitors/{id}")
	@ResponseBody
	public Visitor visitors(@RequestParam int visitor_id)
	{
		return visitorRepo.findOne(visitor_id);
	}
	
	@GetMapping("/visits")
	@ResponseBody
	public List<Visit> visits()
	{
		return visitRepo.findAll();
	}

}
