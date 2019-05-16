package org.andy.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author: andy
 * @Date: 2017/11/2 15:56
 * @Description:
 */
public class Java8Tester {
    public static void main(String args[]) {
//        Java8Tester java8tester = new Java8Tester();
//        LocalDate localDate = LocalDate.now();
//        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM")));
       System.out.println(getJasperNextMonth());
//        java8tester.testLocalDateTime();
//        java8tester.testZonedDateTime();
    }
    public static String getJasperNextMonth(){
        Calendar calendar =Calendar.getInstance();
        calendar.set(Calendar.MONTH,11);
        calendar.set(Calendar.DAY_OF_MONTH,23);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if ( day >= 27) {
            calendar.add(Calendar.MONTH,1);
        }
        calendar.set(Calendar.DAY_OF_MONTH , 27);
        return format(calendar.getTime(), "yyyy-MM-dd");
    }
    public static String format(Date date, String pattern) {
        String returnValue = "";

        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    public void testLocalDateTime() {
        Clock c1 = Clock.systemUTC(); // 系统默认UTC时钟（当前瞬时时间
        // System.currentTimeMillis()）
        System.out.println(c1.millis()); // 每次调用将返回当前瞬时时间（UTC）
        Clock c2 = Clock.systemDefaultZone(); // 系统默认时区时钟（当前瞬时时间）
        System.out.println(c2.millis());
        System.out.println("************************************");
       /* LocalDateTime nowDateTime =  LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime expireDateTimpe = nowDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0).plus(1, ChronoUnit.DAYS);
        System.out.println(Duration.between(nowDateTime,expireDateTimpe).getSeconds());*/
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println( Date.from(nowDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println("************************************");




        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);


        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Europe/Paris"));
        System.out.println(now2);// 会以相应的时区显示日期


        LocalDateTime now3 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now3);// 会以相应的时区显示日期
        System.out.println("---------------");
        LocalDate date1 = currentTime.toLocalDate();
        System.out.println(LocalDateTime.parse("2016-12-23 20:15:40",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("当前日期: " +  date1.plus(1, ChronoUnit.YEARS));
        System.out.println(currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int hour=currentTime.getHour();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds + ", 时: " + hour);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012).plusDays(3);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15, 33);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);


    }

    public void testZonedDateTime(){

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        String formattedZonedDate = formatter.format(ZonedDateTime.now());
        System.out.println("formattedZonedDate = " + formattedZonedDate);
    }

    public static LocalDateTime UDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate UDateToLocalDate(Date date) {
        return UDateToLocalDateTime(date).toLocalDate();
    }

    public static LocalTime UDateToLocalTime(Date date) {
        return UDateToLocalDateTime(date).toLocalTime();
    }


    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return  Date.from(instant);
    }
    // 取今天：2018-03-18
    LocalDate today = LocalDate.now();

    // 取本月第1天： 2018-03-01
    LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());

    // 取本月第10天：2018-03-10
    LocalDate tenDayOfThisMonth = today.withDayOfMonth(10);

    // 取本月最后一天（自动识别28、29、30、31）：2018-03-31
    LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());

    // 取下一天：2018-03-19
    LocalDate lastDay = today.plusDays(1);

    // 取这个月第一个周一：2018-03-05
    LocalDate firstMondayOfThisMonth = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));

}
