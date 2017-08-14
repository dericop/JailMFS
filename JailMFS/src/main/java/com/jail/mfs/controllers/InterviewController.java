package com.jail.mfs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Authorization;
import com.jail.mfs.models.Interview;
import com.jail.mfs.models.Interview.Result;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.AuthorizationRepository;
import com.jail.mfs.services.InterviewRepository;
import com.jail.mfs.services.PrisonerRepository;
import com.jail.mfs.services.VisitorRepository;

@Controller
@RequestMapping("/interviews")
public class InterviewController {
	
	@Autowired
	private InterviewRepository interRep;
	
	@Autowired
	private PrisonerRepository prisonerRep;
	
	@Autowired
	private AuthorizationRepository authRep;
	
	@Autowired
	private VisitorRepository visitorRepository;
	
	@GetMapping
	public String interviews(Model model) {
		model.addAttribute("interview", new Interview());
		model.addAttribute("prisoners", prisonerRep.findAll());
		return "interviews";
	}
	
	@PostMapping("/new")
	public String newInterview(@ModelAttribute Interview interview) {
		interRep.save(interview);
		return "redirect:/interviews";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public @ResponseBody List<Interview> intervieewsJson(){
		
		return interRep.findAll();
	}
	
	@GetMapping(value="/{id}")
	public String interviewDetail(Model model, @PathVariable(value="id") int id) {
		
		Interview interview = interRep.findOne(id);
		model.addAttribute("interview", interview);
		
		List<Authorization>auths = authRep.findByInterview(interview);
		if(auths.isEmpty())
			model.addAttribute("authorization", new Authorization());
		else {
			model.addAttribute("authorization", auths.get(0));
		}
		
		model.addAttribute("visitor", new Visitor());
		model.addAttribute("pending", Result.Pending);
		model.addAttribute("approved", Result.Approved);
		model.addAttribute("denied", Result.Denied);
		
		List<String> relationships = new ArrayList<String>();
		relationships.add("Abuelo/a");
		relationships.add("Padre/Madre");
		relationships.add("Hermano/a");
		relationships.add("Primo/a");
		relationships.add("Tio/a");
		relationships.add("Amigo/a");
		
		model.addAttribute("relationships", relationships);
		return "interviews_detail";
	}
	
	@PostMapping(value="/approved")
	public String interviewApproved(Model model, @RequestParam int intId, 
			@ModelAttribute Authorization authorization, @ModelAttribute Visitor visitor) {
	
		Interview interview = interRep.findOne(intId);
		interview.approve();
		
		interRep.save(interview);
		
		visitor.addAuthorization(authorization);
		
		if(!visitor.getName().equals(""))
			visitorRepository.save(visitor);
		
		authorization.setInterview(interview);
		authorization.setPrisoner(interview.getPrisoner());
		
		if(!visitor.getName().equals(""))
			authorization.setVisitor(visitor);
		else {
			Visitor v = (visitorRepository.findByIdCard(interview.getIntervieweeId())).get(0);
			authorization.setVisitor(v);
		}
		
		authRep.save(authorization);
		
		return "redirect:/interviews";
	}
	
	@PostMapping(value="/denied")
	public String interviewDenied(Model model, @RequestParam int intId) {
		Interview interview = interRep.findOne(intId);
		interview.deny();
		interRep.save(interview);
		
		return "redirect:/interviews";
	}
	

}
