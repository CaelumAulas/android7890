package br.com.caelum.casadocodigo.service;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.event.BuscaLivroEvent;
import br.com.caelum.casadocodigo.modelo.Livro;
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
}
