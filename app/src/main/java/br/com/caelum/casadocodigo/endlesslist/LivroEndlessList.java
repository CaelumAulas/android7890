package br.com.caelum.casadocodigo.endlesslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import br.com.caelum.casadocodigo.service.WebService;

public class LivroEndlessList extends RecyclerView.OnScrollListener {
    private boolean pegandoNovosLivros = true;
    private int totalItensAnterior = 0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItens = layoutManager.getItemCount();
        int ultimoItemVisivel = layoutManager.findLastVisibleItemPosition();


        if (pegandoNovosLivros) {
            if (totalItens > totalItensAnterior) {
                totalItensAnterior = totalItens;
                pegandoNovosLivros = false;
            }
        }
        if (!pegandoNovosLivros && totalItens <= ultimoItemVisivel + 1 + 3) {
            new WebService().listaLivros(totalItens, 5);
            pegandoNovosLivros = true;
        }

    }

}
