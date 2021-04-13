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
    private final ArrayList<Promoter> promoters = new ArrayList<>();
    private File dbFile;

    private PromoterService()
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
    }

    public static PromoterService getInstance()
    {
        if (promoterService == null)
            promoterService = new PromoterService();

        return promoterService;
    }


    public void addPromoter(Promoter promoter)
    {
        promoters.add(promoter);
    }

    public ArrayList<Promoter> getPromoters() {
        return promoters;
    }


    public ArrayList<Promoter> loadPromotersFromDB()
    {
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
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return promoters;
    }


    /***
     * Saves the data store in memory permanently on the disk
     * **/
    public void persist() throws IOException {
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
