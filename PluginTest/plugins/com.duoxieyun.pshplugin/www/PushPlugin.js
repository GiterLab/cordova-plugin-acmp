var exec = require('cordova/exec');


exports.init =function(success,error){
	exec(success,error,"Push","init",null);
}

exports.iosInit=function(success,error){
	exec(success,error,"PushIOS","init",null);
}