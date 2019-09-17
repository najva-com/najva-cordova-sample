package com.najva.cordovaplugin;

import android.content.Context;
import android.content.IntentFilter;

import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;
import com.najva.cordovaplugin.userhandler.CordovaUserHandler;

import com.najva.sdk.Najva;
import com.najva.sdk.NajvaJsonDataListener;
import com.najva.sdk.UserSubscriptionListener;


public class CordovaHandler {
    private Context context;

    private JSONNotificationCallback jsonCallback;
    private UserHandlingCallback userHandlingCallback;

    public CordovaHandler(Context context) {
        this.context = context;
    }

    public void init() {
        Najva.initialize(context);
    }

    public void handleUsers(UserHandlingCallback callback) {
        this.userHandlingCallback = callback;
        CordovaUserHandler handler = new CordovaUserHandler(userHandlingCallback);
        Najva.setUserSubscriptionListener(handler);
    }

    public void unHandleUsers() {
        this.userHandlingCallback = null;
        Najva.setUserSubscriptionListener(null);
    }

    public void initJSONNotification(final JSONNotificationCallback callback) {
	Najva.setNajvaJsonDataListener(new NajvaJsonDataListener() {
		@Override
		public void onReceiveJson(String jsonString){
			callback.onNewNotification(jsonString);
		}
	});
	Najva.getCachedJsonData(context);
    }

    public void dontHandleJSONNotification() {
        Najva.setNajvaJsonDataListener(null);
    }

    public String getToken(){
	if(context==null) return null;
	    return Najva.getSubscribedToken(context);
    }
}
