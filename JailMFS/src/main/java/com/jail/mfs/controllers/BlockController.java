package com.jail.mfs.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jail.mfs.models.Block;
import com.jail.mfs.models.Cell;
import com.jail.mfs.models.Prisoner;
import com.jail.mfs.services.BlockRepository;
import com.jail.mfs.services.CellRepository;
import com.jail.mfs.services.PrisonerRepository;

@Controller
@RequestMapping("/blocks")
public class BlockController {

	@Autowired
	BlockRepository bRepo;
	
	@Autowired
	CellRepository cRepo;
	
	@Autowired
	PrisonerRepository pRepository;
	
	@GetMapping
	public String blockList(Model model) {
		model.addAttribute("blocks", bRepo.findAll());
		return "blocks";
	}
	
	@PostMapping
	@ResponseBody
	public Block createBlock() {
		Block b = new Block();
		bRepo.save(b);
		return b;
	}
	
	@GetMapping(value="/{id}")
	public String blockDetail(@PathVariable int id, Model model) {
		model.addAttribute("block", bRepo.findOne(id));
		return "block_detail";
	}
	
	@PostMapping(value="/addCell")
	public String addCell(@RequestParam int cellCapacity, @RequestParam int blockId) {
		Cell newCell = new Cell(cellCapacity);
		Block bDet = bRepo.getOne(blockId);
		bDet.addCell(newCell);		
		cRepo.save(newCell);
		return "redirect:/blocks/"+blockId;
	}
	
	@PostMapping(value="/deleteCell")
	public String deleteCell(@RequestParam int cellId, @RequestParam int blockId, Model model) {
		Cell c1 = cRepo.getOne(cellId);
		int sum = 0;
		for(Block b : bRepo.findAll()) {
			if(b.getId() != blockId) {
				sum += b.getFreeSlots();
			} else {
				for(Cell c : b.getCells())
					if(c.getId() != cellId)
						sum += c.getFreeSlots();
			}
		}
		if(c1.getOccupiedSlots() <= sum) {
			List<Prisoner> freePrisoners = new ArrayList<>();
			freePrisoners.addAll(c1.getPrisoners());
			for(Prisoner p : c1.getPrisoners())
				p.setCell(null);
			c1.setPrisoners(null);
			cRepo.delete(cellId);
			for(Prisoner prisoner : freePrisoners) {
				List<Cell> sortedCells = cRepo.getCellsSortedByAvailability();
				prisoner.setCell(sortedCells.get(0));
				pRepository.save(prisoner);
			}
		} else
			model.addAttribute("error", "error");		
		return "redirect:/blocks/"+blockId;
	}
	
	@PostMapping(value="/deleteBlock")
	public String deleteBlock(@RequestParam int blockId, Model model) {
		Block b1 = bRepo.getOne(blockId);
		int sum = 0;
		for(Block b : bRepo.findAll())
			sum += b.getFreeSlots();
		if(b1.getOccupiedSlots() <= sum) {
			List<Prisoner> freePrisoners = new ArrayList<>();
			for(Cell c : b1.getCells()) {
				freePrisoners.addAll(c.getPrisoners());
				for(Prisoner p : c.getPrisoners())
					p.setCell(null);
			}
			for(Cell c : b1.getCells())
				c.setPrisoners(null);
			bRepo.delete(blockId);
			for(Prisoner prisoner : freePrisoners) {
				List<Cell> sortedCells = cRepo.getCellsSortedByAvailability();
				prisoner.setCell(sortedCells.get(0));
				pRepository.save(prisoner);
			}
		}
		else
			model.addAttribute("error", "error");		
		return "redirect:/blocks/";
	}
	
	@GetMapping(value="/sortedCells")
	@ResponseBody
	public List<Cell> sortedCells(){
		return cRepo.getCellsSortedByAvailability(); 
	}
	
}
