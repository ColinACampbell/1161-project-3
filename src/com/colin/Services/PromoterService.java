package com.colin.Services;

import com.colin.Models.Ministry;
import com.colin.Models.Promoter;
import com.colin.Models.Venue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PromoterService {

    private static PromoterService promoterService;
    private ArrayList<Promoter> promoters = new ArrayList<>();
    private final File dbFile;

    public PromoterService()
    {
        // Set Up File and the directory which we want it to be in if it does not exists
        File dbDir = new File("database-dir");
        if (!dbDir.exists())
            dbDir.mkdir();

        dbFile = new File(dbDir,"promoters.txt");
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadPromotersFromDB();
    }

    /**
     * Looks for promoter in the loaded list of promoters
     * @param id Promoter id
     * @return Promoter if found, null if not found
     */
    public Promoter findPromoter(int id)
    {
        for (Promoter promoter : promoters)
        {
            if (promoter.getId() == id)
                return promoter;
        }

        return null;
    }

    /**
     * Updates a Promoter's information give his id
     * @param id Id Of Promoter
     * @param name Name of Promoter
     * @param phone Phone of Promoter
     * @param email Email of Promoter
     * @param address Address of Promoter
     * @param budget Budget of Promoter
     * @return Promoter that was updated
     */
    public Promoter updatePromoter(int id, String name,String phone, String email, String address,double budget)
    {
        Promoter promoter = findPromoter(id);
        if (promoter == null)
            return null;

        promoter.setName(name);
        promoter.setPhone(phone);
        promoter.setEmail(email);
        promoter.setAddress(address);
        promoter.setBudget(budget);

        promoters.remove(promoter); // Remove then add him/her back
        promoters.add(promoter);

        try {
            persist();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return promoter;
    }

    /**
     * Deletes a Promoter given the id and updates the database
     * @param id ID Of Promoter
     * @return Promoter delete, null if not found to be deleted
     */
    public Promoter deletePromoter(int id)
    {
        Promoter promoter = findPromoter(id);
        if (promoter == null)
            return null;
        promoters.remove(promoter);
        try {
            persist();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return promoter;
    }

    /**
     * Add's a promoter to the service then updated the database
     * @param promoter Promoter to be added
     */
    public void addPromoter(Promoter promoter)
    {
        promoters.add(promoter);
        try {
            persist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Promoter> getPromoters() {

        return promoters;
    }

    /**
     * This loads promoters from the file into memory
     */
    private void loadPromotersFromDB()
    {
        ArrayList<Promoter> loadedPromoters = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(dbFile);
            while (scanner.hasNext())
            {
                String[] promoterAttributes = scanner.nextLine().split("##@");
                int id = Integer.parseInt(promoterAttributes[0]);
                String promoterName = promoterAttributes[1];
                String promoterPhone = promoterAttributes[2];
                String promoterEmail = promoterAttributes[3];
                String promoterAddress = promoterAttributes[4];
                double promoterBudget = Double.parseDouble(promoterAttributes[5]);

                Promoter promoter = new Promoter(promoterName,promoterPhone,promoterEmail,promoterAddress,promoterBudget,new Ministry("Yes Sah",1),new ArrayList<Venue>());
                promoter.overrideID(id);
                loadedPromoters.add(promoter);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        promoters = loadedPromoters;
    }

    /***
     * Saves the data store in memory permanently on the disk
     * **/
    private void persist() throws IOException {
        dbFile.delete(); // delete
        dbFile.createNewFile(); // the create new file
        PrintWriter printWriter = new PrintWriter(dbFile);
        for (Promoter promoter : promoters)
        {
            int id = promoter.getId();
            String name = promoter.getName();
            String phone = promoter.getPhone();
            String email = promoter.getEmail();
            String address = promoter.getAddress();
            double budget = promoter.getBudget();

            printWriter.println(id+"##@"+name+"##@"+phone+"##@"+email+"##@"+address+"##@"+budget);
        }

        printWriter.close(); // no screw ups XD
    }
}
