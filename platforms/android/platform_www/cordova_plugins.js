cordova.define('cordova/plugin_list', function(require, exports, module) {
  module.exports = [
    {
      "id": "najva.najva",
      "file": "plugins/najva/www/najva.js",
      "pluginId": "najva",
      "clobbers": [
        "najva"
      ]
    }
  ];
  module.exports.metadata = {
    "cordova-plugin-whitelist": "1.3.4",
    "najva": "1.2.0"
  };
});