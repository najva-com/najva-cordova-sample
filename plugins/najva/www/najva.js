var exec = cordova.require('cordova/exec');

var najva =  {};
/**
 * initialize najva for receiving notifications
 * @param onSuccess callback response for when success'es
 * @param onError call back response for whed error happens.
 * @param campaignId your champaginId
 * @param websiteId your websiteId
 * @param apiKey your apiKey
 * @param locationEnabled set true if you want to receive location based improvements
 */
najva.init = function (onSuccess,onError,campaignId, websiteId, apiKey, locationEnabled) {
    exec(onSuccess,onError,'najva','init',[campaignId,websiteId,apiKey,locationEnabled]);
};

/**
 * by calling this method custom notifiaction handling will be activated
 * @param callback is the callback to receive notification json.
 */
najva.receiveJSONNotification = function (callback) {
    exec(callback,function (error) {

    },'najva','receiveJSONNotification',[]);
};

/**
 * by calling this method you can receive users token and save them in your server to customize notifications based on users behaviour etc.
 * @param callback is the callback to receive user token.
 */
najva.handleUsers = function (callback) {
    exec(callback, function (error) {
            //error
        },
        'najva', 'handleUsers', []);
};

najva.getSubscribedToken = function(callback) {
    exec(callback,function (error) {
        //error
    },'najva','getSubscribedToken',[]);
}

module.exports = najva;
