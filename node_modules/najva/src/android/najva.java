package com.najva.cordovaplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


import com.najva.cordovaplugin.CordovaHandler;
import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;


import com.najva.sdk.NotificationClickListener;
import com.najva.sdk.NotificationReceiveListener;

/**
 * This class echoes a string called from JavaScript.
 */
public class najva extends CordovaPlugin {

    CordovaHandler handler;
    CallbackContext jsonCallback;
    CallbackContext userCallback;
    CallbackContext notificationReceiverCallback;
    CallbackContext notificationClickReceiverCallback;

    NotificationReceiveListener notificationReceiveListener = new NotificationReceiveListener() {
        @Override
        public void onReceiveNotification(String notificationId) {
            if (notificationReceiverCallback != null) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, notificationId);
                pluginResult.setKeepCallback(true);

                notificationReceiverCallback.sendPluginResult(pluginResult);
            }
        }
    };

    NotificationClickListener notificationClickReceiver = new NotificationClickListener() {
        @Override
        public void onClickNotification(String notificationId, int buttonId) {
            if (notificationClickReceiverCallback != null) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, notificationId);
                pluginResult.setKeepCallback(true);

                notificationClickReceiverCallback.sendPluginResult(pluginResult);
            }
        }
    };

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
                Log.d("Najva","sending token to user " + token);
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, token);
                userCallback.sendPluginResult(pluginResult);
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
            Log.d("najva", "init called");
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
            notificationReceiverCallback = callbackContext;
            handler.setNotificationReceiver(notificationReceiveListener);
            return true;
        } else if (action.equals("notificationClickReceiver")) {
            notificationClickReceiverCallback = callbackContext;
            handler.setNotificationClickReceiver(notificationClickReceiver);
        } else if (action.equals("enableLocation")) {
            handler.enableLocation();
        } else if (action.equals("enableFirebase")) {
            handler.enableFirebase();
        }
        return false;
    }

    private void handleUsers(CallbackContext context) {
        Log.d("Najva", "handling user");
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

