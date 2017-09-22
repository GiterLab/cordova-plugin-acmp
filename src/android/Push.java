package org.giterlab;

import android.graphics.BitmapFactory;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        }else if ("setNotificationSoundFilePath".equals(action)){
            return setNotificationSoundFilePath(args, callbackContext);
        }else if ("setNotificationLargeIcon".equals(action)){
            return setNotificationLargeIcon(args, callbackContext);
        }else if ("setNotificationSmallIcon".equals(action)){
            return setNotificationSmallIcon(args, callbackContext);
        }else if ("setDoNotDisturb".equals(action)){
            return setDoNotDisturb(args, callbackContext);
        }else if ("setCloseDoNotturbMode".equals(action)){
            return setCloseDoNotturbMode(args, callbackContext);
        }else if ("setCleraNotifications".equals(action)){
            return setCleraNotifications(args, callbackContext);
        }else if ("bindPhoneNumber".equals(action)){
            return bindPhoneNumber(args, callbackContext);
        }else if ("unBindPhoneNum".equals(action)){
            return unBindPhoneNum(args, callbackContext);
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

    private boolean setNotificationSoundFilePath(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.getString(0).length()!=0){
            try {
                cloudPushService.setNotificationSoundFilePath(args.getString(0));
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"setNotificationSoundFilePath success");
            }catch (Exception ex){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"setNotificationSoundFilePath error");
            }
        }
        return true;
    }
    private boolean setNotificationLargeIcon(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        if (args.getString(0).length()!=0){
            try {
                cloudPushService.setNotificationLargeIcon(BitmapFactory.decodeFile(args.getString(0)));
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"setNotificationLargeIcon success");
            }catch (Exception ex){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"setNotificationLargeIcon error");
            }
        }
        return true;
    }
    private boolean setNotificationSmallIcon(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        try {
            cloudPushService.setNotificationSmallIcon(args.getInt(0));
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
            Log.d(TAG,"setNotificationLargeIcon success");
        }catch (Exception ex){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
            Log.d(TAG,"setNotificationLargeIcon error");
        }
        return true;

    }
    private boolean setDoNotDisturb(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<4){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        int startHour=args.getInt(0);
        int startMinute=args.getInt(1);
        int endHour=args.getInt(2);
        int endMinute=args.getInt(3);
        if (startHour>23 || startMinute>59 || endHour>23 || endMinute>59){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        if (!((startHour*60+startMinute)<(endHour*60+endMinute))){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.setDoNotDisturb(startHour, startMinute, endHour, endMinute, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"setDoNotDisturb success");
            }

            @Override
            public void onFailed(String s, String s1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"setDoNotDisturb error");
            }
        });
        return true;
    }
    private boolean setCloseDoNotturbMode(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.closeDoNotDisturbMode();
        return true;
    }
    private boolean setCleraNotifications(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.clearNotifications();
        return true;
    }
    private boolean bindPhoneNumber(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        if (args.length()<1){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        Pattern pattern=Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Matcher matcher=pattern.matcher(args.getString(0));
        if (!matcher.matches()){
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
            return false;
        }
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.bindPhoneNumber(args.getString(0), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"bindPhoneNumber success");
            }

            @Override
            public void onFailed(String s, String s1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"bindPhoneNumber error");
            }
        });
        return true;
    }
    private boolean unBindPhoneNum(JSONArray args, final CallbackContext callbackContext)throws JSONException{
        CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
        cloudPushService.unbindPhoneNumber(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK,"ok"));
                Log.d(TAG,"unBindPhoneNum success");
            }

            @Override
            public void onFailed(String s, String s1) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"error"));
                Log.d(TAG,"unBindPhoneNum error");
            }
        });
        return true;
    }


}