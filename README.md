# release

    0.2.4
        1、修复 销毁sdk后 再次进入app不能更新广告问题
        
    0.2.3
        1、初始化逻辑优化
        2、dataHub展现量上报
        注:需添加新的仓库 maven { url 'https://jitpack.io' }
    ---
    0.2.0
        1、修复销毁方法空指针
    0.1.9
        1、网络请求原生实现
    ---
    0.1.6
        1、初始化内部逻辑添加位置信息
    0.1.5
        1、OkHttpRequestImp$CallbackAdapter$run()$e.toString() 空指针 修复
    0.1.4
        1、添加广告下载状态方法
    0.1.3 
        1、去除rtb相关
    0.1.2 
        1、添加定位上报
    0.1.1 
        1、添加获取设备编号方法
    0.1.0 
        1、修复广告更新后轮播异常
    0.0.9 
        1、添加广告位报备

# 1、配置
## 添加仓库 
    repositories {
        google()
        jcenter()
        maven {  //阿里云远程仓库
            url "http://maven.aliyun.com/nexus/content/repositories/releases"
        }
        maven { url 'https://jitpack.io' }
    }
## 添加依赖 
    implementation 'com.esell:yxf:0.2.4'
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
        
## 可选操作

        //        获取设备编号 注:初始化成功后获取
        //        yxf.getDeviceNum();
  
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
        
# 3、混淆规则
 
    #sdk相关
    -keep class com.esell.** { *; }
    #阿里物联网混淆
    # linkkit API
    -keep class com.aliyun.alink.**{*;}
    -keep class com.aliyun.linksdk.**{*;}
    -dontwarn com.aliyun.**
    -dontwarn com.alibaba.**
    -dontwarn com.alipay.**
    -dontwarn com.ut.**
    # keep native method
    -keepclasseswithmembernames class * {
    native <methods>;
    }
    # keep netty
    -keepattributes Signature,InnerClasses
    -keepclasseswithmembers class io.netty.** {
    *;
    }
    -dontwarn io.netty.**
    -dontwarn sun.**
    # keep mqtt
    -keep public class org.eclipse.paho.**{*;}
    # keep fastjson
    -dontwarn com.alibaba.fastjson.**
    -keep class com.alibaba.fastjson.**{*;}
    # keep gson
    -keep class com.google.gson.** { *;}
    # keep network core
    -keep class com.http.**{*;}
    # keep okhttp
    -dontwarn okhttp3.**
    -dontwarn okio.**
    -dontwarn javax.annotation.**
    -keep class okio.**{*;}
    -keep class okhttp3.**{*;}
    -keep class org.apache.commons.codec.**{*;}
    ##OSS
    -keep class com.alibaba.sdk.android.oss.** { *; }
    -dontwarn okio.**
    -dontwarn org.apache.commons.codec.binary.**
              

        
