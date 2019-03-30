package nu.ist.iosf.commons.utils;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IOSFUtils {

    public static final String DISPLAY_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DISPLAY_DATETIME_FORMAT = "yyyy-MM-dd HH:mm a";


    public static final String DB_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static String getDateString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT);
        return dateTime.format(formatter);
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATETIME_FORMAT);
            return dateTime.format(formatter);
        }
        return null;
    }


    public static LocalDateTime getStartOfDate(String dateString) {
        if (!StringUtils.isEmpty(dateString)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATETIME_FORMAT);
            return LocalDateTime.parse(dateString.concat(" 00:00:00"), formatter);
        }
        return null;
    }

    public static LocalDateTime getEndOfDate(String dateString) {
        if (!StringUtils.isEmpty(dateString)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_DATETIME_FORMAT);
            return LocalDateTime.parse(dateString.concat(" 23:59:59"), formatter);
        }
        return null;
    }

    /*public static LocalDateTime getLocalDateTime(String dateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String date = "16/08/2016";

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter)
    }*/

    public static Long longValue(String stringValue){
        return StringUtils.isEmpty(stringValue) ? Long.valueOf(0) : Long.valueOf(stringValue);
    }

    public static Double doubleValue(String stringValue){
        return StringUtils.isEmpty(stringValue) ? Double.valueOf(0) : Double.valueOf(stringValue);
    }

    public static boolean isNullOrZero(Object object) {
        if (ObjectUtils.isEmpty(object)) {
            return true;
        } else if (object == Long.valueOf(0)) {
            return true;
        } else {
            return false;
        }
    }

}
