# 注意 WorkManager 偶现异常 IllegalStateException("WorkManager is not initialized properly")
         Application 实现 Configuration.Provider接口
         class MyApplication extends Application implements Configuration.Provider {
                @Override
                public Configuration getWorkManagerConfiguration() {
                    return Configuration.Builder()
                            .setMinimumLoggingLevel(android.util.Log.INFO)
                            .build();
                }
            }
    
# 注意 2020/10/21 yxf-1.0.9 版本以下 OSS出现编译异常 Failed to resolve: oss-android-sdk-2.9.5 [oss issues](https://github.com/aliyun/aliyun-oss-android-sdk/issues/243)
        
        使用指定版本修复
        api 'com.aliyun.dpa:oss-android-sdk:2.9.4'
        implementation ('com.esell:yxf:xxx'){
            exclude(group: 'com.aliyun.dpa', module: 'oss-android-sdk')
        }
        
# androidX release
    1.1.1
        1、dataHub上报取消TrackUrl限制、扩容分片
        2、支持基本控制监听
            /**
             * see{@link com.esell.yxf.Yxf#setOnRemoteControlListener(OnRemoteControlListener)}
             * see{@link com.esell.yxf.remote.OnRemoteControlListener}
             * see{@link com.esell.yxf.remote.OnSimpleRemoteControlListener}
             * see{@link com.esell.yxf.remote.OnDefRemoteControlListener}
             * see{@link com.esell.yxf.remote.RemoteControlUtils}
             */
    1.1.0
        1、广告添加投放周期限制
    1.0.9
        1、指定使用阿里oss 2.9.4版本
        2、添加Web广告支持
    1.0.8
        1、修复1.0.7版本广告未设置监播地址导致只能播放一个广告的BUG
        2、添加日播放次数上限方法{@link com.esell.yxf.NormalAD#getDayPlayNum()}
    1.0.7
        1、CarouselView 添加播放方式配置方法
             {@link com.esell.component_widget.CarouselView#setDefVideoType(int)}
             {@link com.esell.component_widget.VideoViewCompat#WHICH_TEXTURE_VIEW}
             {@link com.esell.component_widget.VideoViewCompat#WHICH_VIDEO_VIEW}
        2、屏效宝广告监播地址补报处理
             {@link com.esell.yxf.PXBReportWork#report(Application, int)}}
             {@link com.esell.yxf.PXBReportWork#saveReportFailedItem(AD)}
             sdk默认已调用report方法.如使用SlotView 无需任何操作,
             如未使用SlotView,自行在屏效宝广告监播上报失败后使用saveReportFailedItem方法保存即可.
             代码示例
             if (trackUrl.startsWith(PXBReportWork.PXB_URL)) {
                    PXBReportWork.saveReportFailedItem(ad);
                 }
                 
             
    1.0.5
        1、添加方法 主动清理不在广告列表内的素材 Yxf#cleanUpMaterial()
    1.0.4
        1、修复视频素材不下载问题
    1.0.3
        1、添加素材下载进度监听 Yxf#setDownloadListener(DownloadListener)
    1.0.2
        1、迁移到androidX
        2、模块整理、 一些类位置更改、删除
# appCompat release
    0.3.5
        1、修复下载框架异常
        2、添加下载监听Yxf#setDownloadListener(DownloadListener)
        3、添加方法 主动清理不在广告列表内的素材 Yxf#cleanUpMaterial()
    0.3.4
        1、已有trackUrl上报路径的广告 不再dataHub重复上报
        2、废弃yxf.disableDataHubReport()方法,使用setSupportDataHubReport(boolean support)代替
    0.3.3
        1、网络请求响应json数据解析异常处理
    0.3.2
        1、添加全局广告请求监听setOverallAdListener
    0.3.1
        1、支持自定义广告处理 详情参看doc
    0.3.0
        1、init逻辑优化 
        2、广告获取逻辑优化
    0.2.9
        1、修改文件下载完成判断逻辑 
    0.2.8
        1、OkDownloadListener ClassCastException 
    0.2.7
        1、添加http前缀网络请求支持
    0.2.6
        1、修复 未定位成功 重新走注册流程问题
    ---
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
    }
## 添加依赖 
    implementation 'com.esell:yxf:1.1.1'
## 必要配置
    implementation 'androidx.multidex:multidex:2.0.1'
    自定义Application 添加到 AndroidManifest.xml
    public class MyApp extends Application {
        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(this);
            Yxf.getInstance().attachBaseContext(base);
        }
    }

    android {
        ...
        defaultConfig {
            ...
            multiDexEnabled true
            ...
        }

        compileOptions {
            sourceCompatibility 1.8
            targetCompatibility 1.8
        }
        packagingOptions {
            pickFirst  'lib/arm64-v8a/libc++_shared.so'
            pickFirst  'lib/x86/libc++_shared.so'
            pickFirst  'lib/armeabi-v7a/libc++_shared.so'
        }
    }

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
        /*报备广告位id*/
        yxf.report(slotId1,slotId2,slotId3);
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
    -keep class com.tencent.mars.xlog.** { *; }
              

        
