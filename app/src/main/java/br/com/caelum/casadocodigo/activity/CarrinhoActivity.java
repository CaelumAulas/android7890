package br.com.caelum.casadocodigo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {
    @BindView(R.id.lista_itens_carrinho) RecyclerView listaItens;
    @BindView(R.id.valor_carrinho) TextView campoValorTotal;


    private Carrinho carrinho = new Carrinho();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens(),this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));

        campoValorTotal.setText(carrinho.getValorTotalFormatado());
    }
}
