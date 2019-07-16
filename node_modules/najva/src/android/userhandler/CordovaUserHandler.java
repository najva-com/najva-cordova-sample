package com.najva.cordovaplugin.userhandler;

import com.najva.cordovaplugin.callbacks.UserHandlingCallback;

import com.najva.najvasdk.Class.NajvaUserHandler;

public class CordovaUserHandler extends NajvaUserHandler {
    private UserHandlingCallback callback;

    public CordovaUserHandler(UserHandlingCallback callback) {

        this.callback = callback;
    }

    @Override
    public void najvaUserSubscribed(String token) {
        if (callback != null)
            callback.onNewUserSubscribed(token);
        else super.najvaUserSubscribed(token);
    }
}
