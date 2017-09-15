cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "com.duoxieyun.pshplugin.PushPlugin",
        "file": "plugins/com.duoxieyun.pshplugin/www/PushPlugin.js",
        "pluginId": "com.duoxieyun.pshplugin",
        "clobbers": [
            "cordova.plugins.PushPlugin"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.2",
    "com.duoxieyun.pshplugin": "0.0.1"
};
// BOTTOM OF METADATA
});