package com.najva.cordovaplugin;

import android.content.Context;
import android.content.IntentFilter;
import android.app.Activity;
import android.util.Log;

import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;
import com.najva.cordovaplugin.userhandler.CordovaUserHandler;

import com.najva.sdk.Najva;
import com.najva.sdk.NajvaClient;
import com.najva.sdk.NajvaConfiguration;
import com.najva.sdk.NajvaJsonDataListener;
import com.najva.sdk.UserSubscriptionListener;
import com.najva.sdk.NotificationClickListener;
import com.najva.sdk.NotificationReceiveListener;


public class CordovaHandler {
    private Activity context;

    private JSONNotificationCallback jsonCallback;
    private UserHandlingCallback userHandlingCallback;
    private NajvaClient client;
    private NajvaConfiguration config;


    public CordovaHandler(Activity context) {
        this.context = context;
    }

    public void init() {
        config = new NajvaConfiguration();

        config.setNajvaJsonDataListener(new NajvaJsonDataListener() {
            @Override
            public void onReceiveJson(String jsonString) {
                if (jsonCallback != null)
                    jsonCallback.onNewNotification(jsonString);
            }
        });

        config.setUserSubscriptionListener(new UserSubscriptionListener() {
            @Override
            public void onUserSubscribed(String token) {
                Log.d("Handler", "token: " + token);
                if (userHandlingCallback != null)
                    userHandlingCallback.onNewUserSubscribed(token);
            }
        });

        client = NajvaClient.getInstance(context.getApplicationContext(),config);

        context.getApplication().registerActivityLifecycleCallbacks(client);
    }

    public void setNotificationReceiver(NotificationReceiveListener listener) {
        config.setReceiveNotificationListener(listener);
    }

    public void setNotificationClickReceiver(NotificationClickListener listener) {
        config.setNotificationClickListener(listener);
    }

    public void handleUsers(UserHandlingCallback callback) {
        this.userHandlingCallback = callback;
    }

    public void initJSONNotification(final JSONNotificationCallback callback) {
        this.jsonCallback = callback;
    }

    public String getToken() {
        return client.getSubscribedToken();
    }

    public void enableLocation() {
        config.enableLocation();
    }

    public void enableFirebase() {
        config.setFirebaseEnabled(true);
    }
}
