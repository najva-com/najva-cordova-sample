var exec = cordova.require('cordova/exec');

var najva =  {};
/**
 * initialize najva for receiving notifications
 * @param onSuccess callback response for when success'es
 * @param onError callback response for when error happens.
 */
najva.init = function (onSuccess,onError) {
    exec(onSuccess,onError,'najva','init',[]);
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
};

najva.notificationReceiver = function(callback){
    exec(callback,function (error){
    console.log("broadcast receiver failed")
    }, 'najva','notificationReceiver',[]);
};

najva.disableLocation = function(){
    exec(function (success){
        //do nothing
    }, function (error){
        //do nothing
    }, 'najva', 'disableLocation', []);
}

module.exports = najva;
