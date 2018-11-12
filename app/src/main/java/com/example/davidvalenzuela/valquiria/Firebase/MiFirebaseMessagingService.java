package com.example.davidvalenzuela.valquiria.Firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MiFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from= remoteMessage.getFrom();
        Log.d("NOTICIAS", "Mensaje recibido de: "+ from);

        if (remoteMessage.getNotification() !=null){
            Log.d("NOTICIAS", "Notificacion: "+ remoteMessage.getNotification().getBody());

        }
    }
}
