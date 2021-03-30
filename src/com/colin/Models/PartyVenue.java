package com.colin.Models;

import com.colin.Models.Venue;

import java.util.Scanner;

/**
 * A venue to party !
 */
public class PartyVenue extends Venue {
	double stageArea;
	double barArea, foodArea;
	int numSecurity;


	/**
	 * Creates a party venue
	 * @param name Name of party venue
	 * @param stageArea Stage size of party venue in cubic meters
	 * @param barArea Bar size of party venue in cubic meters
	 * @param foodArea Food Vicinity size of party venue in cubic meters
	 * @param numSecurity Number of security for protection of patrons on venue
	 * @param basePrice Base price to rent venue
	 * @param lev Alert level of venue
	 */
	public PartyVenue(String name, double stageArea,	double barArea, 
			           double foodArea,int numSecurity, double basePrice,
			            int lev)
	{
		super( name, stageArea+barArea+foodArea, basePrice, lev);
		this.numSecurity=numSecurity;
		this.stageArea = 	stageArea;
		this.barArea = barArea;
		this.foodArea = foodArea;
		
		
		
	}
	
	public double getEstimate(String type)
	{
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
		if (type.equals("SPORT"))
			price += sportsPrep;
		if (type.equals("TRAINING"))
			price += trainPrep;
	
		
		//System.out.println(this.getName()+":estimate  to hold a "+type +" is "+ price);
		return price;

	}
	
	public double getCurrStage()
	{
		return stageArea;
	}
	
	public double getBarArea()
	{
		return barArea;
	}
	
	public double getFoodArea()
	{
		return foodArea;
	}
	
	
	public int getNumSecurity()
	{
		return numSecurity;
	}
	
	public void setStageArea(double stageArea)
	{
		
		this.stageArea =stageArea;
	}
	
	public void setFoodArea(double foodArea)
	{
		
		this.foodArea =foodArea;
	}
	
	public void setBarArea(double barArea)
	{
		
		this.barArea =barArea;
	}	
	
	public void setNumSecurity(int numSecurity)
	{
		
		this.numSecurity =numSecurity;
	}

	
	   public String toString()
	   {
	   	return "ID:"+this.getId()+";"+this.getName() +";#Events:"+this.getApprovedEvents().size()+";Stage Area:"+stageArea+";Bar Area:"+barArea+";Food Area:"+foodArea+";#Sec"+numSecurity;
	   	
	   }
	   public String toFile()
	   {
		   	return ""+this.getId()+";"+this.getName() +";"+this.getApprovedEvents().size()+";"+stageArea+";"+barArea+";"+foodArea+";"+numSecurity;
		   	
		   }

	/** Updates a venue of this venue type
	* @param scan input scanner to read input stream from screen
	*/
	@Override
	public void updateLocalData(Scanner scan) {
		scan.nextLine();
		String venueName = getName();
		double currentSize = getSize();
		double size = 0;

		double currentBasePrice = getPrice();
		double basePrice = 0;

		int currentLevel = getLevel();
		int level = 0;

		double currentStageArea = getCurrStage();
		double stageArea = 0;

		double currentBarArea = getBarArea();
		double barArea = 0;

		double currentFoodArea = getFoodArea();
		double foodArea = 0;

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

		System.out.println("Hit enter to keep alert level at ["+currentLevel+"] or enter a new alert level:");
		String levelEntry = scan.nextLine();

		if (levelEntry.equals(""))
			level = currentLevel;
		else
			level = Integer.parseInt(levelEntry);

		System.out.println("Hit enter to keep the stage area at ["+currentStageArea +"] or enter new size:");
		String stageEntry = scan.nextLine();

		if (stageEntry.equals(""))
			stageArea = currentStageArea;
		else
			stageArea = Double.parseDouble(stageEntry);

		System.out.println("Hit enter to keep the bar area at ["+currentBarArea +"] or enter new area:");
		String barEntry = scan.nextLine();

		if (barEntry.equals(""))
			barArea = currentBarArea;
		else
			barArea = Double.parseDouble(barEntry);

		System.out.println("Hit enter to keep the food area at ["+currentFoodArea +"] or enter new area:");
		String foodEntry = scan.nextLine();

		if (foodEntry.equals(""))
			foodArea = currentFoodArea;
		else
			foodArea = Double.parseDouble(foodEntry);

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
		setStageArea(stageArea);
		setBarArea(barArea);
		setFoodArea(foodArea);
		setNumSecurity(numSec);
	}
}
