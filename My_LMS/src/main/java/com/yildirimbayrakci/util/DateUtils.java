/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yildirimbayrakci.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YILDIRIM
 */
public class DateUtils {
    
    private static final SimpleDateFormat slashFormatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat hyphenFormatter = new SimpleDateFormat("dd-MM-yyyy");
    
    // read a date String and parse to a Date
    public static Date parseDate(String dateString) throws ParseException{
       
            return slashFormatter.parse(dateString);
    }
    
    // read a Date and convert to String
    public static String formatDate(Date date){
        
        String result = null;
        
        if(date != null)
            result = slashFormatter.format(date);
        
        return result;
    }
    
    public  static String formatDateHyphen(Date date){
    
        String result = null;
        if(date != null)
            result = hyphenFormatter.format(date);
        
        return result;
    }
    
    
    public static  Date addDays(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

}
