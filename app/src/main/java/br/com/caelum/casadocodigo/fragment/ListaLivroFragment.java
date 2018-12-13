package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;
import com.mugen.attachers.RecyclerViewAttacher;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroItemIntercaladoAdapter;
import br.com.caelum.casadocodigo.adapter.LivroItemUnicoAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivroFragment extends Fragment {

    private View view;
    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;
    private ArrayList<Livro> livros;
    private boolean pegandoNovosLivros;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        mFirebaseRemoteConfig.fetch(120).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mFirebaseRemoteConfig.activateFetched();
                } else {
                    Toast.makeText(getContext(), "falha", Toast.LENGTH_SHORT).show();
                    Log.i("REMOTE", "erro: ", task.getException());
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        livros = (ArrayList<Livro>) bundle.getSerializable("livros");

        if (mFirebaseRemoteConfig.getBoolean("item_unico")) {
            recyclerView.setAdapter(new LivroItemUnicoAdapter(livros));
        } else {
            recyclerView.setAdapter(new LivroItemIntercaladoAdapter(livros));
        }
        //recyclerView.addOnScrollListener(new LivroEndlessList());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAttacher attacher = Mugen.with(recyclerView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                new WebService().listaLivros(livros.size(), 5);
                pegandoNovosLivros = true;
                Snackbar.make(recyclerView, "Carregando mais livros", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public boolean isLoading() {
                return pegandoNovosLivros;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        });
        attacher.start();
        attacher.setLoadMoreOffset(3);
    }


    public void adiciona(List<Livro> livros) {
        pegandoNovosLivros = false;
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
