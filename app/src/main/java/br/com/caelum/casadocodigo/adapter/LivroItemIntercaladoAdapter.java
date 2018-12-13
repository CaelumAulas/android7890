package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroItemIntercaladoAdapter extends RecyclerView.Adapter {
        private List<Livro> livros;

        public LivroItemIntercaladoAdapter(List<Livro> livros) {
            this.livros = livros;
        }

    @Override
    public int getItemViewType(int position) {
        return position %2;
    }

    @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            int tipoDeLayout = R.layout.item_livro_impar;

            if (viewType % 2 == 0) {
                tipoDeLayout = R.layout.item_livro_par;
            }


            View view = inflater.inflate(tipoDeLayout, parent, false);

            return new LivroIntercaladoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            LivroIntercaladoViewHolder holder = (LivroIntercaladoViewHolder) viewHolder;
            Livro livro = livros.get(i);
            holder.campoNome.setText(livro.getNome());
            Picasso picasso = Picasso.get();
            picasso.setIndicatorsEnabled(true);
            picasso.load(livro.getUrlFoto()).fit().into(holder.campoFoto);
        }

        @Override
        public int getItemCount() {
            return livros.size();
        }

        class LivroIntercaladoViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_livro_nome)
            TextView campoNome;
            @BindView(R.id.item_livro_foto)
            ImageView campoFoto;

            public LivroIntercaladoViewHolder(View itemView) {
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
