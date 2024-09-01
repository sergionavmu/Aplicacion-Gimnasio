package com.example.appgym;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView favoriteRecyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<Activities> favoriteActivities;
    private ArrayList<Activities> myActivities;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        drawerLayout=findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView= findViewById(R.id.nav_menu);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(FavoriteActivity.this,
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

        // Establece todas las actividades en la clase Favorite
        Favorite.setAllActivities(myActivities);

        favoriteRecyclerView = findViewById(R.id.favoriteListView);
        favoriteActivities = Favorite.getFavoriteActivities(this);

        if (favoriteActivities != null) {
            favoriteAdapter = new FavoriteAdapter(this, favoriteActivities);
            favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            favoriteRecyclerView.setAdapter(favoriteAdapter);

            // Obtén las actividades favoritas y actualiza el adaptador
            updateFavoriteActivitiesList();
        } else {
            // Manejar el caso en el que la lista de actividades favoritas es nula
            showToast("Error al obtener actividades favoritas");
        }
    }

    private void updateFavoriteActivitiesList() {
        favoriteActivities = Favorite.getFavoriteActivities(this);

        // Agrega log para verificar la lista de favoritos
        for (Activities activity : favoriteActivities) {
            Log.d("FavoriteActivity", "Favorite activity: " + activity.getActivitytitle());
        }
        favoriteAdapter.setActivitiesList(favoriteActivities);
        favoriteAdapter.notifyDataSetChanged();

        if (favoriteActivities.isEmpty()) {
            showToast("No hay actividades favoritas");
        }
    }

    // Método para mostrar un Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}