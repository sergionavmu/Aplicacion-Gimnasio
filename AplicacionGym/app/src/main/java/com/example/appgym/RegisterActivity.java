package com.example.appgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;


public class RegisterActivity extends AppCompatActivity {
    private static final String SHARE_PREF_NAME = "mypref";
    Button HaveAccont, btnRegister;
    private Switch switchReceiveNewsletter;

    EditText nameEditText, emailEditText, usernameEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        HaveAccont = findViewById(R.id.HaveAccont);
        btnRegister = findViewById(R.id.btnRegister);
        switchReceiveNewsletter = findViewById(R.id.switchReceiveNewsletter);
        nameEditText = findViewById(R.id.Name);
        emailEditText = findViewById(R.id.Email);
        usernameEditText = findViewById(R.id.Username);
        passwordEditText = findViewById(R.id.Password);


        HaveAccont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //si un usuario nuevo se da de alta, proceso para guardar los datos en la DDBB
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtenemos los valores de cada campo
                String name = nameEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                //guardamos esos valores
                saveUserData(name, email, username, password);

                // Redirigir al usuario a la pagina de login para que inicie sesión una vez registrado
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


        //este sería el código si queremos que nuestra switch haga algo
        switchReceiveNewsletter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                } else {

                }
            }
        });
    }

    //guardamos los datos del usuario en el SharedPreferences
    private void saveUserData(String name, String email, String username, String password) {
        SharedPreferences preferences = getSharedPreferences(SHARE_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("username", username);
        editor.putString("pass", password);
        editor.apply();
    }
}