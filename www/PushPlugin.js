var exec = require('cordova/exec');


exports.androidInit =function(success,error){
	exec(success,error,"Push","init",null);
}

exports.iosInit=function(success,error){
	exec(success,error,"PushIOS","init",null);
}

exports.androidBand=function(success,error,args){
    exec(success,error,"Push","bindAccountandTagandAlias",args);

}
//exports.iosBand=function(success,error){
//               exec(success,error,"PushIOS","bindAccountandTagandAlias",["sdfasfas","{\"tag_key\":1,\"tag_value\":[\"adfsaf\",\"fdasdf\"],\"alias\":\"dfsadfsa\"}"]);
//               
//}
               
exports.iosBand=function(success,error,args){
    exec(success,error,"PushIOS","bindAccountandTagandAlias",args);
               
}