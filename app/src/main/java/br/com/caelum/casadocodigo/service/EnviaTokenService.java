package br.com.caelum.casadocodigo.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EnviaTokenService {
    @POST("device/register/{email}/{id}")
    Call<ResponseBody> registraCelular(@Path("email") String email, @Path("id") String token);
}
