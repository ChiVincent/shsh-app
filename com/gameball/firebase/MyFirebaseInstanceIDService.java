package com.gameball.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.logger.Logger;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    public void onTokenRefresh() {
        Logger.m21e("Refreshed token: " + FirebaseInstanceId.getInstance().getToken(), new Object[0]);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }
}
