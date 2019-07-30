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

    public void init(int campaignId, int websiteId, String apiKey) {
        Najva.initialize(context, campaignId, websiteId, apiKey);
    }

    public void handleUsers(UserHandlingCallback callback) {
        this.userHandlingCallback = callback;
        CordovaUserHandler handler = new CordovaUserHandler(userHandlingCallback);
        Najva.setUserHandler(handler);
    }

    public void unHandleUsers() {
        this.userHandlingCallback = null;
        Najva.setUserHandler(null);
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
    public String getToken(){
	if(context==null) return "context is null";
	return Najva.getSubscribedToken(context);
    }
}
