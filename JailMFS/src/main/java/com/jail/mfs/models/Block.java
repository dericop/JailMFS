package com.jail.mfs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Block implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	public List<Cell> cells = new ArrayList<>();	
	private int capacity;

	public Block() {

	}

	public void addCell(Cell cell) {
		cell.setBlock(this);
		cells.add(cell);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "block", orphanRemoval = true)
	@JsonIgnoreProperties("block")
	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
	
	@Formula("COALESCE((select sum(c.capacity) from cell c where c.block_id = id), 0)")
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Transient
	public int getFreeSlots() {
		int ans = 0;
		for(Cell c : cells)
			ans += c.getFreeSlots();
		return ans;
	}
	
	@Transient
	public int getOccupiedSlots() {
		int ans = 0;
		for(Cell c : cells)
			ans += c.getOccupiedSlots();
		return ans;
	}

}
