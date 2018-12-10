package br.com.caelum.casadocodigo.service;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListaLivroService {
    @GET("listarLivros")
    Call<List<Livro>> buscaLivros(@Query("indicePrimeiroLivro") int indiceInicialLivro, @Query("qtdLivros") int quantidade);
}
