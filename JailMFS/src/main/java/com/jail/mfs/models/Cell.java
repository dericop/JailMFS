package com.jail.mfs.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cell implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int capacity;
	private Block block;
	private List<Prisoner> prisoners;

	public Cell() {

	}

	public Cell(int capacity) {
		super();
		this.capacity = capacity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false)
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@ManyToOne
	@JsonIgnoreProperties("cells")
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@JsonManagedReference
	@OneToMany(mappedBy = "cell")
	public List<Prisoner> getPrisoners() {
		return prisoners;
	}

	public void setPrisoners(List<Prisoner> prisoners) {
		this.prisoners = prisoners;
	}
	
	@Transient
	public int getFreeSlots() {
		return this.capacity - this.prisoners.size();
	}
	
	@Transient
	public int getOccupiedSlots() {
		return this.prisoners.size();
	}

}
