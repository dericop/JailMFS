package com.jail.mfs.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Authorization")
public class Authorization implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Visitor visitor;
	private Prisoner prisoner;
	private String relationship;
	private Interview interview;

	public Authorization() {
		relationship = "";
	}

	public Authorization(int id, Visitor visitor, Prisoner prisoner, String relationship, Interview interview) {
		super();
		this.id = id;
		this.visitor = visitor;
		this.prisoner = prisoner;
		this.relationship = relationship;
		this.interview = interview;
	}

	@ManyToOne
	@JsonBackReference
	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	@ManyToOne
	@JsonBackReference
	public Prisoner getPrisoner() {
		return prisoner;
	}

	public void setPrisoner(Prisoner prisoner) {
		this.prisoner = prisoner;
	}
	
	@Column(nullable = false)
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@OneToOne
	@JoinColumn(name="interview_id")
	@JsonBackReference	
	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
