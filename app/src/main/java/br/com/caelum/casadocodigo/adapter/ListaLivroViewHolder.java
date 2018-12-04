package br.com.caelum.casadocodigo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;

public class ListaLivroViewHolder {
    TextView campoNome;
    ImageView campoFoto;

    public ListaLivroViewHolder(View view) {
        campoNome = view.findViewById(R.id.item_livro_nome);
        campoFoto = view.findViewById(R.id.item_livro_foto);






    }
}
