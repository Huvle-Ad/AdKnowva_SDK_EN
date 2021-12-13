package com.example.adknowvasample_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import com.byappsoft.huvleadlib.*
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    // TODO - Adknowva SDK Library
    lateinit var bav: BannerAdView
    // TODO - Adknowva SDK Library

    private var layout : RelativeLayout? = null
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO - Adknowva SDK Library
        setHuvleAD() // AdKnowva SDK init - Apply to Activivty onCreate position.
        bav.startAd() // Use it when you call AdKnowva alone. Comment out when using AdKnowva after impression of Google Ads.
        // If you use AdKnowva after impression of Google Ads.
//        setGoogleAD()
        // TODO - Adknowva SDK Library
    }

/*
   private fun setGoogleAD(){
        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.gadView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        mAdView.adListener = object: com.google.android.gms.ads.AdListener() {
            override fun onAdLoaded() {
                }
                Log.v("GoogleAD", "The Ad Loaded!")
            }
            override fun onAdFailedToLoad(adError : LoadAdError) {
                // TODO - Adknowva SDK Library
                bav.startAd()
                }
                // TODO - Adknowva SDK Library
                Log.v("GoogleAD", "The Ad failed!")
            }
            override fun onAdOpened() {}
            override fun onAdClicked() {}
            override fun onAdClosed() {}
        }
    }
*/

    // TODO - Adknowva SDK Library
    private fun setHuvleAD() {
        /*
            With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
            For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
        */

//        // When if apply Dynamic Implementation BannerAdView Start
//        bav = BannerAdView(this)
//        layout = findViewById<View>(R.id.adview_container) as RelativeLayout
//        val layoutParams = RelativeLayout.LayoutParams(
//            RelativeLayout.LayoutParams.WRAP_CONTENT,
//            RelativeLayout.LayoutParams.WRAP_CONTENT
//        )
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
//        bav!!.layoutParams = layoutParams
//        layout!!.addView(bav)

        // When if apply Dynamic Implementation BannerAdView Start
        bav = findViewById(R.id.banner_view)
        bav.setPlacementID("test") // 320*50 banner testID , 300*250 banner test ID "testbig"
        bav.setShouldServePSAs(false)
        bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER)
        bav.setAdSize(320, 50) //bav.setAdSize(300, 250);
        // Resizes the container size to fit the banner ad
        bav.setResizeAdToFitContainer(true)
//        bav.setExpandsToFitScreenWidth(true)
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
    // TODO - Adknowva SDK Library

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO - Adknowva SDK Library
        bav.destroy()
        // TODO - Adknowva SDK Library
    }

}