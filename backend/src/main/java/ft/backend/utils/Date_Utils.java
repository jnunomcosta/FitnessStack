package ft.backend.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Date_Utils {

    public static String generateCode(){
        return Long.toHexString(new Date().getTime()) + Integer.toHexString(new Random().nextInt(9));
    }
    
    public static void main(final String[] args)
    {
        System.out.println(generateCode());
    }

}
