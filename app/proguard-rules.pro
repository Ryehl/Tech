# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.tencent.mm.opensdk.** {
    *;
}
-keep class com.tencent.wxop.** {
    *;
}
-keep class com.tencent.mm.sdk.** {
    *;
}

#========================JMRTC================================
-dontwarn cn.jiguang.jmrtc.**
-keep class cn.jiguang.jmrtc.api.** {*;}

#========================Agora================================
-dontwarn io.agora.rtc.**
-keep class io.agora.rtc.** {*;}
-optimizationpasses 7


-dontusemixedcaseclassnames		#混淆时不使用大小写混合，混淆后的类名为小写

-dontpreverify				#不做预检验，preverify是proguard的四个步骤之一,Android不需要preverify，去掉这一步可以加快混淆速度

-verbose				#混淆后会生成映射文件

-ignorewarnings				#忽略警告




# 指定不去忽略非公共的库的类

# 默认跳过，有些情况下编写的代码与类库中的类在同一个包下，并且持有包中内容的引用，此时就需要加入此条声明

-dontskipnonpubliclibraryclasses




# 指定不去忽略非公共的库的类的成员

-dontskipnonpubliclibraryclassmembers




-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*	#混淆时所采用的算法







-keep public class * extends android.app.Activity			#保持哪些类不被混淆

-keep public class * extends android.app.Application			#保持哪些类不被混淆

-keep public class * extends android.app.Service			#保持哪些类不被混淆

-keep public class * extends android.content.BrodcastReceiver		#保持哪些类不被混淆

-keep public class * extends android.content.ContentProvider		#保持哪些类不被混淆

-keep public class * extends android.app.backup.BackupAgentHelper	#保持哪些类不被混淆

-keep public class * extends android.preference.Preference		#保持哪些类不被混淆

-keep public class com.android.vending.licensing.ILicensingService      #保持哪些类不被混淆




#保持 native 方法不被混淆

-keepclasseswithmembernames class * {

    native <methods>;

}




#保持自定义控件类不被混淆

-keepclasseswithmembers class * {

    public <init>(android.content.Context);

}

-keepclasseswithmembernames class * {

    public <init>(android.content.Context, android.util.AttributeSet);

}




-keepclasseswithmembernames class * {

    public <init>(android.content.Context, android.util.AttributeSet, int);

}




#保持 Serializable 不被混淆并且enum 类也不被混淆

-keepclassmembers class * implements java.io.Serializable {

    static final long serialVersionUID;

    private static final java.io.ObjectStreamField[] serialPersistentFields;

    !static !transient <fields>;

    !private <fields>;

    !private <methods>;

    private void writeObject(java.io.ObjectOutputStream);

    private void readObject(java.io.ObjectInputStream);

    java.lang.Object writeReplace();

    java.lang.Object readResolve();

    public void set*(***);

    public *** get*();

}




#保持 Parcelable 不被混淆

-keep class * implements android.os.Parcelable {

  public static final android.os.Parcelable$Creator *;

}




# 对R文件下的所有类及其方法，都不能被混淆

-keepclassmembers class **.R$* {

    *;

}




# 对于带有回调函数onXXEvent的，不能混淆

-keepclassmembers class * {

    void *(**On*Event);

}




#apk 包内所有 class 的内部结构

-dump class_files.txt

#未混淆的类和成员

-printseeds seeds.txt

#列出从 apk 中删除的代码

-printusage unused.txt

#混淆前后的映射

-printmapping mapping.txt




# 抛出异常时保留代码行号

-keepattributes SourceFile,LineNumberTable




# 保护代码中的Annotation不被混淆

# 这在JSON实体映射时非常重要，比如fastJson

-keepattributes *Annotation*




# 避免混淆泛型

# 这在JSON实体映射时非常重要，比如fastJson

-keepattributes Signature




#如果引用了v4或者v7包

-dontwarn android.support.**




#----------------------webview(webView中js和本地代码交互,保持"桥梁类"不被混淆)------------------------------------

-keepclassmembers class fqcn.of.javascript.interface.for.Webview {

   public *;

}

-keepclassmembers class * extends android.webkit.WebViewClient {

    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);

    public boolean *(android.webkit.WebView, java.lang.String);

}

-keepclassmembers class * extends android.webkit.WebViewClient {

    public void *(android.webkit.WebView, jav.lang.String);

}

#----------------------------------------------------------------------------

#-------------------------------------------定制化区域----------------------------------------------#

#======================【三方配置】(看自己项目有没有这些第三方,没有就不用添加对应配置)==================================

#环信混淆--------------------------------------------

-keep class com.easemob.** {*;}

-keep class org.jivesoftware.** {*;}

-keep class org.apache.** {*;}

-dontwarn  com.easemob.**

#另外，demo中发送表情的时候使用到反射，需要keep SmileUtils

-keep class com.easemob.chatuidemo.utils.SmileUtils {*;}

#注意前面的包名，如果把这个类复制到自己的项目底下，比如放在com.example.utils底下，应该这么写（实际要去掉#）

#-keep class com.example.utils.SmileUtils {*;}

#如果使用EaseUI库，需要这么写

-keep class com.easemob.easeui.utils.EaseSmileUtils {*;}




#BaiduMap

#-libraryjars libs/baidumapapi_v3_5_0.jar

#-libraryjars libs/locSDK_6.13.jar

-keep class com.baidu.** { *; }

-dontwarn com.baidu.**

-keep class vi.com.gdi.bgl.android.**{*;}

-dontwarn vi.com.gdi.bgl.android.**

-keep class android.util.FloatMath.**{*;}

-dontwarn android.util.FloatMath.**




#Volley

-keep class com.android.volley.** {*;}

-keep class com.android.volley.toolbox.** {*;}

-keep class com.android.volley.Response$* { *; }

-keep class com.android.volley.Request$* { *; }

-keep class com.android.volley.RequestQueue$* { *; }

-keep class com.android.volley.toolbox.HurlStack$* { *; }

-keep class com.android.volley.toolbox.ImageLoader$* { *; }




#gson(类型转换错误,使用Gson之类的一定要加上)

-keepattributes Signature

-keepattributes *Annotation*

-keep class sun.misc.Unsafe { *; }

-keep class com.google.gson.examples.android.model.** { *; }

-keep class com.google.gson.reflect.TypeToken.**{ *;}

-dontwarn com.google.gson.reflect.TypeToken.**

-keep class org.json.JSONObject { *;}

-keep class com.google.gson.JsonObject { *; }

-keep class com.dianguanjia.net.** { *; }

-keep class com.dianguanjia.views.treeview.** { *; }

-dontwarn com.dianguanjia.views.treeview.**




#2.0.9后加入语音通话功能，如需使用此功能的API，加入以下keep

-dontwarn ch.imvs.**

-dontwarn org.slf4j.**

-keep class org.ice4j.** {*;}

-keep class net.java.sip.** {*;}

-keep class org.webrtc.voiceengine.** {*;}

-keep class org.bitlet.** {*;}

-keep class org.slf4j.** {*;}

-keep class ch.imvs.** {*;}

-keep class com.hyphenate.** {*;}

-dontwarn  com.hyphenate.**




#二维码zxing

-dontwran com.google.zxing.**

-keep class com.google.zxing.**{*;}




#支付宝

-dontwran com.alipay.android.app.**

-keep class com.alipay.android.app.**{*;}




#okhttp

-keep class com.squareup.okhttp.** { *;}

-dontwarn okio.**

-keepclassmembers class **.R$* {

    public static <fields>;

}

#eventbus

-keepattributes *Annotation*

-keepclassmembers class ** {

    @org.greenrobot.eventbus.Subscribe <methods>;

}

-keep enum org.greenrobot.eventbus.ThreadMode { *; }




# Only required if you use AsyncExecutor

-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {

    <init>(java.lang.Throwable);

}

#友盟分享

 -dontwarn com.google.android.maps.**

 -dontwarn android.webkit.WebView

 -dontwarn com.umeng.**

 -dontwarn com.tencent.weibo.sdk.**

 -dontwarn com.facebook.**

 -keep public class javax.**

 -keep public class android.webkit.**

 -dontwarn android.support.v4.**

 -keep class android.support.** {*;}

 -keep enum com.facebook.**

 -keepattributes Exceptions,InnerClasses,Signature

 -keepattributes *Annotation*

 -keepattributes SourceFile,LineNumberTable




 -keep public interface com.facebook.**

 -keep public interface com.tencent.**

 -keep public interface com.umeng.socialize.**

 -keep public interface com.umeng.socialize.sensor.**

 -keep public interface com.umeng.scrshot.**




#视频直播混淆

-dontwarn com.gensee.**

-keep  class  com.gensee.**{*;}

-dontwarn com.tictactec.ta.**




#fastjson

-dontwarn com.alibaba.fastjson.**

-keep class com.alibaba.fastjson.** { *; }




# fresco

-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

-keep @com.facebook.common.internal.DoNotStrip class *

-keepclassmembers class * {@com.facebook.common.internal.DoNotStrip *;}

-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {

    public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory, com.facebook.imagepipeline.core.ExecutorSupplier);

}

-keep class com.facebook.animated.gif.** {*;}

-dontwarn javax.annotation.**




#保留混淆mapping文件

-printmapping build/outputs/mapping/release/mapping.txt







#growingio统计

-keep class com.growingio.android.sdk.** {

    *;

}

-dontwarn com.growingio.android.sdk.**

-keepnames class * extends android.view.View

-keep class * extends android.app.Fragment {

    public void setUserVisibleHint(boolean);

    public void onHiddenChanged(boolean);

    public void onResume();

    public void onPause();

}

-keep class android.support.v4.app.Fragment {

    public void setUserVisibleHint(boolean);

    public void onHiddenChanged(boolean);

    public void onResume();

    public void onPause();

}

-keep class * extends android.support.v4.app.Fragment {

    public void setUserVisibleHint(boolean);

    public void onHiddenChanged(boolean);

    public void onResume();

    public void onPause();

}


-libraryjars log4j-1.2.17.jar

-dontwarn org.apache.log4j.**

-keep class  org.apache.log4j.** { *;}

-keep class com.demo.yichenfeng.bean.** { *; }
