package com.colin.Models;

import com.colin.Models.Venue;

import java.util.Scanner;

/**
 * A type of venue built for training
 */
public class TrainingVenue extends Venue {
	private double instructorArea;
	private double otherArea;


	/**
	 * Creates a training venue, initializes logic
	 * @param name Name of venue
	 * @param instructorArea Size of instructor area in cubic meters
	 * @param otherArea Size where patrons are located, in cubic meters
	 * @param basePrice Base price of the venue
	 * @param lev The alter level of the venue
	 */
	public TrainingVenue(String name, double instructorArea, double otherArea,
		double basePrice,int lev)
{
super(name, instructorArea +otherArea, basePrice,  lev);
this.instructorArea = 	instructorArea;
this.otherArea = otherArea;

}
public double getEstimate(String type)
{
		double price = basePrice;
		if (type.equals("PARTY"))
			price += partyPrep;
        if (type.equals("SPORT"))
				price += sportsPrep;

		//System.out.println(this.getName()+":estimate  to hold a "+type +" is "+ price);
		return price;

	}



public double getInstructorArea()
{
	return instructorArea;
}

public double getOtherArea()
{
	return otherArea;
			
}



public void setInstructorArea(double instructorArea)
{
	
	this.instructorArea =instructorArea;
}

public void setOtherArea(double otherArea)
{
	
	this.otherArea =otherArea;
}

public String toString()
{
	return "ID:"+this.getId()+";"+this.getName() +";#Events:"+this.getApprovedEvents().size()+";Inst.Area"+instructorArea+";Oth.Area"+otherArea;
	
}
public String toFile()
{
	return ""+this.getId()+";"+this.getName() +";"+this.getApprovedEvents().size()+";"+instructorArea+";"+otherArea;
	
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

		double currentInstructorArea = getInstructorArea();
		double instructorArea = 0;

		double currentOtherArea = getOtherArea();
		double otherArea = 0;

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

		System.out.println("Hit enter to keep the instructor area at ["+currentInstructorArea +"] or enter new size:");
		String instructorAreaEntry = scan.nextLine();

		if (instructorAreaEntry.equals(""))
			instructorArea = currentInstructorArea;
		else
			instructorArea = Double.parseDouble(instructorAreaEntry);

		System.out.println("Hit enter to keep the other area at ["+currentOtherArea +"] or enter new area:");
		String otherAreaEntry = scan.nextLine();

		if (otherAreaEntry.equals(""))
			otherArea = currentOtherArea;
		else
			otherArea = Double.parseDouble(otherAreaEntry);


		setName(name);
		setSize(size);
		setPrice(basePrice);
		setLevel(level);
		setInstructorArea(instructorArea);
		setOtherArea(otherArea);
	}
}

