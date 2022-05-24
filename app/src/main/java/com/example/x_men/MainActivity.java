package com.example.x_men;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.x_men.modelos.xmenResponse;
import com.example.x_men.modelos.Characters;
import com.example.x_men.paquete.APIService;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String BASEURL ="https://xmenapiheroku.herokuapp.com/api/";
    private static final String TAG ="XMEN_RESPONSE";

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new  Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obtnerDatosXmen();
    };

    private void obtnerDatosXmen() {
        String[] name = new String[20];
        String[] powers = new String[2];
        String[] alias = new String[20];
        String[] affiliation = new String[20];
        String[] img = new String[20];
        String[] description = new String[20];

        APIService servicio = retrofit.create(APIService.class);
        Call<xmenResponse> respuesta = servicio.obtenerListaXmen();

        respuesta.enqueue(new Callback<xmenResponse>() {
            @Override
            public void onResponse(Call<xmenResponse> call, Response<xmenResponse> response) {

                if (response.isSuccessful()){
                    xmenResponse respuestaApi = response.body();
                    ArrayList <Characters> listXmen = respuestaApi.getResults();

                    for (int i = 0; i < listXmen.size(); i++){
                        Characters xmen = listXmen.get(i);
                        name[i] = xmen.getName();
                        alias[i] = xmen.getAlias();
                        affiliation[i] = xmen.getAffiliation();
                        description[i] = xmen.getDescription();
                        img[i] = xmen.getImg();
                    }

                } else {
                    Log.e(TAG,"Error" +response.errorBody());
                }

                init(name, alias, affiliation, img, description);

            }
            @Override
            public void onFailure(Call<xmenResponse> call, Throwable t) {
                Log.e(TAG,"onFailure" + t.getMessage());
            }
        });
    }

    public void init(String[] name, String[] alias, String[] affiliation, String[] img, String[] description){
        ListAdapter listAdapter = new ListAdapter(MainActivity.this, name, alias, affiliation, img );
        lista = (ListView) findViewById(R.id.listaView);
        lista.setAdapter(listAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                Intent irSegundaPantalla = new Intent(MainActivity.this, SegundaPantalla.class);
                irSegundaPantalla.putExtra("nombrePersonaje", name[posicion]);
                irSegundaPantalla.putExtra("aliasPersonaje", alias[posicion]);
                irSegundaPantalla.putExtra("afiliacion", affiliation[posicion]);
                irSegundaPantalla.putExtra("imagenPersonaje", img[posicion]);
                irSegundaPantalla.putExtra("descripcion", description[posicion]);
                startActivity(irSegundaPantalla);
            }
        });
    }
}