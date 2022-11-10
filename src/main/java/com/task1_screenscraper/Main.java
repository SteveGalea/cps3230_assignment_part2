package com.task1_screenscraper;

import com.task1_screenscraper.facade.Facade;

public class Main {
    static Facade facade;

    public static void setFacade(Facade facade) {
        Main.facade = facade;
    }

    public static void main(String args[]){
        if(facade == null){
            // can never achieve 100% line coverage because this requires the creation of a facade mock
            facade = new Facade();
        }
        facade.scrapeAndUploadXAlertsUsingKeyword(5, "Laptop");
    }

}
