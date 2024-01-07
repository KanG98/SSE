package com.kang98.foundation.helper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Helpers {

    public static String getCurrentDate() {
        ZonedDateTime now = ZonedDateTime.now();
        String isoTime = now.format(DateTimeFormatter.ISO_INSTANT);
        return isoTime;
    }
}
