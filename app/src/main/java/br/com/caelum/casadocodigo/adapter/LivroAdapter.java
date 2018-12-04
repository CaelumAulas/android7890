package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;

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
        final TextView campoNome;
        final ImageView campoFoto;

        public RecyclerLivroViewHolder(View itemView) {
            super(itemView);

            campoNome = itemView.findViewById(R.id.item_livro_nome);
            campoFoto = itemView.findViewById(R.id.item_livro_foto);
        }
    }

}