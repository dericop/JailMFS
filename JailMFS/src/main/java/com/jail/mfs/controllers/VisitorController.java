package com.jail.mfs.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Authorization;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.AuthorizationRepository;
import com.jail.mfs.services.VisitorRepository;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

	@Autowired
	VisitorRepository vRepository;
	
	@Autowired
	AuthorizationRepository authRepository;

	@GetMapping
	public String uiVisitorList(Model model) {
		model.addAttribute("visitors", vRepository.findAll() );
		return "visitors";
	}


	@GetMapping(value = "/{id}")
	public String detailVisitor(Model model, @PathVariable(value = "id") int id) {

		Visitor visitor = vRepository.findOne(id);
		model.addAttribute("visitor", visitor);
		return "visitor_detail";
	}

	@GetMapping(value = "/{id}/edit")
	public String editVisitor(Model model, @PathVariable(value = "id") int id) {
		
		Visitor visitor = vRepository.findOne(id);
		model.addAttribute("visitor", visitor);
		
		return "visitor_edit";
	}

	@PostMapping(value = "/{id}/edit")
	public String editVisitor(Model model, @ModelAttribute Visitor visitor) {
		Visitor visitorToUpd = vRepository.findOne(visitor.getId());
		visitorToUpd.setIdCard(visitor.getIdCard());
		visitorToUpd.setName(visitor.getName());
		visitorToUpd.setLastName(visitor.getLastName());
		visitorToUpd.setBirthday(visitor.getBirthday());
		visitorToUpd.setBirthplace(visitor.getBirthplace());
		visitorToUpd.setAddress(visitor.getAddress());
		visitorToUpd.setPhone(visitor.getPhone());
		
		vRepository.save(visitorToUpd);
		
		return "redirect:/visitors";
	}

	@PostMapping(value = "/delete")
	public String deleteVisitor(Model model, @RequestParam int id) {
		vRepository.delete(id);
		return "redirect:/visitors";
	}
	
	@GetMapping(value="/detail")
	public @ResponseBody Visitor detailVisitor(@RequestParam int id) {
		System.out.println("Id card: "+id);
		
		List<Visitor> visitors = vRepository.findByIdCard(id);
		
		System.out.println(visitors);
		if(visitors.isEmpty()) {
			return new Visitor();
		}
		
		return visitors.get(0);
	}
	
	@PostMapping(value= "/deleteAuth")
	public String deleteAuthorization(Model model, @RequestParam int authid, @RequestParam int id) {
		authRepository.delete(authid);
		return "redirect:/visitors/"+id;
	}
	
	
	@GetMapping(value="/search")
	public String searchVisitor(Model model, @RequestParam String c) {
		System.out.println(c);
		
		Set<Visitor> visitors = new HashSet<>();
		visitors.addAll(vRepository.findByNameContaining(c));
		
		model.addAttribute("visitors", visitors);		
		
		return "visitors";
	}
	


}
