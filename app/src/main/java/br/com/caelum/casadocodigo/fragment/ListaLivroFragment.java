package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;

public class ListaLivroFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista_livros, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView listView = view.findViewById(R.id.lista_livros);

        List<Livro> livros = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Livro livro = new Livro();
            livro.setNome("Livro "+ i);
            livros.add(livro);
        }

        LivroAdapter adapter = new LivroAdapter(livros);

        listView.setAdapter(adapter);
    }
}
