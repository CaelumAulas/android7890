package br.com.caelum.casadocodigo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.activity.MainActivity;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapter extends RecyclerView.Adapter {
    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_livro_impar, parent, false);

        return new RecyclerLivroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerLivroViewHolder holder = (RecyclerLivroViewHolder) viewHolder;
        Livro livro = livros.get(i);
        holder.campoNome.setText(livro.getNome());
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    class RecyclerLivroViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_livro_nome) TextView campoNome;
        @BindView(R.id.item_livro_foto) ImageView campoFoto;

        public RecyclerLivroViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_livro)
        public void cliqueNoItem(View view) {
            Toast.makeText(view.getContext(), "clique no livro", Toast.LENGTH_LONG).show();
            Livro livro = livros.get(getAdapterPosition());
            LivroDelegate delegate = (LivroDelegate)  view.getContext();

            //delegando a ação do clique pra qualquer classe que implementar LivroDelegate
            delegate.lidaComCliqueNo(livro);
        }
    }

}
