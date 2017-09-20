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
        if ("init".equals(action)){
           // init(DXApplication.getContext());
            callbackContext.success("success");
            return true;
        }else if("bindAccountandTagandAlias".equals(action)){
            CloudPushService cloudPushService=PushServiceFactory.getCloudPushService();
            if(args.length()<2){
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR,"invaild arguements"));
                return false;
            }
            final boolean temp_flag;
            cloudPushService.bindAccount(args.get(0).toString(), new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.d(TAG, "bindAccount success"+s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.d(TAG, "bindAccount failed"+s+s1);
                }
            });
            TagsAlias tagsAlias=JSONObject.parseObject(args.get(1).toString(),TagsAlias.class);
            cloudPushService.bindTag(tagsAlias.getTag_key(), tagsAlias.getTag_value(), tagsAlias.getAlias(), new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.d(TAG, "bindTag success"+s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.d(TAG, "bindTag failed"+s+s1);
                }
            });
        }
        mCallbackContext.error("error");
        return false;
    }


}