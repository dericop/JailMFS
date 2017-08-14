package com.jail.mfs.dao;

import java.util.ArrayList;

import com.jail.mfs.models.Visitor;

public class VisitorDAO {
	
	private ArrayList<Visitor> visitors;
	
	public VisitorDAO(){
		visitors = new ArrayList<Visitor>();
		visitors.add(new Visitor());
	}

	public ArrayList<Visitor> findAll() {
		return visitors;
	}

	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
	}

}
