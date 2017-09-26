package org.giterlab;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

/**
 * Created by wxj on 2017/9/11.
 */

public class DXReceiver extends MessageReceiver {

    private static MsgCallBack msgCallBack;

    private static NotifyCallBack notifyCallBack;

    public static void setNotifyCallBack(NotifyCallBack notifyCallBack) {
        DXReceiver.notifyCallBack = notifyCallBack;
    }

    public void setCallBack(MsgCallBack callBack){
        msgCallBack=callBack;
    }

    // 消息接收部分的LOG_TAG
    public static final String REC_TAG = "receiver";
    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        System.out.println("----------------->1:"+title+summary+extraMap.toString());
        Log.e("MyMessageReceiver", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
    }
    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        msgCallBack.onMsgResvice(JSONObject.toJSONString(cPushMessage));
        System.out.println("----------------->2:"+context.toString()+cPushMessage.toString());
        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }
    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        String jsstr="{\"title\":\""+title+"\",\"summary\":\""+summary+"\",\"extraMap\":\""+extraMap+"\"}";
        notifyCallBack.onNotifyClick(jsstr);
        System.out.println("----------------->3:"+context.toString()+title+summary+extraMap.toString());
        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        System.out.println("----------------->4:"+context.toString()+title+summary+extraMap.toString());
        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }
    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        System.out.println("----------------->5:"+context.toString()+title+summary+extraMap.toString()+openType+openActivity+openUrl);
        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        System.out.println("----------------->6:"+context.toString()+messageId);
        Log.e("MyMessageReceiver", "onNotificationRemoved");
    }
}
