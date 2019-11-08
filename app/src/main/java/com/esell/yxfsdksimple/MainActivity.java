package com.esell.yxfsdksimple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.esell.component_common.model.AD;
import com.esell.component_common.model.OnAdListener;
import com.esell.component_rtb.RtbRequest;
import com.esell.component_rtb.RtbSlot;
import com.esell.yxf.OnInitListener;
import com.esell.yxf.SlotView;
import com.esell.yxf.Yxf;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = com.esell.yxfsdksimple.MainActivity.class.getSimpleName();
    private Yxf yxf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*获取实例*/
        yxf = Yxf.getInstance();
        /*打开调试信息*/
        yxf.debug(true);
        /*初始化*/
        yxf.init(getApplicationContext(), " yxfAppId", "yxfAppId",
                new OnInitListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess (line 20): ");
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e(TAG, "onFailed (line 26): code : " + code + ",msg : " + msg);
            }
        });
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

        final SlotView slotView = new SlotView(getApplicationContext());
        /*配置屏效宝广告*/
        yxf.rtbConfig("pxbAppId", "pxbAppKey", "唯一编码");
        /*关联广告位*/
        yxf.rtbLink(slotView, new RtbSlot[]{new RtbSlot("广告位id", "类型", 1/*数量*/), new RtbSlot(
                "广告位id", "类型", 1/*数量*/)});

        FrameLayout container = findViewById(R.id.container);
        container.addView(slotView);
    }

    public void btnClick(View view) {
        /*主动获取普通广告*/
        yxf.getNormalAdList(new OnAdListener() {
            @Override
            public void onAd(List<? extends AD> adList) {

            }
        });
        /*主动获取rtb广告*/
        RtbRequest.request(this, "appId", "appKey", "设备唯一编码", "广告位id", "类型", 1/*数量*/, new OnAdListener() {
            @Override
            public void onAd(List<? extends AD> adList) {
                Log.d(TAG,  "onAd (line 21): "+adList);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        yxf.destroy();
    }
}
