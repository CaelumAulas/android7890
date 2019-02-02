package br.com.caelum.casadocodigo.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.activity.CarrinhoActivity;

public class NotificacaoListener extends FirebaseMessagingService {

    private String channelId = "canal_cdc";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        String mensagem = data.get("message");

        criaCanalDeNotificacao();

        Notification notificacao = criaNotificacao(mensagem);
        mostra(notificacao);
    }

    private void criaCanalDeNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId, "Notificações", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Novidades da app");
            manager.createNotificationChannel(channel);
        }
    }

    private Notification criaNotificacao(String mensagem) {
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this,
                        123,
                        new Intent(this, CarrinhoActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification novaNotificacao = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.casadocodigo)
                .setContentTitle("Nova notificação")
                .setContentText(mensagem)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        return novaNotificacao;
    }

    private void mostra(Notification notification) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(111, notification);
    }
}
