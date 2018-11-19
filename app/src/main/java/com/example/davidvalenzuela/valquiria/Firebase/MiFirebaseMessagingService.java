package com.example.davidvalenzuela.valquiria.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.media.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.davidvalenzuela.valquiria.MainActivity;
import com.example.davidvalenzuela.valquiria.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Map;

public class MiFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from= remoteMessage.getFrom();
        Log.d("NOTICIAS", "Mensaje recibido de: "+ from);

        if (remoteMessage.getNotification() != null){
            Log.d("NOTICIAS", "Notificacion: "+ remoteMessage.getNotification().getBody());

            mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
        if(remoteMessage.getData().size() > 0){
            Log.d("NOTICIAS","Data: "+remoteMessage.getData());
            enviarinfo(remoteMessage.getData());

        }
    }

    private void enviarinfo(Map<String,String> data) {
        
        Log.i("MENSAJERECIB","");

    }

    private void mostrarNotificacion(String title, String body) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
