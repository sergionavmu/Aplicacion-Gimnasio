package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    ImageView logoImage;
    Button button2;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //hooks

        logoImage = findViewById(R.id.logoImage);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.TextBienvendio);

        ObjectAnimator translateYAnimator = ObjectAnimator.ofFloat(logoImage, "translationY", -2000f, 0f);
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(logoImage, "rotation", 0f, 360f);

        ObjectAnimator translateYAnimatorTextView = ObjectAnimator.ofFloat(textView, "translationX", -1200f, 0f);
        ObjectAnimator rotateAnimatorTextView = ObjectAnimator.ofFloat(textView, "rotation", 180f, 0f);

        ObjectAnimator translateYAnimatorbutton2 = ObjectAnimator.ofFloat(button2, "translationX", 1200f, 0f);
        ObjectAnimator rotateAnimatorbutton2 = ObjectAnimator.ofFloat(button2, "rotation", -180f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateYAnimator, rotateAnimator, translateYAnimatorTextView, rotateAnimatorTextView, translateYAnimatorbutton2, rotateAnimatorbutton2);
        animatorSet.setDuration(1500);

        // Iniciar la animación
        animatorSet.start();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}