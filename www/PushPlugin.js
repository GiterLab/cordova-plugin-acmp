var exec = require('cordova/exec');


exports.androidInit =function(success,error){
	exec(success,error,"Push","init",null);
}

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

exports.androidBand=function(success,error,args){
    exec(success,error,"Push","bindAccountandTagandAlias",args);

}
//exports.iosBand=function(success,error){
//   exec(success,error,"PushIOS","bindAccountandTagandAlias",["sdfasfas","{\"tag_key\":1,\"tag_value\":[\"adfsaf\",\"fdasdf\"],\"alias\":\"dfsadfsa\"}"]);
//               
//}
               
exports.iosBand=function(success,error,args){
    exec(success,error,"PushIOS","bindAccountandTagandAlias",args);
               
}