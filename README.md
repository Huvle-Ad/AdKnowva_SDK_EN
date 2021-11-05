# HuvleAdKnowva_SDK_android

## Adknowva Install Guide &nasp;&nasp;&nasp; [kor ver.](guide/guide(kor).md)

AdKnowva has an integration method based on Gradle. Plus, we supply sample application, thus you may easily integrate our service through those examples. 
You can check full contents of guide documents by downloading the files from the **“Download All AdKnowva Sample Projects”** menu below. 
Currently, the latest version is **1.2.2**.



## Affiliate Application
We will help you know how to affiliate with AdKnowva please visit this URL. https://www.huvleview.com/doc/contact.php


### Integration Guide
- Please refer to Usages or the sample project below.
- [Download All AdKnowva Sample Projects](https://github.com/wootaeng/HuvleAdKnowva_SDK_android/archive/main.zip)    
-> adknowva / adknowva + Google ADMOB + HuvleSDK  


## Usages
### 1. Manifest
- Add networkSecurityConfig (If you target Android 10(API level 29) or higher, please add requestLegacyExternalStorage)
```
<uses-permission android:name="android.permission.INTERNET" />

<application
	.
	.
	android:requestLegacyExternalStorage="true"
	android:networkSecurityConfig="@xml/network"
	tools:replace="android:networkSecurityConfig"
	.
	.
	
```

### 2. Add SDK 
Please add sub library including SDK into Gradle to use AdKnowva SDK.
- build.gradle(Project)
```
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            name "Huvle"
            url "https://sdk.huvle.com/repository/internal"
        }
    }
}
```

- build.gradle(app)
```
android {
    ...
    defaultConfig {
        .
	.
        multiDexEnabled true
	.
	.
    }
}

dependencies {
	.
	.
	/**
	* adknowva sdk , play-service-ads 
	*/
	implementation 'com.google.android.gms:play-services-ads:20.4.0'
	implementation 'com.byappsoft.huvleadlib:HuvleAdLib:1.2.2' // Please implement after checking the latest version.
	.
	.
}
```

### 3. Apply to your app
- Activity in which the advertisement will be applied.
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate( savedInstanceState );
  setContentView( R.layout.activity_main );

  setHuvleAD(); // // Call Huvle’s advertisement
}

private void setHuvleAD() {
  final BannerAdView staticBav = findViewById(R.id.banner_view);
  // For the "test" value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
  initBannerView(staticBav, "test",320,50);
}
private void initBannerView(final BannerAdView bav, String id, int w , int h) {
  bav.setPlacementID(id);
  bav.setAdSize(w, h);
  bav.setShouldServePSAs(false);
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER); // Open the browser as the default browser when clicking on an advertisement
  bav.setResizeAdToFitContainer(true);
  AdListener adListener = new AdListener() {
    @Override public void onAdRequestFailed(AdView bav, ResultCode errorCode) {/*Handle when there is no advertiment*/}
    @Override public void onAdLoaded(AdView bav) {Log.v("Huvle_Banner", "The Ad Loaded!");}
    @Override public void onAdLoaded(NativeAdResponse nativeAdResponse) {Log.v("Huvle_Banner", "Ad onAdLoaded NativeAdResponse");}
    @Override public void onAdExpanded(AdView bav) {Log.v("Huvle_Banner", "Ad expanded");}
    @Override public void onAdCollapsed(AdView bav) {Log.v("Huvle_Banner", "Ad collapsed");}
    @Override public void onAdClicked(AdView bav) {Log.v("Huvle_Banner", "Ad clicked; opening browser");}
    @Override public void onAdClicked(AdView adView, String clickUrl) {Log.v("Huvle_Banner", "onAdClicked with click URL");}
    @Override public void onLazyAdLoaded(AdView adView) {}
  };
  bav.setAdListener(adListener);
  new Handler().postDelayed(new Runnable() {
    @Override public void run() {
      bav.loadAd();
    }
  }, 0);
}
```




## License
Huvle Corporation owns the copyright on AdKnowva SDK.
```
Huvle AdKnowva SDK Android
Copyright 2021-present Huvle Corp.

Unauthorized use, modification and redistribution of this software are strongly prohibited.
```

