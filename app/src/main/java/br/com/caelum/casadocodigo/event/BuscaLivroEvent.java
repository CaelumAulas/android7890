package br.com.caelum.casadocodigo.event;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

public class BuscaLivroEvent {
    private List<Livro> livros = new ArrayList<>();

    public BuscaLivroEvent(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
