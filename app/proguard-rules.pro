# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-keepattributes EnclosingMethod
-ignorewarnings
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#基线包使用，生成mapping.txt
-printmapping mapping.txt
#生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下
#修复后的项目使用，保证混淆结果一致
#-applymapping mapping.txt
#防止inline(不注释掉的话，日志会被打印出来，但是注释后打正式包会大约12分钟左右才能打完包..)
-dontoptimize
# 保留support下的所有类及其内部类
-keep class android.support.** {*;}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

-keepattributes *Annotation*
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#所有native的方法不能去混淆.
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

 #不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
-keep public class android.net.http.SslError

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String,int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
   public static int e(...);
}
-assumenosideeffects class java.io.PrintStream {
    public *** println(...);
    public *** print(...);
}
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

