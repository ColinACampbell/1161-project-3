package com.colin.Models;

import com.colin.Models.Venue;

import java.util.Scanner;

/**
 * A venue for sporting
 */
public class SportsVenue extends Venue {
   private double competitorArea;
   private double spectatorArea;
   private int numSecurity;


	/**
	 * Creates a sports venue
	 * @param name Name of sporting venue
	 * @param competitorArea Area where competitors reside, in cubic meters
	 * @param spectatorArea Area where patron are located, in cubic meters
	 * @param numSecurity Number of security for protection
	 * @param basePrice Base cost to rent venue
	 * @param lev The alert level of venue
	 */
   public SportsVenue(String name, double competitorArea, double spectatorArea,
		              int numSecurity, double basePrice, int lev)
	{
	super(name, competitorArea +spectatorArea, basePrice,  lev);
	this.competitorArea = 	competitorArea;
	this.spectatorArea = spectatorArea;
	this.numSecurity=numSecurity;

	}
   
   public double getSize()
   {
	   return competitorArea+ spectatorArea;
   }
   
   public int countSecurity()
   {
	   return numSecurity;
   }
   
   

	public double getCompArea()
	{
		return competitorArea;
	}
	
	public double getSpecArea()
	{
		return spectatorArea;
				
	}
	

	
	public int getNumSecurity()
	{
		return numSecurity;
	}
	
	
	public void setCompArea(double competitorArea)
	{
		
		this.competitorArea =competitorArea;
	}
	
	public void setSpecArea(double spectatorArea)
	{
		
		this.spectatorArea =spectatorArea;
	}
	

	
	public void setNumSecurity(int numSecurity)
	{
		
		this.numSecurity =numSecurity;
	}

	
	public double getEstimate(String type)
	{
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
		if (type.equals("TRAINING"))
			price += trainPrep;
	
		
		//System.out.println(this.getName()+":estimate  to hold a "+type +" is "+ price);
		return price;

	}
	   public String toString()
	   {
	   	return "ID:"+this.getId()+";"+this.getName() +";#Events:"+this.getApprovedEvents().size()+";Compet Area:"+competitorArea+";Spec Area:"+spectatorArea+";#Sec:"+numSecurity;
	   	
	   }
	   public String toFile()
	   {
		   	return ""+this.getId()+";"+this.getName() +";"+this.getApprovedEvents().size()+";"+competitorArea+";"+spectatorArea+";"+numSecurity;
		   	
		   }

	/** Updates a venue of this venue type
	* @param scan input scanner to read input stream from screen
	*/
	@Override
	public void updateLocalData(Scanner scan) {

   		// SportsVenue(String name, double competitorArea, double spectatorArea,
		//		              int numSecurity, double basePrice, int lev)

		scan.nextLine();
		String venueName = getName();
		double currentSize = getSize();
		double size = 0;

		double currentBasePrice = getPrice();
		double basePrice = 0;

		int currentLevel = getLevel();
		int level = 0;

		double currentCompetitorArea = getCompArea();
		double competitorArea = 0;

		double currentSpectatorArea = getSpecArea();
		double spectatorArea = 0;

		int currentNumSec = getNumSecurity();
		int numSec = 0;

		System.out.println("Hit enter to keep name as ["+venueName+"], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = venueName;

		System.out.println("Hit enter to keep size at ["+currentSize +"] or enter new size:");
		String sizeEntry = scan.nextLine();

		if (sizeEntry.equals(""))
			size = currentSize;
		else
			size = Double.parseDouble(sizeEntry);

		System.out.println("Hit enter to keep base price at ["+currentBasePrice+"] or enter new base price:");
		String basePriceEntry = scan.nextLine();

		if (basePriceEntry.equals(""))
			basePrice = currentSize;
		else
			basePrice = Double.parseDouble(basePriceEntry);

		System.out.println("Hit enter to keep alert level at ["+currentLevel+"] or enter new alert level:");
		String levelEntry = scan.nextLine();

		if (levelEntry.equals(""))
			level = currentLevel;
		else
			level = Integer.parseInt(levelEntry);

		System.out.println("Hit enter to keep the competitor area at ["+ currentCompetitorArea+"] or enter new area:");
		String competitorEntry = scan.nextLine();

		if (competitorEntry.equals(""))
			competitorArea = currentCompetitorArea;
		else
			competitorArea = Double.parseDouble(competitorEntry);

		System.out.println("Hit enter to keep the spectator area at ["+currentSpectatorArea +"] or enter new area:");
		String spectatorEntry = scan.nextLine();

		if (spectatorEntry.equals(""))
			spectatorArea = currentSpectatorArea;
		else
			spectatorArea = Double.parseDouble(spectatorEntry);

		System.out.println("Hit enter to keep the number of security at ["+currentNumSec +"] or enter new number:");
		String secEntry = scan.nextLine();

		if (secEntry.equals(""))
			numSec = currentNumSec;
		else
			numSec = Integer.parseInt(secEntry);


		setName(name);
		setSize(size);
		setPrice(basePrice);
		setLevel(level);
		setNumSecurity(numSec);
		setCompArea(competitorArea);
		setSpecArea(spectatorArea);
	}
}
