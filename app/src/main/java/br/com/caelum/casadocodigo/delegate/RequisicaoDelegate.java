package br.com.caelum.casadocodigo.delegate;

import java.util.List;

import br.com.caelum.casadocodigo.event.BuscaLivroEvent;
import br.com.caelum.casadocodigo.modelo.Livro;

public interface RequisicaoDelegate {
    void lidaComSucesso(BuscaLivroEvent event);

    void lidaComErro(Throwable erro);
}
