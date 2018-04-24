package com.gameball.firebase;

import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.gameball.api.MMFavor;
import com.gameball.school.C0308R;
import com.gameball.tools.BadgeUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.Calendar;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static int mId = 0;

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Logger.m19d("onMessageReceived");
        Logger.m19d(remoteMessage.toString());
        Logger.m19d("From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            if (MMFavor.getInstance().account.getToken().length() != 0) {
                mId++;
                BadgeUtil.setBadgeCount(getApplicationContext(), mId);
                Logger.m19d("Message data payload: " + remoteMessage.getData());
                Map<String, String> data = remoteMessage.getData();
                String url = (String) data.get("url");
                String message = (String) data.get(SettingsJsonConstants.PROMPT_MESSAGE_KEY);
                Builder mBuilder = new Builder(this).setSmallIcon(C0308R.drawable.bell).setContentTitle((String) data.get(SettingsJsonConstants.PROMPT_TITLE_KEY)).setDefaults(-1).setDefaults(2).setStyle(new BigTextStyle().bigText(message)).setContentText(message).setAutoCancel(true);
                Intent resultIntent = new Intent("android.intent.action.VIEW");
                resultIntent.setData(Uri.parse(url));
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addNextIntent(resultIntent);
                mBuilder.setContentIntent(stackBuilder.getPendingIntent(0, 134217728));
                ((NotificationManager) getSystemService("notification")).notify((int) Calendar.getInstance().getTimeInMillis(), mBuilder.build());
            } else {
                return;
            }
        }
        if (remoteMessage.getNotification() != null) {
            Logger.m19d("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
