# najva Cordova Application
This is a Ionic application that Najva_cordova_plugin implemented in it.

### Test Najva Service in Cordova
If you want test Najva Push Notification Service in ionic application you should:

1.  Clone this project and rename package name of it 

2.  Register this app after login in [najva panel](https://app.najva.com/accounts/login/?next=/).(to register any app, its package name must be unique!)


3.  After register najva panel gives you campaignId,websiteId,apiKey which is specific to your app

4.  Go to src/index.html file and put this parameters to init method that looks like the following then save it.
window.najva.init(function(success){},function(error){}, YOUR_CAMPAIGN_ID_GOES_HERE, YOUR_WEBSITE_ID_GOES_HERE, YOUR_API_KEY_GOES_HERE);
5.  Now you can run application and send notification from your panel to it!