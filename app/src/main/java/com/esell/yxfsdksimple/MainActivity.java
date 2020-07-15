package com.esell.yxfsdksimple;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.esell.component_common.YLog;
import com.esell.component_common.model.AD;
import com.esell.yxf.CustomAD;
import com.esell.yxf.CustomADSet;
import com.esell.yxf.OnInitListener;
import com.esell.yxf.SlotView;
import com.esell.yxf.Yxf;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private Yxf yxf;
    /**
     * 测试图片广告素材
     */
    String urls[] = {"https://file1.yixinfa" + ".cn/workstation/2007/15" +
            "/285646a7ef0b64c738bd3823379bd45a.jpg",
            "https://file1" + ".yixinfa.cn/workstation" + "/2007/15" +
                    "/cdb5ab994a02700cdc87024332f828a6.png",
            "https" + "://file1.yixinfa" + ".cn" + "/workstation/2007/15" +
                    "/a757b4ae733db6a488cb7ed2674f9496.jpg",
            "https://file1" + ".yixinfa" + ".cn" + "/workstation/2007/15" +
                    "/da19d792aaed9fdd398b746c311cb32d.jpg"};
    /**
     * TODO: 设置参数
     */
    public static final String APP_ID = ;
    public static final String APP_KEY = ;
    public static final int SLOT_ID = ;

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
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
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
        /*报备广告位id*/
        yxf.report(SLOT_ID);
        /*初始化*/
        yxf.init(getApplicationContext(), APP_ID, APP_KEY, new OnInitListener() {
            @Override
            public void onSuccess() {
                /*获取设备编号*/
                String deviceNum = yxf.getDeviceNum();
                YLog.i("onSuccess (line 20): " + deviceNum);
            }

            @Override
            public void onFailed(int code, String msg) {
                YLog.e("onFailed (line 26): code : " + code + ",msg : " + msg);
            }
        });
        //广告位实例方式2  不需要link
        SlotView slotView = yxf.newSlotView(SLOT_ID, "广告位描述");

        FrameLayout container = findViewById(R.id.container);
        container.addView(slotView);
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
        //        //广告位实例方式1
        //        final SlotView slotView = new SlotView(getApplicationContext());
        //        /*设置广告位id*/
        //        slotView.setYxfSlotId(slotId);
        //        /*添加广告位描述*/
        //        slotView.setYxfSlotDes("广告位描述");
        //        yxf.link(slotView);


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

        //        上报广告下载状态
        //        yxf.reportDownloadStatus(ad);
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

    public void getDeviceNum(View view) {
        YLog.d("deviceNum : " + yxf.getDeviceNum());
    }

    public void getNormalAdList(View view) {
        yxf.getNormalAdList(adList -> YLog.d(adList == null ? "null" : adList.toString()));
    }

    public void addCustomList(View view) {
        List<CustomAD> list = new ArrayList<>();
        for (String url : urls) {
            CustomAD customAD = new CustomAD(url.hashCode(), SLOT_ID, AD.ContentType.IMG, url,
                    15 * 1000);
            list.add(customAD);
        }
        CustomADSet customADSet = yxf.getCustomADSet();
        customADSet.addCustomList(list);
        YLog.d("customADSet.size : " + customADSet.size());
    }

    public void cleanCustomList(View view) {
        CustomADSet customADSet = yxf.getCustomADSet();
        customADSet.clear();
        YLog.d("customADSet.size : " + customADSet.size());
    }
}
