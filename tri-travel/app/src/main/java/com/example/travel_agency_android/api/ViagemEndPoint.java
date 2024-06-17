package com.example.travel_agency_android.api;

import com.example.travel_agency_android.api.model.PostDTO;
import com.example.travel_agency_android.api.model.Resposta;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

public interface ViagemEndPoint {
    @POST("api/cadastro/viagem")
    Call<Resposta> postViagem(@Body PostDTO dtoEnviar);

    @GET("api/listar/viagem/{viagemID}")
    Call<Resposta> getViagem(@Query("viagemId") int viagemId);

    @GET("api/listar/viagem/conta?contaId={contaID}")
    Call<Resposta> getViagemConta(@Query("contaID") int contaID);

    @GET("api/listar/viagem")
    Call<Resposta> getViagemPath(@Path("viagemId") int viagemId);

}
