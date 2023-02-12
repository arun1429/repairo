package com.app.repairo.app.activity.fcm;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.app.repairo.app.R;
import com.app.repairo.app.activity.logreg.SplashActivity;
import com.app.repairo.app.utils.ConstantValues;
import com.app.repairo.app.utils.Preferences;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    //
    private static final String TAG = "MyFirebaseMsgService";
    Intent intent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
         Log.d(TAG,remoteMessage.toString());
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage);
            try {
                int notifiCount = Preferences.getInt(this,ConstantValues.KEY_NOTIFICATION_COUNT);
                Preferences.saveInt(this, ConstantValues.KEY_NOTIFICATION_COUNT,notifiCount+1);
            } catch (Exception e) {
                e.printStackTrace();
                Preferences.saveInt(this, ConstantValues.KEY_NOTIFICATION_COUNT,1);
            }
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData() != null) {
            Log.d(TAG, "Notification Message Data: " + remoteMessage.getData());
            Intent intent = new Intent("com.cova.consumer");
            intent.putExtra("isLocation",false);
            sendBroadcast(intent);

        }
    }

    private void sendNotification(RemoteMessage messageBody) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        final int NOTIFICATION_COLOR = getResources().getColor(R.color.colorPrimary);
        final long[] VIBRATE_PATTERN = {0, 500};
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(messageBody.getNotification().getBody())
                        .setContentText(messageBody.getNotification().getTitle())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(defaultSoundUri, createAudioAttributes());
            channel.setLightColor(NOTIFICATION_COLOR);
            channel.setVibrationPattern(VIBRATE_PATTERN);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private AudioAttributes createAudioAttributes() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build();
        return attributes;
    }
}