package com.cx;

import android.app.Application;

import com.tencent.mmkv.MMKV;

/**
 * Created by ly-chenxiao on 25/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }
}
