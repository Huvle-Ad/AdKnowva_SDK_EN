package com.example.adknowvasample_flutter

import android.util.Log
import android.widget.RelativeLayout
import com.byappsoft.huvleadlib.ANClickThroughAction
import com.byappsoft.huvleadlib.AdListener
import com.byappsoft.huvleadlib.BannerAdView
import com.byappsoft.huvleadlib.InterstitialAdView
import com.byappsoft.huvleadlib.NativeAdResponse
import com.byappsoft.huvleadlib.ResultCode
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {

    private val CHANNEL = "com.example.adknowva/ads"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL
        ).setMethodCallHandler { call, result ->
            when(call.method) {
                "loadAd" -> bannerAd()
                "interstitialAd" -> interstitialAd()
                else ->result.notImplemented()
            }
//            if (call.method == "loadAd") {
//                bannerAd()
//            } else {
//                result.notImplemented()
//            }
        }
    }

    private fun bannerAd() {
        val bav = BannerAdView(context)
        val layout = RelativeLayout(context)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.CENTER_VERTICAL)

        layoutParams.setMargins(50,0,0,100)

        bav.layoutParams = layoutParams
        layout.addView(bav)
        this.addContentView(layout,layoutParams)

        bav.placementID = "test" // 320*50 banner testID , 300*250 banner test ID "testbig"
        bav.shouldServePSAs = false
        bav.clickThroughAction = ANClickThroughAction.OPEN_DEVICE_BROWSER
        bav.setAdSize(320, 50) //bav.setAdSize(300, 250);
//                bav.resizeAdToFitContainer = true
//                bav.expandsToFitScreenWidth = true
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
        bav.adListener = adListener
        bav.loadAd()
    }


    private fun interstitialAd() {
        val iadv = InterstitialAdView(context)
        //bav.setBackgroundColor(0xffffffff); // background color
        iadv.closeButtonDelay = 3 * 1000     // Activate close button after 3 seconds
//        iadv.closeButtonDelay = 0          // Activate close button immediately
//        iadv.closeButtonDelay = -1         // Disable close Button

/*
    As for the “testfull” value below, please go to http://ssp.huvle.com/ to sign up > create media > select the 'fullscreen' checkbox > test your app after entering the zoneid corresponding to the 'fullscreen' option.
    Next, contact Huvle before releasing your app for authentication.
    You must not change the banner size.
 */
        iadv.placementID = "testfull" // zoneId
        iadv.shouldServePSAs = false
        iadv.clickThroughAction = ANClickThroughAction.OPEN_DEVICE_BROWSER
        val adListener: AdListener = object : AdListener {
            override fun onAdRequestFailed(
                bav: com.byappsoft.huvleadlib.AdView,
                errorCode: ResultCode
            ) {
                if (errorCode == null) {
                    Log.v("HuvleInterstitialAd", "Call to loadAd failed")
                } else {
                    Log.v("HuvleInterstitialAd", "Ad request failed: $errorCode")
                }
            }

            override fun onAdLoaded(ba: com.byappsoft.huvleadlib.AdView) {
                Log.v("HuvleInterstitialAd", "The Ad Loaded!")
                iadv.show()
            }

            override fun onAdLoaded(nativeAdResponse: NativeAdResponse) {
                Log.v("HuvleInterstitialAd", "Ad onAdLoaded NativeAdResponse")
            }

            override fun onAdExpanded(bav: com.byappsoft.huvleadlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad expanded")
            }

            override fun onAdCollapsed(bav: com.byappsoft.huvleadlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad collapsed")
            }

            override fun onAdClicked(bav: com.byappsoft.huvleadlib.AdView) {
                Log.v("HuvleInterstitialAd", "Ad clicked; opening browser")
            }

            override fun onAdClicked(adView: com.byappsoft.huvleadlib.AdView, clickUrl: String) {
                Log.v("HuvleInterstitialAd", "onAdClicked with click URL")
            }

            override fun onLazyAdLoaded(adView: com.byappsoft.huvleadlib.AdView) {
                Log.v("HuvleInterstitialAd", "onLazyAdLoaded")
            }
        }
        iadv.adListener = adListener
        iadv.loadAd()
    }

}
