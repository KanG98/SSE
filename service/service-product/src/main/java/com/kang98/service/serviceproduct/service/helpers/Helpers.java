package com.kang98.service.serviceproduct.service.helpers;

import java.time.ZonedDateTime;
import java.util.Date;

public class Helpers {
    public static Date convertToDate(String date) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        // Convert ZonedDateTime to Date
        return Date.from(zonedDateTime.toInstant());
    }
}
