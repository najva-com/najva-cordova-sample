package com.najva.cordovaplugin;

import android.content.Context;
import android.content.IntentFilter;

import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;
import com.najva.cordovaplugin.userhandler.CordovaUserHandler;

import com.najva.najvasdk.Class.Najva;
import com.najva.najvasdk.Class.NajvaUserHandler;
import com.najva.najvasdk.Class.NajvaJsonDataListener;

public class CordovaHandler {
    private Context context;

    private JSONNotificationCallback jsonCallback;
    private UserHandlingCallback userHandlingCallback;

    public CordovaHandler(Context context) {
        this.context = context;
    }

    public void init(int campaignId, int websiteId, String apiKey, boolean isLocationEnabled) {
        Najva.initialize(context, campaignId, websiteId, apiKey, isLocationEnabled);
    }

    public void handleUsers(UserHandlingCallback callback) {
        this.userHandlingCallback = callback;
        CordovaUserHandler handler = new CordovaUserHandler(userHandlingCallback);
        Najva.setUserHandler(handler);
    }

    public void unHandleUsers() {
        this.userHandlingCallback = null;

        Najva.setUserHandler(new NajvaUserHandler());
    }

    public void initJSONNotification(final JSONNotificationCallback callback) {
	Najva.setJsonDataListener(new NajvaJsonDataListener() {
		@Override
		public void onReceiveJson(String jsonString){
			callback.onNewNotification(jsonString);
		}
	});
    }

    public void dontHandleJSONNotification() {
        Najva.setJsonDataListener(null);
    }

}
