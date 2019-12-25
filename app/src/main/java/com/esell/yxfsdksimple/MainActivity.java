package com.esell.yxfsdksimple;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.esell.component_common.model.AD;
import com.esell.component_common.model.OnAdListener;
import com.esell.component_rtb.RtbRequest;
import com.esell.component_rtb.RtbSlot;
import com.esell.yxf.OnInitListener;
import com.esell.yxf.SlotView;
import com.esell.yxf.Yxf;

import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = com.esell.yxfsdksimple.MainActivity.class.getSimpleName();
    private Yxf yxf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {/*6.0以上申请权限*/
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) || PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(this, "未获取到所需权限", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        }, 101);
                return;
            }
        }
        init();

    }
    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    private void init() {
        /*获取实例*/
        yxf = Yxf.getInstance();
        /*打开调试信息*/
        yxf.debug(true);
        /*初始化*/
        yxf.init(getApplicationContext(), " yxfAppId", "yxfAppId", new OnInitListener() {
            @Override
            public void onSuccess() {
                /*获取设备编号*/
                String deviceNum = yxf.getDeviceNum();
                Log.i(TAG, "onSuccess (line 20): " + deviceNum);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e(TAG, "onFailed (line 26): code : " + code + ",msg : " + msg);
            }
        });

        /*报备广告位id*/
        yxf.report(slotId1, slotId2, slotId3);
        //        自实现配置
        //        ConfigManager configManager = ConfigManager.getInstance();
        //        本地缓存
        //        configManager.setCache();
        //        图片加载
        //        configManager.setImageLoad();
        //        网络请求
        //        configManager.setNetRequest();
        //        下载
        //        configManager.setDownload();
        //广告位实例方式1
        final SlotView slotView = new SlotView(getApplicationContext());
        /*设置广告位id*/
        slotView.setYxfSlotId(slotId);
        /*添加广告位描述*/
        slotView.setYxfSlotDes("广告位描述");
        yxf.link(slotView);
        //广告位实例方式2  不需要link
        //         SlotView slotView = yxf.newSlotView(slotId, "广告位描述");


        /*rtb关联广告位*/
        yxf.rtbLink(slotView, new RtbSlot[]{new RtbSlot("广告位id", "类型", 1/*数量*/), new RtbSlot(
                "广告位id", "类型", 1/*数量*/)});

        FrameLayout container = findViewById(R.id.container);
        container.addView(slotView);

        //        打开调试信息
        //        yxf.debug(true);

        //        自实现配置

        //        ConfigManager configManager = ConfigManager.getInstance();

        //        本地缓存

        //        configManager.setCache();

        //        图片加载

        //        configManager.setImageLoad();

        //        网络请求

        //        configManager.setNetRequest();

        //        下载

        //        configManager.setDownload();
    }

    public void btnClick(View view) {
        /*主动获取普通广告*/
        yxf.getNormalAdList(new OnAdListener() {
            @Override
            public void onAd(List<? extends AD> adList) {

            }
        });
        /*主动获取rtb广告*/
        RtbRequest.request(this, "appId", "appKey", "设备唯一编码", "广告位id", "类型", 1/*数量*/,
                new OnAdListener() {
            @Override
            public void onAd(List<? extends AD> adList) {
                Log.d(TAG, "onAd (line 21): " + adList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        yxf.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            init();
        }
    }
}
