package com.company.gitaccessservice.util.costant;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TimeStaps {
    public static final long TIMSTEPOFDAY = 24 * 60 * 60 * 1000;
    public static final long TIMSTEPOF3HOURS = 10_800_000;


    public static  Long[] convertSincTill(String startDate, String endDate){
        Long timestamp[]  = new Long[2];
        LocalDate since = LocalDate.parse(startDate);
        LocalDate till = LocalDate.parse(endDate);
        long dayBeforeEpoch = since.getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;
        long dayAfterEpoch;
        if(till.equals(since)) {
            dayAfterEpoch = dayBeforeEpoch + since.atTime(23, 59).getLong(ChronoField.MILLI_OF_DAY);
        } else{
            dayAfterEpoch = till.getLong(ChronoField.EPOCH_DAY) * TimeStaps.TIMSTEPOFDAY - TimeStaps.TIMSTEPOF3HOURS;
        }
        timestamp[0] = dayBeforeEpoch;
        timestamp[1] = dayAfterEpoch;
        return timestamp;
    }
}
