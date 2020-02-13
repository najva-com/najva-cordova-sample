# Najva cordova plugin
plugin that implements najva sdk into a cordova application.

## Instruction 

Use command bellow to add plugin to your project

```
cd your/project/path

cordova plugin add najva@latest
```

after that in your `index.js` file inside the device ready function add code bellow

```
window.najva.init(successCallback,errorCallback);
```

## Receive json notifications

Add code bellow to your `index.js' to receive callbacks

```
window.najva.receiveJSONNotifications(function (json) {
    //todo handle json here
}
);
```

`json` in above code is a string containing JsonObject that you specify in panel.

## Handle users

To handle your users and use different stategy for them you can get their najva token to send notifications directly to them.
To get user tokens add code bellow to your `index.js` script.

```
window.najva.handleUsers(function (token) {
    //todo send token to your server.
}
);
```