package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private NestedScrollView scrollView;
    DatePickerDialog picker;
    private Button btnReserva;

    private ArrayList<Activities> myActivities;

    private ImageView backImg, normalImg, imageHeart, imageArrow;
    private TextView actividadinformation, title;
    private EditText selectDate;
    private String activityTitle;
    private int activityImage;
    private String activityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // hooks
        scrollView = findViewById(R.id.scrollView);
        btnReserva = findViewById(R.id.btnReserva);
        backImg = findViewById(R.id.backImg);
        normalImg = findViewById(R.id.normalImg);
        imageHeart = findViewById(R.id.imageHeart);
        imageArrow = findViewById(R.id.imageArrow);
        actividadinformation = findViewById(R.id.actividadinformation);
        title = findViewById(R.id.title);
        selectDate = findViewById(R.id.selectDate);

        Intent intent = getIntent();
        if (intent != null) {
            myActivities = intent.getParcelableArrayListExtra("my_activities");

            activityTitle = intent.getStringExtra("activity_title");
            activityInfo = intent.getStringExtra("activity_info");
            activityImage = intent.getIntExtra("activity_image", R.drawable.ic_round_access_time_24);

            // Ahora puedes usar estos datos para configurar tus vistas en DetailActivity
            setTitle(activityTitle);
            actividadinformation.setText(activityInfo);
            title.setText(activityTitle);
            backImg.setImageResource(activityImage);
            normalImg.setImageResource(activityImage);
        }

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (int i = 0; i < myActivities.size(); i++) {
            Activities currentActivity = myActivities.get(i);
            ImageView currentHeart = getHeartImageViewForActivity(currentActivity);
            currentHeart.setTag(i);
            currentHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) currentHeart.getTag();
                    Activities selectedActivity = myActivities.get(position);
                    toggleFavorite(activityTitle, currentHeart);
                    Log.d("FavoriteActivity", "Añadido a favoritos: " + selectedActivity.getActivitytitle());
                }
            });
        }

        imageHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleFavorite(activityTitle, imageHeart);
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(DetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        selectDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = selectDate.getText().toString();

                toggleReserve(activityTitle, selectedDate);

            }
        });


    }

        private ImageView getHeartImageViewForActivity(Activities activity) {
            switch (activity.getActivitytitle()) {
                case "Body Combat":
                    return imageHeart;
                case "Body Pump":
                    return imageHeart;
                case "Zumba":
                    return imageHeart;
                case "Yoga":
                    return imageHeart;
                case "Cicling":
                    return imageHeart;
                case "Pilates":
                    return imageHeart;
                case "Tonificación":
                    return imageHeart;
                default:
                    return null;
            }
        }

        private void toggleFavorite(String activityTitle, ImageView imageHeart) {
            if (Favorite.isFavorite(this, activityTitle)) {
                Favorite.removeFavorite(this, activityTitle);
                imageHeart.setImageResource(R.drawable.bg_circle_darck);
                Toast.makeText(DetailActivity.this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
            } else {
                Favorite.addFavorite(this, activityTitle);
                imageHeart.setImageResource(R.drawable.ic_favorite_filled);
                Toast.makeText(DetailActivity.this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        }


    private void toggleReserve(String activityTitle, String reserveDate) {
        if (!Reserve.isReserved(this, activityTitle, reserveDate)) {
            Reserve.addReservation(this, activityTitle, reserveDate);
            Intent intent = new Intent(DetailActivity.this, ReserveActivity.class);
            startActivity(intent);
            Toast.makeText(DetailActivity.this, "Añadido de Reservas", Toast.LENGTH_SHORT).show();
        } else {
            Reserve.removeReservation(this, activityTitle, reserveDate);
            Toast.makeText(DetailActivity.this, "Eliminado a Reservas", Toast.LENGTH_SHORT).show();
        }
    }

}
