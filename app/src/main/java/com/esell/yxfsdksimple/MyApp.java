package com.esell.yxfsdksimple;

import android.app.Application;
import android.content.Context;

import com.esell.yxf.v2.Yxf;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.work.Configuration;

/**
 * @author NiuLei
 * @date 2019/10/22 16:28
 */
public class MyApp extends Application implements Configuration.Provider {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        Yxf.getInstance().attachBaseContext(this);
    }

    @NonNull
    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder().setMinimumLoggingLevel(android.util.Log.INFO).build();
    }
}
