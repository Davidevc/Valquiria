package com.example.davidvalenzuela.valquiria.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.davidvalenzuela.valquiria.MainActivity;
import com.example.davidvalenzuela.valquiria.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.stream.Collector;

public class MiFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from= remoteMessage.getFrom();
        Log.d("NOTICIAS", "Mensaje recibido de: "+ from);

        if (remoteMessage.getNotification() != null){
            Log.d("NOTICIAS", "Notificacion: "+ remoteMessage.getNotification().getBody());

            mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),remoteMessage.getData());
        }
        if(remoteMessage.getData().size() > 0){
            Log.d("NOTICIAS","Data: "+remoteMessage.getData());
            enviarinfo(remoteMessage.getData());

        }
    }

  private void enviarinfo(Map<String, String> data) {
    JSONObject object = new JSONObject(data);
    Log.i("NOTICIAS",""+object.optString("mensaje"));
    String mensaje = object.optString("mensaje");

      Intent intent = new Intent(this, MainActivity.class);
      intent.putExtra("mensaje",mensaje);
      startActivity(intent);

    }


    private void mostrarNotificacion(String title, String body,Map<String, String> data) {

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
