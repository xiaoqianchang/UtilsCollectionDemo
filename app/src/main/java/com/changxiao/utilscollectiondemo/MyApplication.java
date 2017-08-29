package com.changxiao.utilscollectiondemo;

import android.app.Application;

import com.changxiao.utilscollectiondemo.utils.DeviceInfo;

/**
 * $desc$
 * <p>
 * Created by Chang.Xiao on 2017/8/29.
 *
 * @version 1.0
 */

public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        this.application = this;
        DeviceInfo.init(this);
    }

    public static final MyApplication getIntstance() {
        return application;
    }
}
