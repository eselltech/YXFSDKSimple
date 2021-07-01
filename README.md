## [1x版本](https://github.com/eselltech/YXFSDKSimple/tree/legacy-1x)
## [2xdoc](./doc2x)
## 2x版本
    20210701
        1、素材压缩 应用compress-str字段
        2、支持bp下发移动流量控制
        3、修复7.0以上定时广告异常
    20210628
        1、修复单视频播放 第二次播放 重复第一帧
    20210625
        1、添加物联网连接成功回调
        {@link com.esell.yxf.v2.Yxf#addOnIotConnectListener(IOT.OnIotConnectListener)}
        2、经纬度上报
        {@link com.esell.yxf.v2.Yxf#publishLocation(double, double, Callback2)}
    20210623
        1、添加人脸数据记录公开方法
        {@link com.esell.yxf.v2.Yxf#noteFace(int, int, String, int)}
    20210615
        1、支持互动按钮组件
    20210524
        1、支持倒计时组件、混合广告
    20210518
        1、支持天气组件、网络信号
    20210513
        1、支持广告策略粒度到分钟
    20210512
        1、支持运营策略:日志缓存时长、素材缓存时长、展现量离线缓存时长、展现量上报周期、播放类型、播放组件、素材显示类型、屏幕旋转

    20210510
        1、支持bp下发基础控制、定时开关机、远程截屏、音量策略、亮度策略、下载策略
            {@link Yxf#setController(Application, IController)}
            
    2.0.0-SNAPSHOT 20210506
        1、仓库迁移到 mavenCenter,根目录gradle 添加仓库
            allprojects {
                repositories {
                    ...
                    maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
                }
            }
        2、依赖groupId 更改 implementation 'io.github.eselltech:artifactId:version'
        3、接入逻辑修改 引入屏、模板、组件概念
        4、支持组件:广告位、固定图片、固定视频、固定文本、时钟、静态网页、浏览器、扫码投屏
        5、支持广告:图片、视频、文本、网站、wps
## 添加仓库
    allprojects {
        repositories {
            google()
            jcenter()
            maven {  //阿里云远程仓库
                url "http://maven.aliyun.com/nexus/content/repositories/releases"
            }
            maven { url 'https://s01.oss.sonatype.org/content/repositories/snapshots/' }
        }
    }
## 添加依赖
    implementation 'io.github.eselltech:yxf:2.0.0-SNAPSHOT'
## 必要配置
    implementation 'androidx.multidex:multidex:2.0.1'

    自定义Application 添加到 AndroidManifest.xml

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
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
        <uses-permission android:name="android.permission.INTERNET"/>
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
## 添加服务
        <service
            android:name="com.esell.component_mqtt.EsellMqttService"
            android:process=":mqtt" />
## [混淆](./app/proguard-rules.pro)


        
