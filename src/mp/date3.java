/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
/**
 *
 * @author Lenovo
 */
public class date3 {
   public static void main(String args[]) {
      
        Date d=new Date();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String issuedate = simpleDateFormat.format(d);
      Calendar cal=new GregorianCalendar();
      Calendar cal1=new GregorianCalendar();
       cal1.setTime(d);
      cal.setTime(d);
      cal.add(Calendar.DATE, 465);
      Date d1=cal.getTime();
      
     long diff = d1.getTime() - d.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);
      String returndate=simpleDateFormat.format(d1);
      System.out.println(diffDays);
      
      System.out.println(cal.compareTo(cal1));
      
    } 
}
