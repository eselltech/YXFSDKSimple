import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.esell.component_rtb.RtbSlot;
import com.esell.yxf.OnInitListener;
import com.esell.yxf.SlotView;
import com.esell.yxf.Yxf;
import com.esell.yxfsdksimple.R;

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
        yxf.init(getApplicationContext(), "zjXTg5lcVvOnztX", "YJLsO0sbByHy76mAvCn7jMGFymzwi3p",
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
        yxf.rtbConfig("vazazjhenhc5psfz", "71vr6anlbg2f9lvhbbhgj8fy79b55xuc", "419102906489");
        /*关联广告位*/
        yxf.rtbLink(slotView, new RtbSlot[]{new RtbSlot("25075489", "IMG", 1), new RtbSlot(
                "25075490", "VDO", 1)});

        FrameLayout container = findViewById(R.id.container);
        container.addView(slotView);
    }

    public void btnClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        yxf.destroy();
    }
}
