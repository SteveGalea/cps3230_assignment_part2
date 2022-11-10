package com.task1_screenscraper.converters;

public class PriceConverter {

    public int textToCents(String rawPriceText) {
        // only accept numbers and decimal points
        rawPriceText = rawPriceText.replaceAll("[^0-9.]","");

        // escape if string is empty by this point
        if(rawPriceText.length() == 0){
            return -1;
        }

        // conversion euros to cents
        double euros = Double.parseDouble(rawPriceText);
        double cents = euros * 100;
        return (int) cents;
    }
}
