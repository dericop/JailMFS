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

import com.jail.mfs.models.Cell;
import com.jail.mfs.models.Prisoner;
import com.jail.mfs.models.PrisonerRedistributor;
import com.jail.mfs.models.Sentence;
import com.jail.mfs.models.Visitor;
import com.jail.mfs.services.AuthorizationRepository;
import com.jail.mfs.services.CellRepository;
import com.jail.mfs.services.PrisonerRepository;
import com.jail.mfs.services.SentenceRepository;

@Controller
@RequestMapping("/prisoners")
public class PrisonerController {
	
	@Autowired
	PrisonerRepository pRepository;
	
	@Autowired
	CellRepository cRepository;
	
	@Autowired
	SentenceRepository sRepository;
	
	@Autowired
	AuthorizationRepository aRepository;
	
	@GetMapping
	public String uiPrisonersList(Model model) {
		model.addAttribute("prisoners", pRepository.findAll());
		return "prisoners";
	}
	
	@GetMapping(value="/new")
	public String uiNewPrisoner(Model model) {
		model.addAttribute("prisoner", new Prisoner());
		model.addAttribute("sentence", new Sentence());
		return "form_prisoner";
	}
	
	@PostMapping(value="/new")
	public String newPrisoner(@ModelAttribute Prisoner prisoner, @ModelAttribute Sentence sentence)
	{
		List<Cell> sortedCells = cRepository.getCellsSortedByAvailability();
		if(!sortedCells.isEmpty()) {
			prisoner.setCell(sortedCells.get(0));
			sentence.setPrisoner(prisoner);	
			pRepository.save(prisoner);
			sRepository.save(sentence);
		}
		return "redirect:/prisoners";
	}
	
	@GetMapping(value="/{id}")
	public String detailPrisoner(Model model, @PathVariable(value = "id") int id)
	{
		Prisoner prisoner=pRepository.findOne(id);
		model.addAttribute("prisoner", prisoner);
		return "prisoner_detail";
	}
	
	@GetMapping(value = "/{id}/edit")
	public String editPrisoner(Model model, @PathVariable(value = "id") int id) {
		Prisoner prisoner=pRepository.findOne(id);
		model.addAttribute("prisoner", prisoner);
		return "form_edit_prisoner";
	}
	
	@GetMapping(value = "/{id}/newSentence")
	public String addSentence(Model model, @PathVariable(value = "id") int id) {
		model.addAttribute("sentence", new Sentence());
		model.addAttribute("p", pRepository.findOne(id));
		return "form_sentence";
	}
	
	@PostMapping(value = "/{id}/newSentence")
	public String saveSentence(@ModelAttribute Sentence sentence, @PathVariable(value = "id") int id) {
		sRepository.save(sentence);
		return "redirect:/prisoners/"+id;
	}
	
	@PostMapping(value = "/edit")
	public String editPrisoner(@ModelAttribute Prisoner prisonerToUpdate) {
		Prisoner p = pRepository.findOne(prisonerToUpdate.getId());
		p.setName(prisonerToUpdate.getName());
		p.setLastName(prisonerToUpdate.getLastName());
		p.setBirthDate(prisonerToUpdate.getBirthDate());
		p.setBirthPlace(prisonerToUpdate.getBirthPlace());
		p.setContactPhoneNumber(prisonerToUpdate.getContactPhoneNumber());
		p.setSerialNumber(prisonerToUpdate.getSerialNumber());
		pRepository.save(p);
		return "redirect:/prisoners";
	}
	
	@PostMapping(value="/delete")
	public String deletePrisoner(@RequestParam int id)
	{
		pRepository.delete(id);
		List<Cell> cells = PrisonerRedistributor.distribute(cRepository.findAll());
		for(Cell c : cells) {
			System.out.println("cell: "+c.getId());
			for(Prisoner p : c.getPrisoners()) {
				System.out.println(p.getFullName());
				p.setCell(c);
				pRepository.save(p);
			}
			System.out.println();
		}
		return "redirect:/prisoners";
	}
		
	@PostMapping(value= "/deleteAuth")
	public String deleteAuthorization(Model model, @RequestParam int authid, @RequestParam int id) {
		aRepository.delete(authid);
		return "redirect:/prisoners/"+id;
	}
	
	@PostMapping(value= "/deleteSent")
	public String deleteSentence(Model model, @RequestParam int sentid, @RequestParam int id) {
		sRepository.delete(sentid);
		return "redirect:/prisoners/"+id;
	}
	
	@GetMapping(value="/search")
	public String search(Model model, @RequestParam String c) {

		Set<Prisoner> prisoners = new HashSet<>();
		List<Prisoner> pList = pRepository.findByNameContaining(c);
		System.out.println(pList.size());
		prisoners.addAll(pList);
		
		model.addAttribute("prisoners", prisoners);
		return "prisoners";
	}
	
}
