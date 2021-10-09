package com.wust.java8.time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeTest {
    /**
     * LocalDateTime
     */
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2021, 10, 21, 14, 45, 12);
        System.out.println(ldt2);

        //时间相加
        LocalDateTime ldt4 = ldt.plusYears(1);
        System.out.println(ldt4);
    }

    /**
     * Instant:时间戳
     */
    @Test
    public void test2(){
        //获取的是UTC时间，比如美国，和中国相差8个小时
        Instant now = Instant.now();
        System.out.println(now);
        //转为时间戳
        System.out.println(now.toEpochMilli());

        OffsetDateTime odt = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

    }

    /**
     * Duration:计算两个时间之间的间隔
     */
    @Test
    public void test3() throws InterruptedException {
        //获取两个时间戳之间的间隔
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between.toMillis());

        LocalTime s = LocalTime.now();
        Thread.sleep(1000);
        LocalTime e = LocalTime.now();
        System.out.println(Duration.between(s,e).toMillis());
    }

    /**
     * Period:计算两个日期之间的间隔
     */
    @Test
    public void test4(){
        LocalDate start = LocalDate.of(2019,10,1);
        LocalDate end = LocalDate.now();
        Period between = Period.between(start, end);
        //相隔两年7天
        System.out.println(between);
        //获取相隔的年数
        System.out.println(between.getYears());
        //获取相隔的月数
        System.out.println(between.getMonths());
        //获取相隔的天数
        System.out.println(between.getDays());
    }

    /**
     * TemperalAdjuster:时间纠正器
     */
    @Test
    public void test5(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //改变为指定日期
        LocalDateTime ldt = now.withDayOfMonth(21);
        System.out.println(ldt);

        //如果想计算出下个周天或者是自定义一些特殊时间，比如结婚纪念日
        LocalDateTime with = now.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(with);

        //自定义为计算出下个工作日
        LocalDateTime ldt2 = now.with(t -> {
            LocalDateTime time = (LocalDateTime) t;
            if (time.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                return time.plusDays(3);
            } else if (time.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
                return time.plusDays(2);
            } else {
                return time.plusDays(1);
            }
        });
        System.out.println(ldt2);
    }

    /**
     * DateTimeFormatter:格式化器
     */
    @Test
    public void test6(){
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime now = LocalDateTime.now();
        String format = isoDateTime.format(now);
        System.out.println(format);

        //指定为自定义的格式化格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //格式化
        String s = dateTimeFormatter.format(now);
        System.out.println(s);
        //再次转化为LocalDateTime
        System.out.println(dateTimeFormatter.parse(s));
    }
}
