package com.example.appgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ArrayList<Activities> myActivities;
    private Toolbar toolbar;

    private ImageView bodycombat, bodypump, cicling, yoga, pilates, tonificaction, zumba;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodycombat=findViewById(R.id.bodyCombat);
        bodypump=findViewById(R.id.bodyPump);
        cicling=findViewById(R.id.cicling);
        yoga=findViewById(R.id.yoga);
        pilates=findViewById(R.id.pilates);
        tonificaction=findViewById(R.id.tonificacion);
        zumba=findViewById(R.id.zumba);
        drawerLayout=findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView= findViewById(R.id.nav_menu);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout, toolbar,R.string.open, R.string.close);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_cursos);


        myActivities = new ArrayList<>();
        myActivities.add(new Activities("Body Combat", "Una sesión de Body Combat se divide en diferentes bloques y tiene una duración de 55 minutos. " +
                "El primer bloque sirve de calentamiento y para empezar a familiarizarse con los movimientos que vas al siguiente bloque. En esta segunda parte se realizan, " +
                "al ritmo de la música, diferentes movimientos provenientes de técnicas como el boxeo, kick-boxing, karate, taekwondo o muay thai, entre otros, eso sí, sin impactar a nadie.\n",
                R.drawable.body_combat, false));
        myActivities.add(new Activities("Body Pump","Se trata de una sesión intensa en la que se trabajan los principales grupos musculares con barra y discos, " +
                "al ritmo de la música, en una secuencia de 10 ejercicios distintos. El peso varía en función del grupo muscular que se trabaje y según la capacidad de la persona que lo practique.\n",
                R.drawable.body_pumb, false));
        myActivities.add(new Activities("Zumba", "Es una sesión de baile fácil y divertida, coreografiada en bloques, al ritmo de la música latina y \"¡Que no pare la fiesta!\".\n" +
                "A través de los ritmos latinos, y con pasos de distintos estilos de baile, la Zumba permite realizar un trabajo cardiovascular intenso, con coreografías muy sencillas para seguir " +
                "la sesión correctamente y disfrutar así de todos sus beneficios. Así, aprendes a bailar ritmos latinos como la salsa, la bachata, el merengue, el reggeaton, la samba, etc. " +
                "y otros estilos de moda adaptados a la sesión.\n",
                R.drawable.zumba, false));
        myActivities.add(new Activities("Yoga","El yoga es una práctica que conecta el cuerpo, la respiración y la mente. Esta práctica utiliza posturas físicas, " +
                "ejercicios de respiración y meditación para mejorar la salud general. El yoga se desarrolló como una práctica espiritual hace miles de años. Hoy en día, " +
                "la mayoría de las personas en occidente que practican yoga lo hacen como ejercicio o para reducir el estrés.\n",
                R.drawable.yoga, false));
        myActivities.add(new Activities("Cicling", "El cycling, o ciclo indoor, consiste en clases dirigidas en una bicicleta fija acompañadas de una música brutal. " +
                "Eso es en esencia. Clases vibrantes, motivantes y llenas de energía. Es toda una experiencia donde las luces y la música juegan un papel primordial.\n",
                R.drawable.cicling, false));
        myActivities.add(new Activities("Pilates", "El método Pilates aporta grandes beneficios para estabilizar la postura, reforzar la musculatura profunda y reducir el dolor de espalda y articular.\n" +
                "Adicionalmente, la práctica de Pilates requiere concentración y tomar consciencia del cuerpo y de la respiración, por lo que aporta relajación mental y reducción del estrés.\n" +
                "Pilates proporciona grandes beneficios a cualquier perfil de practicante, independientemente de su nivel físico: jóvenes, adultos, personas mayores, embarazadas y deportistas. \n",
                R.drawable.pilates, false));
        myActivities.add(new Activities("Tonificación","Es un entrenamiento muscular compuesto de ejercicios de calentamiento, tonificación y estiramientos.\n" +
                "Enfocada a la mejora del tono muscular y condición física, la sesión es muy dinámica e intensa al combinar ejercicios funcionales realizados con diferentes materiales como el " +
                "bosu, xertubes, mancuernas, steps, glidings,etc. implicando e integrando diferentes partes del cuerpo en su ejecución.\n",
                R.drawable.tonificacion, false));

        Log.d("Numero de actividades", "NUM:" + myActivities.size());
        bodycombat.setTag(0);
        bodypump.setTag(1);
        cicling.setTag(4);
        yoga.setTag(3);
        pilates.setTag(5);
        tonificaction.setTag(6);
        zumba.setTag(2);


        bodycombat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) bodycombat.getTag();
                launchDetailActivity(myActivities.get(position));

            }
        });

        bodypump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) bodypump.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });
        cicling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) cicling.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });
        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) yoga.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });
        pilates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) pilates.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });
        tonificaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) tonificaction.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });
        zumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) zumba.getTag();
                launchDetailActivity(myActivities.get(position));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_cursos) {
            Toast.makeText(this, "Cursos", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_perfil) {
            Intent intentPerfil = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intentPerfil);
        }else if (itemId == R.id.favoritos) {
            Intent intentFavorito = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intentFavorito);
        }else if (itemId == R.id.reservas) {
            Intent intentPerfil = new Intent(MainActivity.this, ReserveActivity.class);
            startActivity(intentPerfil);
        }else if (itemId == R.id.contacto) {
            Intent intentPerfil = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intentPerfil);
        }else if (itemId == R.id.somos) {
            Intent intentPerfil = new Intent(MainActivity.this, QuiSomActivity.class);
            startActivity(intentPerfil);
        }else if (itemId == R.id.estamos) {
            Intent intentPerfil = new Intent(MainActivity.this, OnSomActivity.class);
            startActivity(intentPerfil);
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void launchDetailActivity(Activities activity) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putParcelableArrayListExtra("my_activities", myActivities);
        //intent.putExtra("my_activities", myActivities);
        intent.putExtra("activity_title", activity.getActivitytitle());
        intent.putExtra("activity_info", activity.getInfo());
        intent.putExtra("activity_image", activity.getImgActivity());
        intent.putExtra("activity_date", activity.getReserveDate());

        if (!Favorite.isFavorite(MainActivity.this, activity.getActivitytitle())) {
            // Si no es favorita, agrégala a favoritos
            Favorite.addFavorite(MainActivity.this, activity.getActivitytitle());
            // Actualiza la lista de actividades favoritas en la pantalla de favoritos (opcional)
            launchFavoriteActivity();
        }
        if (!Reserve.isReserved(MainActivity.this, activity.getActivitytitle(), activity.getReserveDate())) {
            Reserve.addReservation(MainActivity.this, activity.getActivitytitle(), activity.getReserveDate());
            launchReserveActivity();
        }

        startActivity(intent);

        Log.d("MainActivity", "Numero de actividades pasadas a ReserveActivity: " + myActivities.size());
    }

    private void launchFavoriteActivity() {
        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
        intent.putParcelableArrayListExtra("my_activities", myActivities);
        startActivity(intent);

        Log.d("MainActivity", "Numero de actividades pasadas a FavoriteActivity: " + myActivities.size());
    }

    private void launchReserveActivity() {
        Intent intent = new Intent(MainActivity.this, ReserveActivity.class);
        intent.putParcelableArrayListExtra("my_activities", myActivities);
        startActivity(intent);
    }

}