package com.esell.yxfsdksimple;

import android.app.Application;
import android.content.Context;

import com.esell.yxf.Yxf;

import androidx.multidex.MultiDex;

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
        Yxf.getInstance().attachBaseContext(base);
    }
}
