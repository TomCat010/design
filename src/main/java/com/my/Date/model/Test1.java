package com.my.Date.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test1 {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        Calendar cld = Calendar.getInstance(Locale.CHINA);
        cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        //cld.setTimeInMillis(System.currentTimeMillis());//当前时间
        cld.setTime(df.parse("2022-11-09"));
        cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        Date one = cld.getTime();
        System.out.println(df.format(one));
        cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周日
        Date sun = cld.getTime();
        System.out.println(df.format(sun));
        //-----------------------------------
        System.out.println(System.currentTimeMillis());
    }
}
