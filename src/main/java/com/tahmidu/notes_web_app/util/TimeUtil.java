package com.tahmidu.notes_web_app.util;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class TimeUtil {

    public static long calculateCodeExpiryDate(int minutes){
        return LocalTime.now().plusMinutes(minutes).getLong(ChronoField.MICRO_OF_DAY);
    }

    public static long calculateTime(){
        return LocalTime.now().getLong(ChronoField.MICRO_OF_DAY);
    }
}
