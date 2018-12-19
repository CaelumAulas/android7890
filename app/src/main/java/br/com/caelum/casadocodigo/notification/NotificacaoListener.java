package br.com.caelum.casadocodigo.notification;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

public class NotificacaoListener extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        EventBus.getDefault().post(remoteMessage);
        Log.i("NOTIFICACAO", "notificação recebida: "+ remoteMessage.getNotification().getTitle());
    }
}
