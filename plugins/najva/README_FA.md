# پلاگین نجوا برای کوردوا
یک پلاگین ساده برای استفاده از کتابخانه نجوا در پلتفرم کوردوا

## پیاده سازی

ترمینال یا `cmd` را باز کرده و دستورات زیر را در آن وارد کنید.

```
cd your/project/path/

cordova plugin add najva@1.0.2

```

پس از آن در فایل `index.js` خود خط زیر را در تابع `onDeviceReady` اضافه کنید.

```
window.najva.init(successCallback,errorCallback,champaignId,websiteId,apikey,enableLocation);
```

### دریافت نوتیفیکیشن بصورت JSON 
برای دریافت نوتیفیکیشن ها و پیاده سازی کد توسط خودتان می‌توانید از کد زیر استفاده کنید.

```
window.najva.receiveJSONNotifications(function (json) {
    //todo handle json here
}
);
```

تابع `receiveJSONNotification` یک تابع به عنوان ورودی دریافت میکند و هرگاه نوتیفیکیشنی برای شما ارسال شود آنرا فراخوانی می‌کند.

* در کد فوق `json` یک `string` است که محتوای آن یک `json` است.

### دریافت توکن کاربران
برای دریافت توکن کاربران و ارسال آن به سرور خودتان می‌توانید از کد زیر استفاده کنید.

```
window.najva.handleUsers(function (token) {
    //todo send token to your server.
}
)
```
تابع `handleUsers` یک تابع به عنوان ورودی دریافت می‌کند و هرگاه توکن برای کاربر شما ساخته شود و یا توکن کاربر تغییر کند این تابع فراخوانی خواهد شد.

* در کد فوق `token` مشخص کننده منحصر به فرد برای هر شخص است.

