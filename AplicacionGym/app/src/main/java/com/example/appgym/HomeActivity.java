package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textUsername;
    Button buttonLogout;
    SharedPreferences sharedPreferences;

    private static final String SHARE_PREF_NAME = "mypref";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASS = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textUsername = findViewById(R.id.textUsername);
        buttonLogout = findViewById(R.id.buttonLogout);

        sharedPreferences = getSharedPreferences(SHARE_PREF_NAME, MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_NAME, null);

        if (username != null) {
            textUsername.setText("Username: " + username);
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la sesión del usuario
                clearSession();

                // Redirigir a la actividad de inicio de sesión
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearSession() {
        // Borrar datos de sesión
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}