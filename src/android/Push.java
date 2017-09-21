package org.giterlab;

import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Application;
import android.content.Context;
import android.util.Log;
//import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.fastjson.*;

import static com.alibaba.sdk.android.push.AgooMessageReceiver.TAG;

public class Push extends CordovaPlugin{

    private CallbackContext mCallbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.mCallbackContext = callbackContext;
        if("bindAccount".equals(action)){
            return bindAccount(args,callbackContext);
        }else if ("bindTagsandAlias".equals(action)){
            return bindTagsandAlias(args,callbackContext);
        }else if ("unBindAccount".equals(action)){
            return unBindAccount(args,callbackContext);
        }else if("unBindTagsandAlias".equals(action)){
            return unBindTagsandAlias(args, callbackContext);
        } else if ("getDeviceId".equals(action)) {
            return getDeviceId(args, callbackContext);
        }else if ("listTags".equals(action)){
            return listTags(args, callbackContext);
        }else if ("listAlias".equals(action)){
            return listAlias(args, callbackContext);
        }else if ("removeAlias".equals(action)){
            return removeAlias(args, callbackContext);
        }else {
            mCallbackContext.error("error");
            return false;
        }
    }

    private boolean bindAccount(JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if(args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }

        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.get(0).toString().length()!=0){
            cloudPushService.bindAccount(args.get(0).toString(), new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                    Log.d(TAG, "bindAccount success"+s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                    Log.d(TAG, "bindAccount failed"+s+s1);
                }
            });
        }
        callbackContext.success("success");
        return true;
    }

    private boolean bindTagsandAlias(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if(args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.get(0).toString().length()!=0){
            try{
                TagsAlias tagsAlias=JSONObject.parseObject(args.get(1).toString(),TagsAlias.class);
                cloudPushService.bindTag(tagsAlias.getTag_key(), tagsAlias.getTag_value(), tagsAlias.getAlias(), new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                        Log.d(TAG, "bindTag success"+s);
                    }

                    @Override
                    public void onFailed(String s, String s1) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                        Log.d(TAG, "bindTag failed"+s+s1);
                    }
                });
            }catch (Exception ex){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild JSON arguements"));
                return false;
            }
        }
        callbackContext.success("success");
        return true;
    }

    private boolean unBindAccount(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.unbindAccount(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"unBindAccount success");
            }

            @Override
            public void onFailed(String s, String s1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"unBindAccount failed");

            }
        });
        callbackContext.success("success");
        return true;
    }

    private boolean unBindTagsandAlias(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }

        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.get(0).toString().length()!=0){
            try{
                TagsAlias tagsAlias=JSONObject.parseObject(args.get(1).toString(),TagsAlias.class);
                cloudPushService.unbindTag(tagsAlias.getTag_key(), tagsAlias.getTag_value(), tagsAlias.getAlias(), new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                        Log.d(TAG, "bindTag success"+s);
                    }

                    @Override
                    public void onFailed(String s, String s1) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                        Log.d(TAG, "bindTag failed"+s+s1);
                    }
                });
            }catch (Exception ex){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild JSON arguements"));
                return false;
            }
        }
        callbackContext.success("success");
        return true;
    }

    private boolean getDeviceId(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,cloudPushService.getDeviceId()));
        Log.d(TAG,"unBindAccount success");
        callbackContext.success("success");
        return true;
    }
    private boolean listTags(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.get(0).toString().length()!=0){
            try{
                TagsAlias tagsAlias=JSONObject.parseObject(args.get(1).toString(),TagsAlias.class);
                cloudPushService.listTags(tagsAlias.getTag_key(), new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                        Log.d(TAG,"listTags success");
                    }

                    @Override
                    public void onFailed(String s, String s1) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                        Log.d(TAG,"listTags failed");
                    }
                });
            }catch (Exception ex){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild JSON arguements"));
                return false;
            }
        }

        callbackContext.success("success");
        return true;
    }

    private boolean listAlias(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.listAliases(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"listAlias success");
            }

            @Override
            public void onFailed(String s, String s1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"listAlias failed");
            }
        });
        callbackContext.success("success");
        return true;
    }
    private boolean removeAlias(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if(args.get(0).toString().length()!=0){
            cloudPushService.removeAlias(args.get(0).toString(), new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,s));
                    Log.d(TAG,"removeAlias success");
                }

                @Override
                public void onFailed(String s, String s1) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,s));
                    Log.d(TAG,"removeAlias failed");
                }
            });
        }
        callbackContext.success("success");
        return true;
    }


}