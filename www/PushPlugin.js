var exec = require('cordova/exec');


exports.iosInit=function(success,error){
	exec(success,error,"PushIOS","init",null);
}

exports.iosBindAccount=function(success,error,args){
	exec(success,error,"PushIOS","bindAccount",args);
}
exports.iosBindTagsandAlias=function(success,error,args){
	exec(success,error,"PushIOS","bindTagsandAlias",args);
}
exports.iosUnBindAccount=function(success,error,args){
	exec(success,error,"PushIOS","unBindAccount",args);
}
exports.iosUnBindTagsandAlias=function(success,error,args){
	exec(success,error,"PushIOS","unBindTagsandAlias",args);
}

exports.iosGetDeviceId=function(success,error){
	exec(success,error,"PushIOS","getDeviceId",null);
}
exports.iosListTags=function(success,error){
	exec(success,error,"PushIOS","listTags",null);
}
exports.iosListAlias=function(success,error){
	exec(success,error,"PushIOS","listAlias",null);
}
exports.iosRemoveAlias=function(success,error){
	exec(success,error,"PushIOS","removeAlias",null);
}

//android

exports.androidBindAccount=function(success,error,args){
	exec(success,error,"Push","bindAccount",args);
}
exports.androidBindTagsandAlias=function(success,error,args){
	exec(success,error,"Push","bindTagsandAlias",args);
}
exports.androidUnBindAccount=function(success,error,args){
	exec(success,error,"Push","unBindAccount",args);
}
exports.androidUnbindTagsandAlias=function(success,error,args){
	exec(success,error,"Push","unBindTagsandAlias",args);
}

exports.androidGetDeviceId=function(success,error){
	exec(success,error,"Push","getDeviceId",null);
}
exports.androidListTags=function(success,error){
	exec(success,error,"Push","listTags",null);
}
exports.androidListAlias=function(success,error){
	exec(success,error,"Push","listAlias",null);
}
exports.androidRemoveAlias=function(success,error){
	exec(success,error,"Push","removeAlias",null);
}
exports.androidSetNotificationSoundFilePath=function(success,error,args){
	exec(success,error,"Push","setNotificationSoundFilePath",args);
}
exports.androidSetNotificationLargeIcon=function(success,error,args){
	exec(success,error,"Push","setNotificationLargeIcon",args);
}
exports.androidSetNotificationSmallIcon=function(success,error,args){
	exec(success,error,"Push","setNotificationSmallIcon",args);
}
exports.androidSetDoNotDisturb=function(success,error,args){
	exec(success,error,"Push","setDoNotDisturb",args);
}
exports.androidSetCloseDoNotturbMode=function(success,error){
	exec(success,error,"Push","setCloseDoNotturbMode",null);
}
exports.androidSetClearNotifications=function(success,error){
	exec(success,error,"Push","setCleraNotifications",null);
}
exports.androidBindPhoneNumber=function(success,error,args){
	exec(success,error,"Push","bindPhoneNumber",args);
}
exports.androidUnBindPhoneNum=function(success,error){
	exec(success,error,"Push","unBindPhoneNum",null);
}