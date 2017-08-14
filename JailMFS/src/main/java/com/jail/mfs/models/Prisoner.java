package com.jail.mfs.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Prisoner {

	private int id;
	private String name;
	private String lastName;
	private String birthPlace;
	private Date birthDate;
	private String serialNumber;
	private String contactPhoneNumber;
	private Cell cell;
	private Set<Visit> visits;
	private Set<Authorization> authorizations;
	private Set<Interview> interviews;
	private Set<Sentence> sentences;

	public Prisoner() {
		id = 0;
		name = "";
		lastName = "";
		birthPlace = "";
		birthDate = new Date();
		serialNumber = "";
		contactPhoneNumber = "";
		authorizations = new HashSet<Authorization>();
		sentences = new HashSet<Sentence>();
		visits = new HashSet<Visit>();
	}

	public Prisoner(Cell cell, String name, String lastName, String birthPlace, Date birthDate, String serialNumber,
			String contactPhoneNumber) {
		super();
		this.cell = cell;
		this.name = name;
		this.lastName = lastName;
		this.birthPlace = birthPlace;
		this.birthDate = birthDate;
		this.serialNumber = serialNumber;
		this.contactPhoneNumber = contactPhoneNumber;
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(nullable = false)
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@ManyToOne
	@JsonBackReference
	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	@Column(nullable = false, unique = true)
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(nullable = false)
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	@OneToMany(mappedBy = "prisoner", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonManagedReference
	public Set<Authorization> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(Set<Authorization> authorizations) {
		this.authorizations = authorizations;
	}

	@OneToMany(mappedBy = "prisoner", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}
	
	@OneToMany(mappedBy = "prisoner", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("prisoner")
	public Set<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(Set<Sentence> sentences) {
		this.sentences = sentences;
	}
	
	@OneToMany(mappedBy = "prisoner", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Visit> getVisits() {
		return visits;
	}
	
	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}
	
	@Transient
	public String getFullName() {
		return this.name+" "+this.lastName; 
	}
	
}
