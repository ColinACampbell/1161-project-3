package com.colin;

import com.colin.Models.Ministry;
import com.colin.Models.Promoter;
import com.colin.Screens.*;
import com.colin.Services.PromoterService;
import java.util.ArrayList;

public class Main 
{
    public static void main(String[] args)
    {
        new HomeScreen(); // start the app
    }

    private static void persistPromotersTest()
    {
        PromoterService promoterService = new PromoterService();
        Ministry ministry = new Ministry("Min",1);
        Promoter promoter = new Promoter("John Doe","1234","seefo@mail.com","Some Adddress",200.00,ministry,new ArrayList<>());
        Promoter promoter2 = new Promoter("Bill Mahr","1234","email@main.com","Some Adddress",200.00,ministry,new ArrayList<>());
        promoterService.addPromoter(promoter);
        promoterService.addPromoter(promoter2);

    }

}
