package com.example.appgym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button HavntAccont;
    Button ForgotPass;
    SharedPreferences sharedPreferences;
    EditText username, password;

    private static final String SHARE_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASS = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        HavntAccont = findViewById(R.id.HavntAccont);
        ForgotPass = findViewById(R.id.ForgotPass);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        sharedPreferences = getSharedPreferences(SHARE_PREF_NAME, MODE_PRIVATE);

        if (checkSession()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional, para cerrar LoginActivity si hay una sesión activa
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                // Validar que se haya ingresado un nombre de usuario y contraseña
                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save session data
                saveSession(enteredUsername, enteredPassword);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar LoginActivity después de un inicio de sesión exitoso

                Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            }
        });

        HavntAccont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RecoverPassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveSession(String name, String pass) {
        // Guardar datos de sesión
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASS, pass);
        editor.apply();
    }

    private boolean checkSession() {
        SharedPreferences prefs = getSharedPreferences(
                SHARE_PREF_NAME, Context.MODE_PRIVATE);
        String username = prefs.getString(KEY_NAME, null);
        String password = prefs.getString(KEY_PASS, null);
        if (username != null) {
            // Username encontrado
            return true;
        }
        // Username no encontrado
        return false;
    }

    // Limpiar el Fichero
    private void clearSession() {
        // Borrar datos de sesión
        SharedPreferences.Editor editor = getSharedPreferences(
                SHARE_PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }
}