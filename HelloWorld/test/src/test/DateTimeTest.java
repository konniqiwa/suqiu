package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeTest {
    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String s = format.format(ca.getTime());
        System.out.println(s);

        String str = "helloworld";

    }


}
