package com.example.x_men;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SegundaPantalla extends AppCompatActivity {

    private ImageView imagen, img;
    private String nombrePersonaje, aliasPersonaje, afiliacionPersonaje, descripcionPersonaje, imgPersonaje;
    TextView name, alias, affiliation, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);

        imagen = findViewById(R.id.imagen);
        String url= "https://i.pinimg.com/originals/b4/55/54/b455540b13eb57ba3f09d9ebcd93b187.gif";
        Uri urlparse = Uri.parse(url);
        Glide.with(getApplicationContext()).load(urlparse).into(imagen);

        imgPersonaje = getIntent().getStringExtra("imagenPersonaje");
        nombrePersonaje = getIntent().getStringExtra("nombrePersonaje");
        aliasPersonaje =  getIntent().getStringExtra("aliasPersonaje");
        afiliacionPersonaje =  getIntent().getStringExtra("afiliacion");
        descripcionPersonaje =  getIntent().getStringExtra("descripcion");

        img = findViewById(R.id.img_personaje);
        name = findViewById(R.id.nombre_personaje);
        alias = findViewById(R.id.alias);
        affiliation = findViewById(R.id.afiliacion_personaje);
        description = findViewById(R.id.descripcion_personaje);

        String urlImagenPersonaje = imgPersonaje;
        Uri urlparseImg = Uri.parse(urlImagenPersonaje);
        Glide.with(getApplicationContext()).load(urlparseImg).into(img);

        name.setText(nombrePersonaje);
        alias.setText(aliasPersonaje);
        affiliation.setText(afiliacionPersonaje);
        description.setText(descripcionPersonaje);


    }
}