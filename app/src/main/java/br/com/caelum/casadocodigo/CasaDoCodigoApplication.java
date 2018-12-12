package br.com.caelum.casadocodigo;

import android.app.Application;

import br.com.caelum.casadocodigo.inject.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.inject.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Carrinho;

public class CasaDoCodigoApplication extends Application {
    private static CasaDoCodigoApplication instancia;
    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instancia = this;

        component = DaggerCasaDoCodigoComponent.create();
    }

    public CasaDoCodigoComponent getComponent() {
        return component;
    }

    public static CasaDoCodigoApplication getInstance() {
        return instancia;
    }
}
