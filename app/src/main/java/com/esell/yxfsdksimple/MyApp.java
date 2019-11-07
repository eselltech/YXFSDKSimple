package com.esell.yxfsdksimple;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 *
 * @author NiuLei
 * @date 2019/10/22 16:28
 */
public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
