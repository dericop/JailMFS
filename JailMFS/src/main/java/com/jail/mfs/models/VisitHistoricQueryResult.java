package com.jail.mfs.models;

import java.util.Date;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


public class VisitHistoricQueryResult {

	private String visitorFullName;
	private int visitorIdCard;
	private Date visitDate;
	
	public VisitHistoricQueryResult(String visitorFullName,int visitorIdCard,Date visitDate)
	{
		super();
		this.visitorFullName=visitorFullName;
		this.visitorIdCard=visitorIdCard;
		this.visitDate=visitDate;
	}

	public String getVisitorFullName() {
		return visitorFullName;
	}

	public void setVisitorFullName(String visitorFullName) {
		this.visitorFullName = visitorFullName;
	}

	public int getVisitorIdCard() {
		return visitorIdCard;
	}

	public void setVisitorIdCard(int visitorIdCard) {
		this.visitorIdCard = visitorIdCard;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
}
