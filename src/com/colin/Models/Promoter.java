package com.colin.Models;
import com.colin.Plan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class represents someone who organizes events, and spend money on venues
 */
public class Promoter  implements Comparable<Promoter> {
	private String name;
	private String phone;
	private String email;
	private String address;
	private double budget;
	private Ministry min;
	///
	private int id;
	private static int nextid =0;
	private ArrayList<Venue> venues;
	private ArrayList<Event> approvedEvents = new ArrayList<Event>();
	private ArrayList<Plan> plannedEvents = new ArrayList<Plan>();
	
	public Promoter (String name,String phone, String email, String address,double budget, Ministry min, ArrayList<Venue> venues) {

		this.name = name;
		this.budget = budget;
		this.min = min;    
		this.venues= venues;
		this.phone = phone;
		this.email = email;
		this.address = address;
		id = nextid;
		nextid++;
  	}

	
	public int compareTo(Promoter other)
	{
		return this.getName().compareTo(other.getName());
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBudget(double budget)
	{
		this.budget = budget;
	}
	
	public String toString()
	{
		return this.getId()+";"+this.name +";"+this.budget+";"+phone+";"+email+";"+address;
	}
	
	public void addPlan (Plan p)
	{
		plannedEvents.add(p);
	}
	
	public static void resetId()
	{
		
		nextid=0;
	}

	/**
	 * Useful method to understand rest of program
	 * @param scan Provides input stream to read data from screen
	 */
	public void updateLocalData(Scanner scan)
	{
	    scan.nextLine();
		String currname = getName();
		double currBudget = getBudget();
		System.out.println("Hit enter to keep name as ["+currname+"], or enter new name:");
		String name = scan.nextLine();
		if (name.equals(""))
			name = currname;
		System.out.println("Hit enter to keep budget at ["+currBudget +"] or enter new budget:");
		String budentry=scan.nextLine();
		double budget;
		if (budentry.equals(""))
			budget = currBudget;
		else
			budget = Double.parseDouble(budentry);
		setName(name);
		setBudget(budget);

	}
	
	public void payFor(Venue venue, Event event)
	{
		String type = event.getType();
		double cost = venue.getEstimate(type);
		budget -=cost;
	}
	
	public void submitPlans()
	{
		//System.out.println(name + " submitting "+ plannedEvents.size()+ " events.");
		for (Plan pl:plannedEvents)
		{
			int approvid =planEvent(pl.getNumPatrons(), pl.getEventType(),pl.getDate());
			//System.out.println(approvid +":"+ pl.getNumPatrons()+ pl.getEventType()+pl.getDate());
		}
			
	}

	public int planEvent(int numPatrons, String eventType, Date date)
	{
		double minCost =Double.MAX_VALUE;
		Venue selVen = null;
		int returnVal = -1;
		ArrayList<Venue> affordableVens= new ArrayList<Venue>();
		for (Venue ven:venues)
		{
			//System.out.println("checking "+ven.getName());
			if (this.budget >= ven.getEstimate(eventType))
				affordableVens.add(ven);
		}

		if (affordableVens.size()>0)
		{

			for (Venue ven:affordableVens)
			{
				//System.out.println("checking "+ven.getName());
				if (ven.available(date))
				{

					if (ven.canHold(numPatrons))
					{ 
						double eventCost = ven.getEstimate(eventType);
						//System.out.println("Evaluating "+ven.getName()+"@"+eventCost);
						if (eventCost<minCost)
						{
							minCost = eventCost;
							selVen = ven;
						}

					}
				//	else
				//		System.out.println(ven.getName()+" cannot hold "+numPatrons);
				}
				//else
				//	System.out.println(ven.getName()+" not available on "+date);

			}
		}
		if (selVen!=null)
		{
			Event e = new Event(eventType + " FOR " + this.name,eventType,numPatrons, date);
			returnVal = selVen.reserve(e,getBudget(),min);
			if (returnVal>=0)
			{
				System.out.println(this.getName() + " reserved "+e.getVenue().getName()+" for a party on day " +date.getDay());
				e.setVenue(selVen);
				payFor(selVen,e);
				approvedEvents.add(e);

			}
			else
				System.out.println("Unable to reserve  "+selVen.getName()+" for a party on day " +date.getDay()+"("+ this.getName() + ")");

		}
		else 
			returnVal= -1;
		//select venue
		return returnVal;
	}

	/**
	 * Override the id that's assigned to the promoter when created
	 * @param newID new id
	 */
	public void overrideID(int newID)
	{
		this.id = newID;
		nextid = newID+1; // to prevent conflicts
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBudget()
	{
		return budget;
	}

	public String getName()
	{
		return name;
	}


	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
}
