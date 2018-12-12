package br.com.caelum.casadocodigo.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.inject.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.inject.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhesLivroFragment extends Fragment {
    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;
    @BindView(R.id.detalhes_livro_nome)
    TextView nome;
    @BindView(R.id.detalhes_livro_autores)
    TextView autores;
    @BindView(R.id.detalhes_livro_comprar_fisico)
    Button botaoComprarFisico;
    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button botaoComprarEbook;
    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button botaoComprarAmbos;
    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;
    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;
    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numPaginas;
    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    private Carrinho carrinho;
    private Livro livro;

    @Inject
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public static DetalhesLivroFragment getInstanceWith(Livro livro) {
       DetalhesLivroFragment detalhesLivroFragment = new DetalhesLivroFragment();
       Bundle bundle = new Bundle();
       bundle.putSerializable("livro", livro);
       detalhesLivroFragment.setArguments(bundle);
       return detalhesLivroFragment;
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro,container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        livro = (Livro) bundle.getSerializable("livro");
        populaCamposCom(livro);

        CasaDoCodigoApplication.getInstance().getComponent().inject(this);

        return view;
    }

    @OnClick({ R.id.detalhes_livro_comprar_fisico,
                R.id.detalhes_livro_comprar_ebook,
                R.id.detalhes_livro_comprar_ambos })
    public void selecionaItem(View button) {
        Item item = new Item(livro, defineTipoDeCompra(button));
        carrinho.adiciona(item);
        Toast.makeText(getContext(), "Livro adicionado", Toast.LENGTH_SHORT).show();
    }

    private TipoDeCompra defineTipoDeCompra(View button) {
        switch (button.getId()) {
            case R.id.detalhes_livro_comprar_fisico:
                return TipoDeCompra.FISICO;
            case R.id.detalhes_livro_comprar_ebook:
                return TipoDeCompra.VIRTUAL;
            default:
                return TipoDeCompra.JUNTOS;
        }
    }


    private void populaCamposCom(Livro livro) {
        nome.setText(livro.getNome());

        String listaDeAutores = "";
        for (Autor autor : livro.getAutores()) {
            if (!listaDeAutores.isEmpty()) {
                listaDeAutores += ", ";
            }
            listaDeAutores += autor.getNome();
        }
        autores.setText(listaDeAutores);

        descricao.setText(livro.getDescricao());
        numPaginas.setText(String.valueOf(livro.getNumPaginas()));
        isbn.setText(livro.getISBN());
        dataPublicacao.setText(livro.getDataPublicacao());

        Picasso.get().load(livro.getUrlFoto()).into(foto);


        String textoComprarFisico = String.format("Comprar Livro Físico - R$ %.2f", livro.getValorFisico());
        botaoComprarFisico.setText(textoComprarFisico);

        String textoComprarEbook = String.format("Comprar Livro Ebook - R$ %.2f", livro.getValorVirtual());
        botaoComprarEbook.setText(textoComprarEbook);

        String textoComprarAmbos = String.format("Comprar Livro Ambos - R$ %.2f", livro.getValorDoisJuntos());
        botaoComprarAmbos.setText(textoComprarAmbos);
    }
}
