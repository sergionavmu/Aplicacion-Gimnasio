package com.example.appgym;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Activities implements Parcelable {
    private String activitytitle;
    private String info;
    private String reserveDate;
    private int imgActivity;
    private boolean favorite;
    private static List<Activities> myActivities = new ArrayList<>();

    // Constructor sin argumentos necesario para Parcelable
    public Activities() {
        // Constructor sin argumentos
    }
    public Activities(String activitytitle, String info, int imgActivity, boolean favorite) {
        this.activitytitle = activitytitle;
        this.info = info;
        this.imgActivity = imgActivity;
        this.favorite = false;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getActivitytitle() {
        return activitytitle;
    }

    public static List<Activities> getMyActivities() {
        return myActivities;
    }

    public void setActivitytitle(String activitytitle) {
        this.activitytitle = activitytitle;
    }

    public int getImgActivity() {
        return imgActivity;
    }

    public void setImgActivity(int imgActivity) {
        this.imgActivity = imgActivity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    // Método estático para agregar actividades
    public static void addActivity(Activities activity) {
        if (myActivities != null) {
            myActivities.add(activity);
        }
    }

    protected Activities(Parcel in) {
        activitytitle = in.readString();
        info = in.readString();
        imgActivity = in.readInt();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Activities> CREATOR = new Parcelable.Creator<Activities>() {
        @Override
        public Activities createFromParcel(Parcel in) {
            return new Activities(in);
        }

        @Override
        public Activities[] newArray(int size) {
            return new Activities[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activitytitle);
        dest.writeString(info);
        dest.writeInt(imgActivity);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }

    public static Activities getActivityByTitle(List<Activities> allActivities, String title) {
        if (allActivities != null) {
            for (Activities activity : allActivities) {
                if (activity.getActivitytitle().equals(title)) {
                    return activity;
                }
            }
        }
        return null;
    }
}
