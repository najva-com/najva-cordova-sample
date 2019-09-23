package com.najva.cordovaplugin.userhandler;

import com.najva.cordovaplugin.callbacks.UserHandlingCallback;

import com.najva.sdk.UserSubscriptionListener;

public class CordovaUserHandler implements UserSubscriptionListener {
    private UserHandlingCallback callback;

    public CordovaUserHandler(UserHandlingCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onUserSubscribed(String token) {
        if (callback != null)
            callback.onNewUserSubscribed(token);
    }
}
