package com.example.x_men.paquete;
import com.example.x_men.modelos.xmenResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("characters")
    Call<xmenResponse> obtenerListaXmen();
}
