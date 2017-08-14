package com.jail.mfs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Authorization;
import com.jail.mfs.models.Prisoner;
import com.jail.mfs.models.Visit;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.PrisonerRepository;
import com.jail.mfs.services.VisitRepository;
import com.jail.mfs.services.VisitorRepository;

@Controller
@RequestMapping("/visits")
public class VisitController {
	
	@Autowired
	private VisitRepository visitRep;
	
	@Autowired
	private PrisonerRepository prisonerRep;
	
	@Autowired
	private VisitorRepository visitorRep;
	
	@GetMapping
	public String visits(Model model)
	{
		model.addAttribute("visit",new Visit());
		model.addAttribute("visits", visitRep.findAll());				
		model.addAttribute("prisoners", prisonerRep.findAll());
		return "visits";
	}
	
	@PostMapping("/visitorsForPrisoner")
	@ResponseBody
	public List<Visitor> getVisitorsForPrisoner(@RequestParam int prisonerId){
		List<Visitor> result = new ArrayList<>();
		Prisoner p = prisonerRep.findOne(prisonerId);
		for(Authorization a : p.getAuthorizations())
			result.add(a.getVisitor());
		return result;
	}
	
	@PostMapping("/delete")
	public String deleteVisit(@RequestParam int id) {
		visitRep.delete(id);
		return "redirect:/visits";
	}
	
	@PostMapping("/register")
	public String createNewVisit(@RequestParam int prisoner_id,@RequestParam int visitor_id,@ModelAttribute Visit visit)
	{
		Prisoner prisoner=prisonerRep.findOne(prisoner_id);
		Visitor visitor=visitorRep.findOne(visitor_id);
		visit.setPrisoner(prisoner);
		visit.setVisitor(visitor);
		visitRep.save(visit);
		return "redirect:/visits";
	}

}
