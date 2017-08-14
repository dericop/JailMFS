package com.jail.mfs.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Visit")
public class Visit {
	
	private int id;
	private Date date;
	private Prisoner prisoner;
	private Visitor visitor;
	
	public Visit() {
		this.date=new Date();
	}
	
	public Visit(Prisoner prisoner,Visitor visitor,Date date)
	{
		super();
		this.prisoner=prisoner;
		this.visitor=visitor;
		this.date=date;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "prisoner_id")
	@JsonBackReference
	public Prisoner getPrisoner() {
		return prisoner;
	}

	public void setPrisoner(Prisoner prisoner) {
		this.prisoner = prisoner;
	}

	@ManyToOne
	@JoinColumn(name = "visitor_id")
	@JsonBackReference
	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Transient
	public String getVisitorFullName()
	{
		return visitor.getName()+" "+visitor.getLastName();
	}
	
	@Transient
	public String getPrisonerFullName()
	{
		return prisoner.getName()+" "+prisoner.getLastName();
	}
	
	@Transient
	public String getRelationship()
	{
		for(Authorization a : visitor.getAuthorizations())
			if(a.getPrisoner().equals(prisoner))
				return a.getRelationship();
		return "?";
	}
	
}
