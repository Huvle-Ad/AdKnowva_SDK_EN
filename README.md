# AdKnowva_SDK_android

## AdKnowva Install Guide         


AdKnowva has an integration method based on Gradle. Plus, we supply sample application, thus you may easily integrate our service through those examples. 
You can check full contents of guide documents by downloading the files from the **“Download All AdKnowva Sample Projects”** menu below. 
Currently, the latest version is **1.2.2**.



## Affiliate Application
We will help you know how to affiliate with AdKnowva please visit this URL. https://www.huvleview.com/doc/contact.php


### Integration Guide
- Please refer to Usages or the sample project below.
- [Download All AdKnowva Sample Projects](https://github.com/Huvle-Ad/AdKnowva_SDK_EN/archive/main.zip)    
-> AdKnowva / AdKnowva + HuvleSDK  


## Usages
### 1. Manifest
- Add networkSecurityConfig 
```
<uses-permission android:name="android.permission.INTERNET" />

<application
	.
	.
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
- Java code
```java
// TODO - Adknowva SDK Library
private BannerAdView bav;
// TODO - Adknowva SDK Library

@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate( savedInstanceState );
  setContentView( R.layout.activity_main );

  // TODO - Adknowva SDK Library  
  setHuvleAD(); // AdKnowva SDK init - Apply Activivty onCreate position.
  bav.startAd(); // Call Huvle’s advertisement 
  // TODO - Adknowva SDK Library
}

private void setHuvleAD() {
// For the "test" value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
  bav = findViewById(R.id.banner_view);
  bav.setPlacementID("test"); // 320*50 banner testID , 300*250 banner test ID "testbig"
  bav.setShouldServePSAs(false);
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);
  bav.setAdSize(320, 50); //bav.setAdSize(300, 250);
  // Resizes the container size to fit the banner ad
  bav.setResizeAdToFitContainer(true);
// bav.setExpandsToFitScreenWidth(true);
  AdListener adListener = new AdListener() {
      @Override
      public void onAdRequestFailed(AdView bav, ResultCode errorCode) {
          if (errorCode == null) {
              Log.v("HuvleBANNER", "Call to loadAd failed");
          } else {
              Log.v("HuvleBANNER", "Ad request failed: " + errorCode);
          }
      }

      @Override
      public void onAdLoaded(AdView ba) {Log.v("HuvleBANNER", "The Ad Loaded!");}
      @Override
      public void onAdLoaded(NativeAdResponse nativeAdResponse) {}
      @Override
      public void onAdExpanded(AdView bav) {}
      @Override
      public void onAdCollapsed(AdView bav) {}
      @Override
      public void onAdClicked(AdView bav) {}
      @Override
      public void onAdClicked(AdView adView, String clickUrl) {}
      @Override
      public void onLazyAdLoaded(AdView adView) {}
  };
  bav.setAdListener(adListener);
  bav.init(this);

}
```

- Kotlin code
```java
// TODO - Adknowva SDK Library
lateinit var bav: BannerAdView
// TODO - Adknowva SDK Library

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO - Adknowva SDK Library
    setHuvleAD() // AdKnowva SDK init - Apply to Activivty onCreate position.
    bav.startAd() // Call Huvle’s advertisement 
    // TODO - Adknowva SDK Library
}

// TODO - Adknowva SDK Library
private fun setHuvleAD() {
/*
  With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
  For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
*/
  bav = findViewById(R.id.banner_view)
  bav.setPlacementID("test") // 320*50 banner testID , 300*250 banner test ID "testbig"
  bav.setShouldServePSAs(false)
  bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER)
  bav.setAdSize(320, 50) //bav.setAdSize(300, 250);
  // Resizes the container size to fit the banner ad
  bav.setResizeAdToFitContainer(true)
// bav.setExpandsToFitScreenWidth(true)
  val adListener: AdListener = object : AdListener {
      override fun onAdRequestFailed(
          bav: com.byappsoft.huvleadlib.AdView,
          errorCode: ResultCode
      ) {
          if (errorCode == null) {
              Log.v("HuvleBANNER", "Call to loadAd failed")
          } else {
              Log.v("HuvleBANNER", "Ad request failed: $errorCode")
          }
      }
      override fun onAdLoaded(ba: com.byappsoft.huvleadlib.AdView) {
          Log.v("HuvleBANNER", "The Ad Loaded!")
      }
      override fun onAdLoaded(nativeAdResponse: NativeAdResponse) {}
      override fun onAdExpanded(bav: com.byappsoft.huvleadlib.AdView) {}
      override fun onAdCollapsed(bav: com.byappsoft.huvleadlib.AdView) {}
      override fun onAdClicked(bav: com.byappsoft.huvleadlib.AdView) {}
      override fun onAdClicked(adView: com.byappsoft.huvleadlib.AdView, clickUrl: String) {}
      override fun onLazyAdLoaded(adView: com.byappsoft.huvleadlib.AdView) {}
  }
  bav.setAdListener(adListener)
  bav.init(this)
}
```




## License
Huvle Corporation owns the copyright on AdKnowva SDK.
```
AdKnowva SDK Android
Copyright 2021-present Huvle Corp.

Unauthorized use, modification and redistribution of this software are strongly prohibited.
```

