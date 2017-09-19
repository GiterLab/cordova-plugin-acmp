package org.giterlab;

import android.app.Application;
import android.content.Context;

import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

/**
 * Created by wxj on 2017/9/11.
 */

public class DXApplication extends Application {
    private static DXApplication myContext;

    public DXApplication(){}
    public static DXApplication getContext(){
        return myContext;
    }

    public void onCreate(){
        super.onCreate();
        PushServiceFactory.init(this);
        myContext= DXApplication.this;
    }
}
