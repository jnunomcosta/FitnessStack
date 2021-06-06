package ft.backend.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Date_Utils {

    public static String generateCode(){
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, new Random().nextInt(2050 - 1000) + 1000);
        return Long.toHexString(cal.getTime().getTime());
    }
    
    public static void main(final String[] args)
    {
        for(int i = 0 ;i < 100000;i++) {
            String a = generateCode();
            //if (a.length()!=12)
                System.out.println(a);
        }
    }

}
