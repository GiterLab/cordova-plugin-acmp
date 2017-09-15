package com.duoxieyun;

import android.app.Application;
import android.content.Context;

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
//        this.myContext=Application.class;
        myContext= DXApplication.this;
    }
}
