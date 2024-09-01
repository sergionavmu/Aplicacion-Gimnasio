package com.example.appgym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActivitiesAdapter extends ArrayAdapter<Activities> {
    private Context mContext;

    public ActivitiesAdapter(Context context, ArrayList<Activities> activitiesArrayList) {
        super(context, 0, activitiesArrayList);
        mContext = context;
    }

    private static class ViewHolder {
        TextView titleTextView, title;
        ImageView imageView;

        ViewHolder(View view) {
            titleTextView = view.findViewById(R.id.actividadinformation);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.backImg);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        ViewHolder holder;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(mContext).inflate(R.layout.activity_detail, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        Activities currentActivity = getItem(position);

        if (currentActivity != null) {
            if (holder.titleTextView != null) {
                holder.titleTextView.setText(currentActivity.getActivitytitle());
            }

            if (holder.imageView != null) {
                Glide.with(mContext)
                        .load(currentActivity.getImgActivity())
                        .into(holder.imageView);
            }
        }

        return listItemView;
    }
}
