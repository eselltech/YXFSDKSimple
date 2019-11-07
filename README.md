# YXFSDKSimple
广告sdk示例

#1、添加依赖

implementation 'com.esell:yxf:lastVersion'

#2、添加权限

<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    
    <uses-permission android:name="android.permission.INTERNET"/>
    
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    
#3、添加服务

service android:name="com.esell.component_mqtt.EsellMqttService"
android:process=":mqtt" />
            
 #4、获取实例初始化
 
  /*获取实例*/
  
        yxf = Yxf.getInstance();
        
        /*初始化*/
        
        yxf.init(getApplicationContext(), "appId", "appKey",
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
        
        #5、添加广告位
        
        SlotView slotView = new SlotView(getApplicationContext());
        
        container.addView(slotView);
        
  # 可选操作
  
          /*打开调试信息*/
          
        yxf.debug(true);
        
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
        
