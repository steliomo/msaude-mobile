package mz.co.msaude.mobile.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stélio Moiane on 6/11/17.
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }

    public static String format(Date date) {
        return dateFormat.format(date);
    }

    public static Date parse(String date) {
        Date finalDate = null;

        try {
            finalDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finalDate;
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
