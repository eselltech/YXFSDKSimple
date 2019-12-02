# 1、配置
## 添加仓库 
    repositories {
        google()
        jcenter()
        maven {  //阿里云远程仓库
            url "http://maven.aliyun.com/nexus/content/repositories/releases"
        }
    }
## 添加依赖 
    implementation 'com.esell:yxf:lastVersion'
## 使用java8
    compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
    }
## 由于物联网代码方法比较多需自行分包配置
    1、implementation 'com.android.support:multidex:1.0.3'
    2、android {
        ...
        defaultConfig {
            ...
            multiDexEnabled true
            ...
        }
       }
    3、自定义Application 添加到 AndroidManifest.xml


## 添加权限
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

    
## 添加服务
        <service
            android:name="com.esell.component_mqtt.EsellMqttService"
            android:process=":mqtt" />
# 2、使用
## 动态申请存储权限
## 初始化   
        /*获取实例*/
        yxf = Yxf.getInstance();
        /*打开调试信息*/
        yxf.debug(true);
        /*初始化*/
        yxf.init(getApplicationContext(), " yxfAppId", "yxfAppId", new OnInitListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "onSuccess (line 20): ");
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.e(TAG, "onFailed (line 26): code : " + code + ",msg : " + msg);
            }
        });
        /*报备广告位id*/
        yxf.report(slotId1,slotId2,slotId3);
        
        <!--添加广告位-->
        final SlotView slotView = new SlotView(getApplicationContext());
        /*设置广告位id*/
        slotView.setYxfSlotId(slotId);
        /*添加广告位描述*/
        slotView.setYxfSlotDes("广告位描述");
        /*rtb关联广告位*/
        yxf.rtbLink(slotView, new RtbSlot[]{new RtbSlot("广告位id", "类型", 1/*数量*/), new RtbSlot(
                "广告位id", "类型", 1/*数量*/)});
        
## 可选操作
  
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
                
        /*主动获取普通广告*/
        yxf.getNormalAdList(new OnAdListener() {
            @Override
            public void onAd(List<? extends AD> adList) {
                Log.d(TAG, "onAd (line 21): " + adList);
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


        
