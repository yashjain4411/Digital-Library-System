/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.ParseException;
/**
 *
 * @author Lenovo
 */
public class date {
    public static void main(String args[]) throws java.text.ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
    }
}
