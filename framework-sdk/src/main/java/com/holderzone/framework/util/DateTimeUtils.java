package com.holderzone.framework.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Mr.Q
 * @date 2019/12/25 22:54
 * desc：
 */
public final class DateTimeUtils {
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_MILL_SECONDS = "yyyy-MM-ddTHH:mm:ss.fffZ";

    private DateTimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static ZoneId chinaZoneId() {
        return ZoneId.of("Asia/Shanghai");
    }

    public static ZoneOffset chinaZoneOffset() {
        return ZoneOffset.of("+8");
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(chinaZoneId());
    }

    public static long nowMillis() {
        return Instant.now(Clock.system(chinaZoneId())).toEpochMilli();
    }

    public static String nowString() {
        return nowString("yyyy-MM-dd HH:mm:ss");
    }

    public static String nowString(String pattern) {
        return now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static long localDateTime2Mills(LocalDateTime localDateTime) {
        return localDateTime.toInstant(chinaZoneOffset()).toEpochMilli();
    }

    public static long localDate2Mills(LocalDate localDate) {
        return localDate.atStartOfDay().toInstant(chinaZoneOffset()).toEpochMilli();
    }

    public static String localDate2String(LocalDate localDate) {
        return localDate2String(localDate, "yyyy-MM-dd");
    }

    public static String localDateTime2String(LocalDateTime localDateTime) {
        return localDateTime2String(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String localDate2String(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String localDateTime2String(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String mills2String(long mills) {
        return mills2String(mills, "yyyy-MM-dd HH:mm:ss");
    }

    public static String mills2String(long mills, String pattern) {
        return mills2LocalDateTime(mills).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime mills2LocalDateTime(long mills) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(mills), chinaZoneId());
    }

    public static long string2Mills(CharSequence date) {
        return string2Mills(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static long string2Mills(CharSequence date, String pattern) {
        return string2LocalDateTime(date, pattern).toInstant(chinaZoneOffset()).toEpochMilli();
    }

    public static LocalDateTime string2LocalDateTime(CharSequence date) {
        return string2LocalDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime string2LocalDateTime(CharSequence date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth) {
        return LocalDateTime.of(year, month, dayOfMonth, 0, 0);
    }

    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public static LocalDateTime of(String dateTime) {
        return dateTime.length() <= 10 ? LocalDate.parse(dateTime).atStartOfDay() : LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean isCurrentYear(LocalDateTime localDateTime) {
        return localDateTime.getYear() == now().getYear();
    }

    public static boolean isCurrentMonth(LocalDateTime localDateTime) {
        LocalDateTime now = now();
        return localDateTime.getYear() == now.getYear() && localDateTime.getMonthValue() == now.getMonthValue();
    }

    public static boolean isCurrentDay(LocalDateTime localDateTime) {
        LocalDateTime now = now();
        return localDateTime.getYear() == now.getYear() && localDateTime.getMonthValue() == now.getMonthValue() && localDateTime.getDayOfMonth() == now.getDayOfMonth();
    }

    public static boolean isCurrentHour(LocalDateTime localDateTime) {
        LocalDateTime now = now();
        return localDateTime.getYear() == now.getYear() && localDateTime.getMonthValue() == now.getMonthValue() && localDateTime.getDayOfMonth() == now.getDayOfMonth() && localDateTime.getHour() == now.getHour();
    }

    public static LocalDateTime beginTimeOfLastHour(LocalDateTime localDateTime) {
        return beginTimeOfHour(localDateTime).minusHours(1L);
    }

    public static LocalDateTime endTimeOfLastHour(LocalDateTime localDateTime) {
        return endTimeOfHour(localDateTime).minusHours(1L);
    }

    public static LocalDateTime beginTimeOfHour(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(localDateTime.getHour(), 0));
    }

    public static LocalDateTime endTimeOfHour(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(localDateTime.getHour(), 59, 59, 999999999));
    }

    public static LocalDateTime reachedTimeOfHour(LocalDateTime localDateTime) {
        LocalDateTime endTimeOfHour = endTimeOfHour(localDateTime);
        LocalDateTime now = now();
        return endTimeOfHour.isBefore(now) ? endTimeOfHour : now;
    }

    public static LocalDateTime beginTimeOfNextHour(LocalDateTime localDateTime) {
        return beginTimeOfHour(localDateTime).plusHours(1L);
    }

    public static LocalDateTime endTimeOfNextHour(LocalDateTime localDateTime) {
        return endTimeOfHour(localDateTime).plusHours(1L);
    }

    public static LocalDateTime beginTimeOfLastDay(LocalDateTime localDateTime) {
        return beginTimeOfDay(localDateTime).minusDays(1L);
    }

    public static LocalDateTime endTimeOfLastDay(LocalDateTime localDateTime) {
        return endTimeOfDay(localDateTime).minusDays(1L);
    }

    public static LocalDateTime beginTimeOfDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay();
    }

    public static LocalDateTime endTimeOfDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(LocalTime.MAX);
    }

    public static LocalDateTime reachedTimeOfDay(LocalDateTime localDateTime) {
        LocalDateTime endTimeOfDay = endTimeOfDay(localDateTime);
        LocalDateTime now = now();
        return endTimeOfDay.isBefore(now) ? endTimeOfDay : now;
    }

    public static LocalDateTime beginTimeOfNextDay(LocalDateTime localDateTime) {
        return beginTimeOfDay(localDateTime).plusDays(1L);
    }

    public static LocalDateTime endTimeOfNextDay(LocalDateTime localDateTime) {
        return endTimeOfDay(localDateTime).plusDays(1L);
    }

    public static LocalDateTime beginTimeOfLastMonth(LocalDateTime localDateTime) {
        return beginTimeOfMonth(localDateTime).minusMonths(1L);
    }

    public static LocalDateTime endTimeOfLastMonth(LocalDateTime localDateTime) {
        return endTimeOfMonth(localDateTime).minusMonths(1L);
    }

    public static LocalDateTime beginTimeOfMonth(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
    }

    public static LocalDateTime endTimeOfMonth(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);
    }

    public static LocalDateTime reachedTimeOfMonth(LocalDateTime localDateTime) {
        LocalDateTime lastDayOfMonth = endTimeOfMonth(localDateTime);
        LocalDateTime now = now();
        return lastDayOfMonth.isBefore(now) ? lastDayOfMonth : now;
    }

    public static LocalDateTime beginTimeOfNextMonth(LocalDateTime localDateTime) {
        return beginTimeOfMonth(localDateTime).plusMonths(1L);
    }

    public static LocalDateTime endTimeOfNextMonth(LocalDateTime localDateTime) {
        return endTimeOfMonth(localDateTime).plusMonths(1L);
    }

    public static LocalDateTime beginTimeOfLastYear(LocalDateTime localDateTime) {
        return beginTimeOfYear(localDateTime).minusYears(1L);
    }

    public static LocalDateTime endTimeOfLastYear(LocalDateTime localDateTime) {
        return endTimeOfYear(localDateTime).minusYears(1L);
    }

    public static LocalDateTime beginTimeOfYear(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().with(TemporalAdjusters.firstDayOfYear()).atStartOfDay();
    }

    public static LocalDateTime endTimeOfYear(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().with(TemporalAdjusters.lastDayOfYear()).atTime(LocalTime.MAX);
    }

    public static LocalDateTime beginTimeOfNextYear(LocalDateTime localDateTime) {
        return beginTimeOfYear(localDateTime).plusYears(1L);
    }

    public static LocalDateTime endTimeOfNextYear(LocalDateTime localDateTime) {
        return endTimeOfYear(localDateTime).plusYears(1L);
    }

    public static long parseStrDate(String date, String pattern) {
        if (!StringUtils.isEmpty(date) && !StringUtils.isEmpty(pattern)) {
            DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime parse = LocalDateTime.parse(date, ftf);
            return LocalDateTime.from(parse).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        } else {
            return 0L;
        }
    }

    public static String parseLong2Str(Long time, String pattern) {
        if (time == null) {
            return "";
        } else {
            DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
            return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("Asia/Shanghai")));
        }
    }
}

