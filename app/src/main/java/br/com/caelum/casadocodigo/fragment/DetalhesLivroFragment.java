package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesLivroFragment extends Fragment {
   @BindView(R.id.detalhes_livro_nome) TextView campoNome;

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
        Livro livro = (Livro) bundle.getSerializable("livro");
        campoNome.setText(livro.getNome());

        return view;
    }
}
