# bfc-imageLoader
中间件项目：图片缓存库
# Feedback
**图片缓存库的需求或使用不爽的地方可在GitLab上吐槽。[Issues传送门](http://172.28.2.93/bfc/BfcImageLoader/issues)**

**生命在于折腾（各版本升级，可能存在较大改动，使用前请尽可能查看最新Readme文档的[版本更新日志](#update-list)及[使用说明](#using)**


# Design
* [框架设计](http://172.28.2.93/bfc/BfcImageLoader/raw/develop/doc/screenshots/structure_design.png)
* [详细设计](http://172.28.2.93/bfc/BfcImageLoader/raw/develop/doc/screenshots/inteface_design.png)

# Gradle
添加私有maven配置

gradle.properties
```properties
    #本地库URL
    MAVEN_URL= http://172.28.1.147:8081/nexus/content/repositories/thirdparty/
```
project build.gradle
```groovy
    allprojects {
        repositories {
            jcenter()
            maven { url MAVEN_URL }
        }
    }
```
module build.gradle
```groovy
    compile 'com.eebbk.bfc:bfc-imageloader:0.1.0+'
```
# Dependence
内部依赖项目
```groovy
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.android.support:support-v4:23.4.0'
    compile 'jp.wasabeef:glide-transformations:2.0.1'

```
# Build info
构建信息
```groovy
    compileSdkVersion 23
    buildToolsVersion "24.0.0"
    minSdkVersion 15
    targetSdkVersion 24
```
# Inner Permission
```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
# Update List
* V0.1.0-beta 基本网络图片缓存封装
    * 加载参数支持网络url及资源id
* V0.2.0-beta 图片缓存库基本功能实现
    * 添加加载参数Uri的支持
    * 缓存配置及相关操作实现
* V0.3.0-beta
    * 添加Fragment入口参数
* V0.1.0
    * V0.3.0-beta对应版本
* V0.4.0-beta
    * 修改回调设置入口及添加图片加载位置设置接口
* V0.4.0
    * V0.4.0-beta对应版本
* V0.5.0
    * 修改主题商店集成中发现返回Drawable不确定的bug，添加在配置信息中设置返回图片类型
    * 添加into方法target参数，允许直接获取下载Bitmap
* V0.5.1
    * 添加SDKVersion用于显示库信息
* V0.5.2
    * 丰富ImageTarget加载回调接口
* V2.0.0
    * BFC大版本发布，统一版本号
* V2.0.1-rc
    * 更新readme，静态代码检查
* V2.1.0
    * 2.1.0 BFC 发布
* V2.0.2-rc
    * 添加dontAnimate接口

# Using
```java
    //建议调用方式
    ImageLoader.getInstance().load(this, url, null, null).into(imageView);

    ImageLoader.getInstance().load(this, url, R.drawable.placeholder, R.drawable.error, null).into(imageView);

    //自定义配置调用方式
    ImageLoadConfig conf = new ImageLoadConfig.Builder()
            .setPlaceHolderResId(R.drawable.placeholder)
            .setErrorResId(R.drawable.error)
            .setScaleType(ImageView.ScaleType.FIT_CENTER)
            .setTargetSize(new ImageLoadConfig.TargetSize(500, 400))
            .setPriority(ImageLoadConfig.LoadPriority.HIGH)
            .setCacheStrategy(ImageLoadConfig.CacheStrategy.ALL)
            .setTransformations(new RoundedCornersTransformation(this, 10, 0), new BlurTransformation(getApplicationContext(), 10))
            .setCustomConf(map)
            .build();

    //加载网络资源
    ImageLoader.getInstance().load(this, url, conf, new ImageLoadCallBack() {
        @Override
        public void onSuccess(Object loadSource, boolean isFromMemoryCache, boolean isFirstResource) {
        }

        @Override
        public void onError(Exception e, Object loadSource) {
        }
    }).into(imageView);

    ImageLoader.getInstance().load(this, url, R.drawable.placeholder, R.drawable.error, new ImageLoadCallBack() {
        @Override
        public void onSuccess(Object loadSource, boolean isFromMemoryCache, boolean isFirstResource) {
        }

        @Override
        public void onError(Exception e, Object loadSource) {
        }
    }).into(imageView);
    //加载本地资源
    ImageLoader.getInstance().load(this, resId, conf, null).into(imageView);
    //加载Uri对应资源
    ImageLoader.getInstance().load(this, uri, conf, null).into(imageView);
```
自定义配置中接受设置参数包括：**占位图：加载中、加载失败**、**ImageView缩放裁剪模式**、**目标图片大小**、**请求策略**、**缓存策略**、**图片效果**、**自定义属性**

以上接口方法中的**this**接受对象包括：**Activity**、**Fragment**、**FragmentActivity**、**Context**
#### 其他接口
```java
    //内存缓存清理
    ImageLoader.getInstance().clearMemoryCache(this);
    //磁盘缓存清理
    ImageLoader.getInstance().clearDiskCache(this);
    //暂停所有请求
    ImageLoader.getInstance().pauseLoad(this);
    //恢复所有请求
    ImageLoader.getInstance().resumeLoad(this);
    //取消图片请求
    ImageLoader.getInstance().clear(iv);
    //设置调试模式
    ImageLoader.DEBUG = true;
    //设置默认配置
    ImageLoader.initDefaultConf(new ImageLoadConfig.Builder().build());
```
#### 自定义缓存及图片配置
Manifest配置
```xml
    <meta-data
            android:name="com.example.imageloader.open.GlideCacheModule"
            android:value="GlideModule" />
```
具体配置
```java
    public class GlideCacheModule implements GlideModule {
        @Override
        public void applyOptions(Context context, GlideBuilder builder) {
            // 配置缓存空间
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 5 * 1024 * 1024))
                    .setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
             // 设置BitMap加载模式为ARGB_8888
             builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        }

        @Override
        public void registerComponents(Context context, Glide glide) {

        }
    }
```
具体实现可参考[Demo](http://172.28.2.93/bfc/BfcImageLoader/tree/develop/ImageLoader-sample)<br/>
Tips: 该配置基于Glide实现，因源码内无代码配置方式，故只能如此公开且改配置不适用于用户自定义图片缓存实现
# User Custom ImageLoader realize
#### 如需要使用其他图片加载框架实现图片加载功能或需要未提供的个性化功能，可以实现IImageLoadStrategy接口，并通过如下方法配置
```java
    ImageLoader.getInstance().setImageLoadStrategy(new PicassoLoadStrategy());
```
PicassoLoadStrategy实现可参考Demo中[PicassoLoadStrategy.java](http://172.28.2.93/bfc/BfcImageLoader/blob/develop/ImageLoader-sample/src/main/java/com/example/imageloader/strategy/PicassoLoadStrategy.java)
#### Tips
附 [FrescoLoadStrategy.java](http://172.28.2.93/bfc/BfcImageLoader/blob/develop/ImageLoader-sample/src/main/java/com/example/imageloader/strategy/FrescoLoadStrategy.java)

# ErrorCode
错误码设计：模块码+错误码

网络请求模块：0B
