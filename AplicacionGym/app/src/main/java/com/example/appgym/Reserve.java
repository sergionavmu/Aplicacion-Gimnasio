package com.example.appgym;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reserve {

    private static final String PREF_NAME = "MyReservations";
    private static final String KEY_RESERVATIONS = "reservations";
    private static List<Activities> allActivities;

    public static void setAllActivities(List<Activities> activities) {
        allActivities = activities;
    }

    public static List<Activities> getReservations(Context context) {
        if (allActivities == null) {
            return new ArrayList<>();
        }

        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> reservationTitles = preferences.getStringSet(KEY_RESERVATIONS, new HashSet<>());

        List<Activities> reserveActivities = new ArrayList<>();
        for (String title : reservationTitles) {
            Activities reserveActivity = Activities.getActivityByTitle(allActivities, title);
            if (reserveActivity != null) {
                reserveActivities.add(reserveActivity);
            }
        }

        return reserveActivities;
    }

    public static void addReservation(Context context, String activityTitle, String reservationDate) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> reservations = preferences.getStringSet(KEY_RESERVATIONS, new HashSet<>());

        // Usa una cadena de formato para combinar el título y la fecha
        String reservationKey = activityTitle + reservationDate;
        reservations.add(reservationKey);

        editor.putStringSet(KEY_RESERVATIONS, reservations);
        editor.apply();
    }

    public static void removeReservation(Context context, String activityTitle, String reservationDate) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Set<String> reservations = preferences.getStringSet(KEY_RESERVATIONS, new HashSet<>());

        // Usa una cadena de formato para combinar el título y la fecha
        String reservationKey = activityTitle + reservationDate;
        reservations.remove(reservationKey);

        editor.putStringSet(KEY_RESERVATIONS, reservations);
        editor.apply();
    }

    public static boolean isReserved(Context context, String activityTitle, String reservationDate) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> reservations = preferences.getStringSet(KEY_RESERVATIONS, new HashSet<>());

        // Usa una cadena de formato para combinar el título y la fecha
        String reservationKey = activityTitle + reservationDate;

        return reservations.contains(reservationKey);
    }

}