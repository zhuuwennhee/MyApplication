package com.life.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTimeUtils {

    private static final Logger LOGGER = Logger.getLogger(DateTimeUtils.class.getName());

    public static String dateToddMMyyyy(Date date) {
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            result = df.format(date);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return result;
    }

    public static String dateToddMMyyyyHHmm(Date date) {
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            result = df.format(date);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return result;
    }

    public static String dateToddMMyyyyHHmmss(Date date) {
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            result = df.format(date);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return result;
    }

    public static Date ddMMyyyyHHmmssToDate(String ddMMyyyyHHmmss) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ddMMyyyyHHmmss);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return date;
    }

    public static Date ddMMyyyyHHmmToDate(String ddMMyyyyHHmm) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(ddMMyyyyHHmm);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return date;
    }

    public static Date ddMMyyyyHHToDate(String ddMMyyyyHH) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH").parse(ddMMyyyyHH);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return date;
    }

    public static Date ddMMyyyyToDate(String ddMMyyyy) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(ddMMyyyy);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return date;
    }

    public static Date ddMMyyToDate(String ddMMyy) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(ddMMyy);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return date;
    }

    public static Date stringToDate(String dateString) {
        Date date = null;
        if (dateString != null && !dateString.trim().isEmpty()) {
            int length = dateString.trim().length();
            if (length > 16 && length <= 19) {
                date = ddMMyyyyHHmmssToDate(dateString.trim());
            }
            if (length > 13 && length <= 16) {
                date = ddMMyyyyHHmmToDate(dateString.trim());
            }
            if (length > 10 && length <= 13) {
                date = ddMMyyyyHHToDate(dateString.trim());
            }
            if (length > 8 && length <= 10) {
                date = ddMMyyyyToDate(dateString.trim());
            }
            if (length <= 8) {
                date = ddMMyyToDate(dateString.trim());
            }
        }
        return date;
    }

    public static String currentYYYYMMddHHmmss() {
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            result = df.format(new Date());
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
            LOGGER.log(Level.WARNING, ex.getStackTrace().toString());
        }
        return result;
    }

    /**
     *  GET START OF DAY -> 00:00:00
     */
    public static Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  GET END OF DAY -> 23:59:59
     */
    public static Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  PLUS N DAY
     */
    public static Date plusDays(Date date, int days) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(localDateTime.plusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  MINUS N DAY
     */
    public static Date minusDays(Date date, int days) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return Date.from(localDateTime.minusDays(days).atZone(ZoneId.systemDefault()).toInstant());
    }

}