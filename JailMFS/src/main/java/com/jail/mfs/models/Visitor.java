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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Visitor {

	private int id;
	private int idCard;
	private String name;
	private String lastName;
	private Date birthday;
	private String birthplace;
	private String address;
	private String phone;
	private Set<Authorization> authorizations;
	private Set<Visit> visits;

	public Visitor() {
		id = 0;
		name = "";
		lastName = "";
		birthday = new Date();
		birthplace = "";
		address = "";
		authorizations = new HashSet<>();
		visits = new HashSet<>();
	}

	public Visitor(int id, int idCard, String name, String lastName, Date birthday, String birthplace, String address,
			String phone, Set<Authorization> authorizations, Set<Visit> visits) {
		super();
		this.id = id;
		this.idCard = idCard;
		this.name = name;
		this.lastName = lastName;
		this.birthday = birthday;
		this.birthplace = birthplace;
		this.address = address;
		this.phone = phone;
		this.authorizations = authorizations;
		this.visits = visits;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false, unique = true)
	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(nullable = false)
	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "visitor", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonManagedReference
	public Set<Authorization> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(Set<Authorization> authorizations) {
		this.authorizations = authorizations;
	}
	
	@OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	public Set<Visit> getVisits() {
		return visits;
	}

	public void setVisits(Set<Visit> visits) {
		this.visits = visits;
	}

	public void addAuthorization(Authorization authorization) {
		authorizations.add(authorization);
	}
	
	@Transient
	public String getFullName() {
		return this.name+" "+this.lastName; 
	}
	
	
	int visitsToBlockBetweenDates;
	
	@Transient
	public int getVisitsToBlockBetweenDates() {
		return visitsToBlockBetweenDates;
	}
	
	public void setVisitsToBlockBetweenDates(int block_id, Date startDate, Date endDate) {
		int acc = 0;
		for(Visit vis : this.visits)
			if(vis.getPrisoner().getCell().getBlock().getId() == block_id && startDate.compareTo(vis.getDate()) <= 0 && endDate.compareTo(vis.getDate()) >= 0)
				acc++;
		visitsToBlockBetweenDates = acc;
	}
	
	public void setVisitsToBlockBetweenDates(Date startDate, Date endDate) {
		int acc = 0;
		for(Visit vis : this.visits)
			if(startDate.compareTo(vis.getDate()) <= 0 && endDate.compareTo(vis.getDate()) >= 0)
				acc++;
		visitsToBlockBetweenDates = acc;
	}

}
