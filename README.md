# PermissionJ
PermissionJ


**引入**

老版本 build.gradle，务必添加阿里仓库,否则aspectj无法下载
```
buildscript {
 repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/google' }
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }

dependencies {
        classpath "com.android.tools.build:gradle:7.0.4"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21"
        classpath 'org.aspectj:aspectjtools:1.9.1'
        classpath 'org.aspectj:aspectjweaver:1.9.1'
        classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
   }
    
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/google' }
        maven { url 'https://jitpack.io' }
        google()
        mavenCentral()

    }

    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor 0, 'seconds'
        }
    }
}

```

新版本 对于没有allprojects的
在settings.gradle修改代码为
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://maven.aliyun.com/repository/public' }
        maven { url 'https://maven.aliyun.com/repository/google' }
        maven { url 'https://jitpack.io' }
    }
}
```

app build.gradle 根添加
```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'android-aspectjx'
}
aspectjx{
    enabled true
    exclude 'androidx','kotlin','com.google','com.squareup','com.alipay','org.apache'
}
dependencies {
	implementation 'com.github.SunStarJ:PermissionJ:0.0.1_beta'
}

```

核心代码
```
@PermissionChecker([Manifest.permission.WRITE_EXTERNAL_STORAGE])//权限数组
    fun openCamera() {
        //同意请求
        //业务代码
    }

    @PermissionDine
    fun permissionDine() {
        //请求失败
        Log.e("getPermission", "getPermission: error")
    }


```
调用
```
 //打开相机操作
 openCamera()
```