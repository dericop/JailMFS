package com.jail.mfs.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Interview{

	public enum Result {
		Pending, Approved, Denied
	}
	
	private int id;
	
	private Date assignationDate;
	private Date startDate;
	private Date endDate;
	private int intervieweeId;
	private String intervieweeName;
	private Result result;
	private Authorization authorization;
	private Prisoner prisoner;

	public Interview() {
		assignationDate = new Date();
		result = Result.Pending;
		prisoner = new Prisoner();
	}

	public Interview(int id, Date assignationDate, Date startDate, Date endDate, int intervieweeId,
			String intervieweeName, Prisoner prisoner) {
		super();
		this.id = id;
		this.assignationDate = assignationDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.intervieweeId = intervieweeId;
		this.intervieweeName = intervieweeName;
		this.prisoner = prisoner;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:")
	public Date getAssignationDate() {
		return assignationDate;
	}

	public void setAssignationDate(Date assignationDate) {
		this.assignationDate = assignationDate;
	}

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(nullable = false)
	public int getIntervieweeId() {
		return intervieweeId;
	}

	public void setIntervieweeId(int intervieweeId) {
		this.intervieweeId = intervieweeId;
	}

	@Column(nullable = false)
	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@OneToOne(mappedBy = "interview", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
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
	
	public void approve() {
		this.result = Result.Approved;
	}
	
	public void deny() {
		this.result = Result.Denied;
	}

}
