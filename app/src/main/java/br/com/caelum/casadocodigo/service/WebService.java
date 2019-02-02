package br.com.caelum.casadocodigo.service;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.event.BuscaLivroEvent;
import br.com.caelum.casadocodigo.modelo.Livro;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebService {

    public void listaLivros(int indiceInicialLivro, int quantidade){
        String url = "http://cdcmob.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        final ListaLivroService listaLivroService = retrofit.create(ListaLivroService.class);
        Call<List<Livro>> servico = listaLivroService.buscaLivros(indiceInicialLivro, quantidade);

        servico.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                List<Livro>  livros = response.body();
                //avisa que terminou
                EventBus.getDefault().post(new BuscaLivroEvent(livros));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable erro) {
                //avisa que terminou
                EventBus.getDefault().post(erro);
            }
        });
    }

    public void cadastraCelular(final String email, String token){
        String url = "http://10.0.2.2:8080/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        EnviaTokenService enviaTokenService = retrofit.create(EnviaTokenService.class);
        String encodedToken = token;
        try {
            encodedToken = URLEncoder.encode(token, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> servico = enviaTokenService.registraCelular(email, encodedToken);

        servico.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("TOKEN", "registrou celular com email: " + email);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("TOKEN", "erro ao registrar", t);
            }
        });
    }
}
