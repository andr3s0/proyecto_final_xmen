package com.example.x_men;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.x_men.modelos.xmenResponse;
import com.example.x_men.modelos.Characters;
import com.example.x_men.paquete.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String BASEURL ="https://xmenapiheroku.herokuapp.com/api/";
    private static final String TAG ="XMEN_RESPONSE";

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
                        Log.i(TAG,"X-menPersonajes: "+ xmen.getName());
                    }
                } else {
                    Log.e(TAG,"Error" +response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<xmenResponse> call, Throwable t) {
                Log.e(TAG,"onFailure" + t.getMessage());
            }
        });
    }
}