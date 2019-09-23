package com.najva.cordovaplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.najva.cordovaplugin.CordovaHandler;
import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;

/**
 * This class echoes a string called from JavaScript.
 */
public class najva extends CordovaPlugin {

    CordovaHandler handler;
    CallbackContext jsonCallback;
    CallbackContext userCallback;
    CallbackContext notificationReceiverCallback;

    JSONNotificationCallback jsonlistener = new JSONNotificationCallback() {
        @Override
        public void onNewNotification(String json) {
            if (jsonCallback != null) {
                jsonCallback.success(json);
            }
        }
    };

    UserHandlingCallback usersListener = new UserHandlingCallback() {
        @Override
        public void onNewUserSubscribed(String token) {
            if (userCallback != null) {
                userCallback.success(token);
            }
        }
    };

    BroadcastReceiver notificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            android.util.Log.i("najva", "onReceive: " + intent.getStringExtra("message_id"));

            if (notificationReceiverCallback != null) {
                notificationReceiverCallback.success(intent.getStringExtra("message_id"));
            }
        }
    };

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        //and call your method
        handler = new CordovaHandler(cordova.getActivity());
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            init();
            callbackContext.success();
            return true;
        } else if (action.equals("receiveJSONNotification")) {
            receiveJSONData(callbackContext);
            return true;
        } else if (action.equals("handleUsers")) {
            handleUsers(callbackContext);
            return true;
        } else if (action.equals("getSubscribedToken")) {
            callbackContext.success(handler.getToken());
            return true;
        } else if (action.equals("notificationReceiver")) {
            notifaicationReceiver(callbackContext);
            return true;
        }
        return false;
    }

    private void notifaicationReceiver(CallbackContext context) {
        notificationReceiverCallback = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.najva.sdk.Najva.ACTION_NOTIFICATION_RECEIVED");
        cordova.getActivity().getApplicationContext().registerReceiver(notificationReceiver, filter);
    }

    private void handleUsers(CallbackContext context) {
        userCallback = context;
        handler.handleUsers(usersListener);
    }

    private void receiveJSONData(CallbackContext context) {
        jsonCallback = context;
        handler.initJSONNotification(jsonlistener);
    }

    private void init() {
        handler.init();
    }

}

