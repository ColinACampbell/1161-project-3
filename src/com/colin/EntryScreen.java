package com.colin;

import com.colin.Models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Represents interface to interact with the program
 */
public class EntryScreen {

	public EntryScreen() {}

	public ArrayList<Promoter> managePromoters(Scanner scan, ArrayList<Promoter> proms, Ministry min, ArrayList<Venue> vens) throws NumberFormatException
	{
		ReportScreen r = new ReportScreen();
		char mchoice = 'c';
		String menu="";
		while (mchoice!='X')
		{
			String menuOptions = "[A]dd/Create promoter\n[E]dit/Update promoter\n";
			menuOptions+="[L]ist/Read promoters\n[D]elete promoter\nE[x]it\n";
			System.out.println(menuOptions);
			menu = scan.next().toUpperCase();
			mchoice = menu.charAt(0);
			switch(mchoice)
			{
			case 'A':{
				Promoter p = createPromoter(scan, min, vens);
				if (p!=null)
					proms.add(p);
				break;
			}
			case 'L': {
				Collections.sort(proms); 
				r.listPromoters(proms, System.out);
				break;
			}
			case 'E':{
				System.out.println("Please enter the ID of the promoter to be updated:");
				int pid = Integer.parseInt(scan.next());
				int pdx = findPromoter(proms,pid);
				if (pdx>=0)
					proms.get(pdx).updateLocalData(scan);
				else
					System.out.println("Promoter with id "+pid+ " not found.");
				break;
			}
			case 'D':{
				System.out.println("Please enter the ID of the promoter to be deleted:");
				int pid = Integer.parseInt(scan.next());
				int pdx = findPromoter(proms,pid);

				if (pdx>=0)
					proms.remove(pdx);
				else
					System.out.println("Promoter with id "+pid+ " not found.");
				break;
			}


			}

		}
		return proms;
	}



	public Promoter createPromoter( Scanner scan, Ministry min, ArrayList<Venue> vens)
	{
		Promoter p = null;
		try
		{
			System.out.println("Please enter Promoter Name:");
			String name = scan.next();
			System.out.println("Please enter Promoter Budget:");
			double budget = Double.parseDouble(scan.next());
			p = new Promoter(name,"","","", budget, min, vens);
		}
		catch(NumberFormatException nf) {}
		
		return p;
	}


	public int findPromoter(ArrayList<Promoter> proms, int pid)
	{
		int pdx =-1;
		int currdx=0;
		while ((currdx<proms.size())&&(pdx==-1))
		{
			if (proms.get(currdx).getId()==pid)
				pdx = currdx;
			currdx++;

		}

		return pdx;

	}

	/** Screen to manage venue(s)
	* @param scan Input stream to read data from screen
	* @param vens List of venues to be manipulated, read, or deleted
	* @return A list of venues that have been created, read or updated
	 */
	public ArrayList<Venue> manageVenues(Scanner scan, ArrayList<Venue> vens)
	{
		ReportScreen r = new ReportScreen();

		String menu = "[A]dd/Create venue\n";
		menu += "[E]dit/Update venue\n";
		menu += "[L]ist/Read Venues\n";
		menu += "[D]elete venue\n";
		menu += "E[x]it\n";

		char choice = 0;
		while (choice != 'X')
		{
			System.out.println(menu);

			choice = scan.next().toUpperCase().charAt(0);

			switch (choice)
			{
				case 'A' :
				{
					Venue venue = createVenue(scan);
					vens.add(venue);
					break;
				}
				case 'L':
				{
					r.listVenues(vens,System.out);
					break;
				}
				case 'E':
				{
					System.out.println("Please enter the ID of the venue to be updated:");
					int venID = Integer.parseInt(scan.next());
					int pos = findVenue(vens,venID);

					if (pos>=0)
						vens.get(pos).updateLocalData(scan);
					else
						System.out.println("Venue with id "+venID+ " not found.");

					break;
				}
				case 'D':
				{
					System.out.println("Please enter the ID of the venue to be deleted:");
					int venID = Integer.parseInt(scan.next());
					int pos = findVenue(vens,venID);
					if (pos >= 0 )
						//vens.get(pos).remove(); // TODO : What's wrong ??
						continue;
					else
						System.out.println("Venue with id "+venID+ " not found.");
					break;
				}
				default:
					break;
			}
		}

		//code required here to implement a venue management screen
		return vens;
	}



	/** Provides interface to create venue
	* @param scan Input stream to read data from screen
	* return A venue that have been created
	*/
	public Venue createVenue( Scanner scan)
	{
		Venue v = null;
		char mchoice = 'c';
		String menu= "General Purpose [V]enue\n";
		menu += "[P]arty Venue\n";
		menu += "[S]port venue\n";
		menu += "[T]rain venue\n";
		menu += "E[x]it to previous menu\n";

		while (mchoice != 'X')
		{
			System.out.println(menu);

			mchoice = scan.next().toUpperCase().charAt(0);

			switch (mchoice)
			{
				case 'V' : // General purpose venue

					try {

						System.out.println("Enter The Name of the Venue");
						String name = scan.next();

						System.out.println("Enter The Size of the Venue");
						int size = scan.nextInt();

						System.out.println("Enter The Base Price of the Venue");
						double basePrice = scan.nextDouble();

						System.out.println("Enter The Alert Level of the Venue");
						int level = scan.nextInt();
						v = new Venue(name,size,basePrice,level);

					} catch (NumberFormatException e)
					{
						System.out.println("Wrong Number Format Entered");
					}

					break;
				case 'S' :
					try {
						System.out.println("Enter The Name of the Venue");
						String name = scan.nextLine();

						System.out.println("Enter The Competitor Area of the Venue");
						double competitorArea = scan.nextDouble();

						System.out.println("Enter The Spectator Area of the Venue");
						double spectatorArea = scan.nextDouble();

						System.out.println("Enter The Number of Security on the Venue");
						int numSecurity = scan.nextInt();

						System.out.println("Enter The Base Price of the Venue");
						double basePrice = scan.nextDouble();

						System.out.println("Enter The Alert Level of the Venue");
						int level = scan.nextInt();
						v = new SportsVenue(name,competitorArea,spectatorArea,numSecurity,basePrice,level);

					} catch (NumberFormatException e)
					{
						System.out.println("Wrong Number Format Entered");
					}
					break;

				case 'T' :
					try {
						System.out.println("Enter The Name of the Venue");
						String name = scan.nextLine();

						System.out.println("Enter The Instructor Area of the Venue");
						double instructorArea = scan.nextDouble();

						System.out.println("Enter The Other Area of the Venue");
						double otherArea = scan.nextDouble();

						System.out.println("Enter The Base Price of the Venue");
						double basePrice = scan.nextDouble();

						System.out.println("Enter The Alert Level of the Venue");
						int level = scan.nextInt();
						v = new TrainingVenue(name,instructorArea,otherArea,basePrice,level);

					} catch (NumberFormatException e)
					{
						System.out.println("Wrong Number Format Entered");
					}
					break;
				default:
					break;
			}
		}

	    //code needed to obtain the information required to represent a venue and create an appropriate venue object
		return v;
	}



	public int findVenue(ArrayList<Venue> vens, int vid)
	{
		int vdx =-1;
		int currdx=0;

		while ((currdx<vens.size()) && (vdx==-1))
		{
			if (vens.get(currdx).getId()==vid)
				vdx = currdx;
			currdx++;

		}

		return vdx;

	}



}

