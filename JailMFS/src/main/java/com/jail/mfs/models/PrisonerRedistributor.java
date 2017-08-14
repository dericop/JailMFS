package com.jail.mfs.models;

import java.util.List;

public class PrisonerRedistributor {

	public static List<Cell> distribute(List<Cell> cells){
		double[] filledPercentages = getFilledPercentages(cells);
		//printCellsAndPercentages(cells, filledPercentages);
		
		cells = sortCellsAccordingToPercentage(cells, filledPercentages);
		printCellsAndPercentages(cells, filledPercentages);
		
		double previousDev = std_dev(filledPercentages);
		System.out.println(previousDev);
		double currentDev = Double.MAX_VALUE;
		
		while(currentDev > 0.1 && Math.abs(currentDev - previousDev) > 0.05) {
			previousDev = currentDev;
			Prisoner p1 = cells.get(cells.size()-1).getPrisoners().get(0);
			cells.get(0).getPrisoners().add(p1);
			cells.get(cells.size()-1).getPrisoners().remove(p1);
			filledPercentages = getFilledPercentages(cells);
			cells = sortCellsAccordingToPercentage(cells, filledPercentages);
			currentDev = std_dev(filledPercentages);
			
			printCellsAndPercentages(cells, filledPercentages);
			System.out.println(currentDev);
		}
		
		return cells;
	}
	
	private static double std_dev(double a[]) {
		double n = (double)a.length;
	    if(n == 0)
	        return 0.0;
	    double sum = 0;
	    double sq_sum = 0;
	    for(int i = 0; i < n; ++i) {
	       sum += a[i];
	       sq_sum += a[i] * a[i];
	    }
	    double mean = sum / n;
	    double variance = sq_sum / n - mean * mean;
	    return Math.sqrt(variance);
	}
	
	private static void printCellsAndPercentages(List<Cell> cells, double[] percentages) {
		for(int i = 0; i < cells.size(); i++)
			System.out.println(cells.get(i).getId()+" : "+percentages[i]);
	}
	
	private static double[] getFilledPercentages(List<Cell> cells) {
		double[] filledPercentages = new double[cells.size()];
		for(int i = 0; i < cells.size(); i++)
			filledPercentages[i] = (double)cells.get(i).getPrisoners().size()/(double)cells.get(i).getCapacity();
		return filledPercentages;
	}
	
	private static List<Cell> sortCellsAccordingToPercentage(List<Cell> cells, double[] percentages){
		boolean done = false;
		while(!done) {
			done = true;
			for(int i = 0; i < percentages.length - 1; i++) {
				if(percentages[i+1] < percentages[i]) {
					double aux = percentages[i];
					percentages[i] = percentages[i+1];
					percentages[i+1] = aux;
					Cell caux = cells.get(i);
					cells.set(i, cells.get(i+1));
					cells.set(i+1, caux);
					done = false;
				}
			}
		}
		return cells;
	}
	
}
