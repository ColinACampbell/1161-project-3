package com.colin;

import com.colin.Models.Ministry;
import com.colin.Models.Promoter;
import com.colin.Models.Venue;
import com.colin.Screens.*;
import com.colin.Services.PromoterService;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main 
{
    public static void main(String[] args)
    {
        //new HomeScreen(); // start the app
        PromoterService promoterService = PromoterService.getInstance();
        promoterService.loadPromotersFromDB();
        //persistPromotersTest();
    }

    private static void persistPromotersTest()
    {
        PromoterService promoterService = PromoterService.getInstance();
        Ministry ministry = new Ministry("Min",1);
        Promoter promoter = new Promoter("John Doe","1234","seefo@mail.com","Some Adddress",200.00,ministry,new ArrayList<>());
        Promoter promoter2 = new Promoter("Bill Mahr","1234","email@main.com","Some Adddress",200.00,ministry,new ArrayList<>());
        promoterService.addPromoter(promoter);
        promoterService.addPromoter(promoter2);
        try {
            promoterService.persist();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Promoter promoter3 = new Promoter("Big Boss","(858) 484 4884","email2@mail.com","Big Address",200.00,ministry,new ArrayList<>());
        try {
            promoterService.addPromoter(promoter3);
            promoterService.persist();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
