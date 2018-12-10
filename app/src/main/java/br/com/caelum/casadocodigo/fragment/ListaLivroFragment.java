package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.endlesslist.LivroEndlessList;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivroFragment extends Fragment {

    private View view;
    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;
    private ArrayList<Livro> livros;

    public static ListaLivroFragment getInstanceWith(List<Livro> livros) {
        ListaLivroFragment listaLivroFragment = new ListaLivroFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("livros", (ArrayList<Livro>) livros);
        listaLivroFragment.setArguments(bundle);
        return listaLivroFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        livros = (ArrayList<Livro>) bundle.getSerializable("livros");

        recyclerView.setAdapter(new LivroAdapter(livros));

        recyclerView.addOnScrollListener(new LivroEndlessList());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }


    public void adiciona(List<Livro> livros) {
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
