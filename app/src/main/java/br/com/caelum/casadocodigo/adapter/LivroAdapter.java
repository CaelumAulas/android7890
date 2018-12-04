package br.com.caelum.casadocodigo.adapter;

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

public class LivroAdapter extends BaseAdapter {
    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int position) {
        return livros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return livros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ListaLivroViewHolder holder;
        if (convertView == null) {
            Log.i("LISTA", "Criando view" + position);
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.item_livro_impar, parent, false);

            holder = new ListaLivroViewHolder(view);

            view.setTag(holder);
        } else {
            Log.i("LISTA", "Reaproveitando view" + position);
            view = convertView;
            holder = (ListaLivroViewHolder) convertView.getTag();

        }

        Livro livro = livros.get(position);
        holder.campoNome.setText(livro.getNome());
        return view;
    }
}
