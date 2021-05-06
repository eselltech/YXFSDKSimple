package com.esell.yxfsdksimple;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.esell.component_log.Log;
import com.esell.component_widget.v2.IDisplayData;
import com.esell.yxf.bean.Screen;
import com.esell.yxf.v2.Callback;
import com.esell.yxf.v2.OnRegisterListener;
import com.esell.yxf.v2.Yxf;

import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    private Yxf yxf;
    public static final String APP_ID = "ade3qax24449f80b6";
    public static final String APP_KEY = "n6b1dls5b7c40ADEQdr3ab1b3c31386";
    public static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {/*6.0以上申请权限*/
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "未获取到所需权限", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
                return;
            }
        }
        init();
    }

    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION,
            WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, READ_PHONE_STATE})
    private void init() {
        yxf = Yxf.getInstance();
        /*打开调试信息*/
        yxf.debug(true);
        yxf.attach(0, findViewById(R.id.container));
        /*广告列表请求回调*/
        /*yxf.addOnRequestDisplayListListener(new Callback<Map<Integer, List<IDisplayData>>>() {
            @Override
            public void onSuccess(Map<Integer, List<IDisplayData>> body) {
                Log.i(TAG,  "onSuccess (line 44): "+new Gson().toJson(body));
            }

            @Override
            public void onFailed(int code, String describe) {

            }
        });*/
        /*播放监听*/
        /*
        yxf.addOnDisplayListener(new OnDisplayListener() {
            @Override
            public void onDisplayStart(BasicSlotView basicSlotView, IDisplayData iDisplayData) {
                Log.i(TAG, "onDisplayStart");
            }

            @Override
            public void onDisplayIng(BasicSlotView basicSlotView, IDisplayData iDisplayData,
                                     int i) {
            }

            @Override
            public void onDisplayEnd(BasicSlotView basicSlotView, IDisplayData iDisplayData) {
                Log.i(TAG, "onDisplayEnd");
            }

            @Override
            public void onIdle(BasicSlotView basicSlotView) {

            }

            @Override
            public void onTemplatePreDraw() {

            }
        });*/
        /*远程控制监听*/
        /*
        yxf.addOnRemoteControlListener(new OnRemoteControlListener() {
            @Override
            public void onBrightness(int brightness) {
                android.util.Log.i(TAG, "onBrightness (line 66): " + brightness);
            }

            @Override
            public void onVolume(int volume) {
                android.util.Log.i(TAG, "onVolume (line 71): " + volume);
            }

            @Override
            public void onReboot() {
                android.util.Log.i(TAG, "onReboot (line 76): ");
            }

            @Override
            public void onShutDown() {
                android.util.Log.i(TAG, "onShutDown (line 81): ");
            }

            @Override
            public void onClear() {
                android.util.Log.i(TAG, "onClear (line 86): ");
            }
        });*/
        /*注册*/
        yxf.register(getApplication(), APP_ID, APP_KEY, new OnRegisterListener() {
            @Override
            public void onIotSuccess() {
                android.util.Log.d(TAG, "onIotSuccess");
            }

            @Override
            public void onRegisterSuccess() {
                android.util.Log.d(TAG, "onRegisterSuccess");
            }

            @Override
            public void onFailed(int code, String msg) {
                android.util.Log.e(TAG, "onFailed (line 26): code : " + code + ",msg : " + msg);
            }
        }, new Screen(0, 1080, 720), new Screen(1, 800, 400));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        yxf.destroy();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Yxf.getInstance().attachBaseContext(getApplication());
            init();
        }
    }

    public void getDeviceNum(View view) {
        Log.d("deviceNum : " + yxf.getDeviceNum());
    }

    public void getNormalAdList(View view) {
        yxf.requestDisplayListByNet(0, new Callback<Map<Integer, List<IDisplayData>>>() {
            @Override
            public void onSuccess(Map<Integer, List<IDisplayData>> body) {

            }

            @Override
            public void onFailed(int code, String describe) {

            }
        });
    }
}
