package com.example.appgym;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Favorite {
    private static final String PREF_NAME = "MyFavorites";
    private static final String KEY_FAVORITES = "favorites";
    private static List<Activities> allActivities;

    public static void setAllActivities(List<Activities> activities) {
        allActivities = activities;
    }

    public static List<Activities> getFavoriteActivities(Context context) {
        if (allActivities == null) {
            // Si la lista de todas las actividades aún no se ha establecido, devuelve una lista vacía
            return new ArrayList<>();
        }

        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoriteTitles = preferences.getStringSet(KEY_FAVORITES, new HashSet<>());

        List<Activities> favoriteActivities = new ArrayList<>();
        for (String title : favoriteTitles) {
            Activities favoriteActivity = Activities.getActivityByTitle(allActivities, title);
            if (favoriteActivity != null) {
                favoriteActivities.add(favoriteActivity);
            }
        }

        return favoriteActivities;
    }

    public static List<String> getFavoriteTitles(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> favoritesSet = preferences.getStringSet(KEY_FAVORITES, new HashSet<>());

        // Convertir el conjunto a una lista
        return new ArrayList<>(favoritesSet);
    }

    public static void addFavorite(Context context, String activityTitle) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        List<String> favorites = getFavoriteTitles(context);

        if (!favorites.contains(activityTitle)) {
            favorites.add(activityTitle);
        }
        Set<String> favoritesSet = new HashSet<>(favorites);

        editor.putStringSet(KEY_FAVORITES, favoritesSet);
        editor.apply();

        // Agregar la actividad a la lista en Activities
        Activities activity = Activities.getActivityByTitle(allActivities, activityTitle);
        if (activity != null) {
            activity.setFavorite(true);
        }
    }

    public static void removeFavorite(Context context, String activityTitle) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        List<String> favorites = getFavoriteTitles(context);
        favorites.remove(activityTitle);

        editor.putStringSet(KEY_FAVORITES, new HashSet<>(favorites));
        editor.apply();
    }

    public static boolean isFavorite(Context context, String activityTitle) {
        List<String> favorites = getFavoriteTitles(context);
        return favorites.contains(activityTitle);
    }



    public void displayFavorites(Context context, ListView listView) {
        List<Activities> favoriteActivities = getFavoriteActivities(context);

        ArrayAdapter<Activities> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, favoriteActivities);
        listView.setAdapter(adapter);
    }
}