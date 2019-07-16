package com.najva.cordovaplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.najva.cordovaplugin.CordovaHandler;
import com.najva.cordovaplugin.callbacks.JSONNotificationCallback;
import com.najva.cordovaplugin.callbacks.UserHandlingCallback;

/**
 * This class echoes a string called from JavaScript.
 */
public class najva extends CordovaPlugin {

    CordovaHandler handler;

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

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        //and call your method
        handler = new CordovaHandler(cordova.getActivity());
    }

    CallbackContext jsonCallback;
    CallbackContext userCallback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            int campaignId = args.getInt(0);
            int websiteId = args.getInt(1);
            String apiKey = args.getString(2);
            boolean locationEnabled = args.getBoolean(3);
            if (apiKey == null || apiKey.equals("")) {
                callbackContext.error("api key cannot be null or empty");
                return false;
            }
            init(campaignId, websiteId, apiKey, locationEnabled);
            return true;
        } else if (action.equals("receiveJSONNotification")) {
            receiveJSONData(callbackContext);
            return true;
        } else if (action.equals("handleUsers")) {
            handleUsers(callbackContext);
            return true;
        }
        return false;
    }

    private void handleUsers(CallbackContext context) {
        userCallback = context;
        handler.handleUsers(usersListener);
    }

    private void receiveJSONData(CallbackContext context) {
        jsonCallback = context;
        handler.initJSONNotification(jsonlistener);
    }

    private void init(int campaignId, int websiteId, String apiKey, boolean locationEnabled) {
        handler.init(campaignId, websiteId, apiKey, locationEnabled);
    }

}
