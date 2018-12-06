package br.com.caelum.casadocodigo.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.delegate.RequisicaoDelegate;
import br.com.caelum.casadocodigo.event.BuscaLivroEvent;
import br.com.caelum.casadocodigo.fragment.CarregaFragment;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivroFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebService;

public class MainActivity extends AppCompatActivity implements LivroDelegate, RequisicaoDelegate {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WebService(this).listaLivros();

        exibe(new CarregaFragment(), false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void lidaComCliqueNo(Livro livro) {
        DetalhesLivroFragment detalhesLivroFragment = DetalhesLivroFragment.getInstanceWith(livro);
        exibe(detalhesLivroFragment, true);
    }


    @Subscribe
    @Override
    public void lidaComSucesso(BuscaLivroEvent event) {
        List<Livro> livros = event.getLivros();
        ListaLivroFragment listaLivroFragment = ListaLivroFragment.getInstanceWith(livros);
        exibe(listaLivroFragment,false);
    }

    @Subscribe
    @Override
    public void lidaComErro(Throwable erro) {
        Toast.makeText(this, "Deu erro" + erro.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void exibe(Fragment fragment, boolean podeEmpilhar) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_principal, fragment);
        if (podeEmpilhar) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
