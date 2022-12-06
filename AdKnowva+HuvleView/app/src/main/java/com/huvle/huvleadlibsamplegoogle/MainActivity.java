package com.huvle.huvleadlibsamplegoogle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.byappsoft.huvleadlib.ANClickThroughAction;
import com.byappsoft.huvleadlib.AdListener;
import com.byappsoft.huvleadlib.AdView;
import com.byappsoft.huvleadlib.BackAdListener;
import com.byappsoft.huvleadlib.BannerAdView;
import com.byappsoft.huvleadlib.InterstitialAdView;
import com.byappsoft.huvleadlib.NativeAdResponse;
import com.byappsoft.huvleadlib.ResultCode;
import com.byappsoft.huvleadlib.SDKSettings;
import com.byappsoft.huvleadlib.utils.Clog;
import com.byappsoft.huvleuid.HuidManager;
import com.byappsoft.sap.launcher.Sap_act_main_launcher;
import com.byappsoft.sap.utils.Sap_Func;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    // TODO - Adknowva SDK Library
    private BannerAdView bav;
    // TODO - Adknowva SDK Library

    private RelativeLayout layout; // Dynamic view
    private com.google.android.gms.ads.AdView mAdView; //Google Admob

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO - Adknowva SDK Library
        setHuvleAD(); // AdKnowva SDK init - Apply to Activivty onCreate position.
        bav.startAd(); // Use it when you call AdKnowva alone. Comment out when using AdKnowva after impression of Google Ads.
        // If you use AdKnowva after impression of Google Ads.
//         setGoogleAD();
        // TODO - Adknowva SDK Library

        findViewById(R.id.load_iad_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // InterstitialAd sample
                launchInterstitialAd();
            }
        });

    }
/*
    private void setGoogleAD(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mAdView = findViewById(R.id.gadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override public void onAdLoaded() {
                // TODO - Adknowva SDK Library
                Log.v("GoogleAD", "The Ad Loaded!");
            }
            @Override public void onAdFailedToLoad(LoadAdError adError) {
                // TODO - Adknowva SDK Library
                bav.startAd();
                // TODO - Adknowva SDK Library
                Log.v("GoogleAD", "The Ad failed!");
            }
            @Override public void onAdOpened() {}
            @Override public void onAdClicked() {}
            @Override public void onAdClosed() {}
        });
    }
*/


    // TODO - Adknowva SDK Library
    private void setHuvleAD(){

 /*
    With checking the implementation guide below, please apply Implementation either only Dynamic or Static.
    For the “test” value below, please go to http://ssp.huvle.com/ to sign up > create media > Test your app after typing zoneid. Next, contact Huvle before releasing your app for authentication. You must not change the banner size.
*/

        // When if apply Dynamic Implementation BannerAdView Start
//        bav = new BannerAdView(this);
//        layout = (RelativeLayout) findViewById(R.id.adview_container);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//        bav.setLayoutParams(layoutParams);
//        layout.addView(bav);

        // When if apply Static Implementation BannerAdView Start
        bav = findViewById(R.id.banner_view);

        bav.setPlacementID("test"); // 320*50 banner testID , 300*250 banner test ID "testbig"
        bav.setShouldServePSAs(false);
        bav.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);
        bav.setAdSize(320, 50); //bav.setAdSize(300, 250);
        // Resizes the container size to fit the banner ad
        bav.setResizeAdToFitContainer(true);
//        bav.setExpandsToFitScreenWidth(true);
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

    //InterstitialAd
    private void launchInterstitialAd() {
        final InterstitialAdView iadv = new InterstitialAdView(this);
        //bav.setBackgroundColor(0xffffffff); // background color
        iadv.setCloseButtonDelay(10 * 1000);  // Activate close button after 10 seconds
        //badv.setCloseButtonDelay(0);        // Activate close button immediately
        //iadv.setCloseButtonDelay(-1);       // Disable close Button
/*
        As for the “testfull” value below, please go to http://ssp.huvle.com/ to sign up > create media > select the 'fullscreen' checkbox > test your app after entering the zoneid corresponding to the 'fullscreen' option.
        Next, contact Huvle before releasing your app for authentication.
        You must not change the banner size.
 */
        iadv.setPlacementID("testfull"); // zoneId
        iadv.setShouldServePSAs(false);
        iadv.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);


        AdListener adListener = new AdListener() {
            @Override
            public void onAdRequestFailed(AdView bav, ResultCode errorCode) {
                if (errorCode == null) {
                    Log.v("HuvleInterstitialAd", "Call to loadAd failed");
                } else {
                    Log.v("HuvleInterstitialAd", "Ad request failed: " + errorCode);
                }
            }

            @Override
            public void onAdLoaded(AdView ba) {
                Log.v("HuvleInterstitialAd", "The Ad Loaded!");
                iadv.show();
            }

            @Override
            public void onAdLoaded(NativeAdResponse nativeAdResponse) {
                Log.v("HuvleInterstitialAd", "Ad onAdLoaded NativeAdResponse");
            }

            @Override
            public void onAdExpanded(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad expanded");
            }

            @Override
            public void onAdCollapsed(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad collapsed");
            }

            @Override
            public void onAdClicked(AdView bav) {
                Log.v("HuvleInterstitialAd", "Ad clicked; opening browser");
            }

            @Override
            public void onAdClicked(AdView adView, String clickUrl) {
                Log.v("HuvleInterstitialAd", "onAdClicked with click URL");
            }

            @Override
            public void onLazyAdLoaded(AdView adView) {
                Log.v("HuvleInterstitialAd", "onLazyAdLoaded");
            }
        };
        iadv.setAdListener(adListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iadv.loadAd();
            }
        }, 0);
    }

    // backPressed InterstitialAd load
    @Override
    public void onBackPressed() {
        launchBackButtonAd();
    }

    private void launchBackButtonAd() {
        final InterstitialAdView badv = new InterstitialAdView(this);
//        bav.setBackgroundColor(0xffffffff);

//        badv.setCloseButtonDelay(10 * 1000);   // Activate close button after 10 seconds
//        badv.setCloseButtonDelay(0);           // Activate close button immediately
        badv.setCloseButtonDelay(-1);            // Disable close Button


        badv.setPlacementID("testfull"); // zoneId
        badv.setShouldServePSAs(false);
        badv.setClickThroughAction(ANClickThroughAction.OPEN_DEVICE_BROWSER);

        AdListener adListener = new BackAdListener() {
            @Override
            public void onBackPressed() {
                //return to the app
                Log.v("backIAD", "BackAdListener.onBackPressed()!");
            }

            @Override
            public void onAdLoaded(AdView adView) {
                Log.v("backIAD", "The Ad Loaded!");
                badv.show();

            }

            @Override
            public void onAdLoaded(NativeAdResponse nativeAdResponse) {
                Log.v("backIAD", "Ad onAdLoaded NativeAdResponse");
            }

            @Override
            public void onAdRequestFailed(AdView adView, ResultCode errorCode) {
                if (errorCode == null) {
                    Log.v("backIAD", "Call to loadAd failed");
                } else {
                    Log.v("backIAD", "Ad request failed: " + errorCode);
                }
                // end the app if no Ad
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 400);
            }

            @Override
            public void onAdExpanded(AdView adView) {
                Log.v("backIAD", "Ad expanded");
            }

            @Override
            public void onAdCollapsed(AdView adView) {
                // Shut down app when you click the Close button
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 400);
            }

            @Override
            public void onAdClicked(AdView adView) {
                Log.v("backIAD", "Ad clicked; opening browser");
            }

            @Override
            public void onAdClicked(AdView adView, String s) {
                Log.v("backIAD", "onAdClicked with click URL");
            }

            @Override
            public void onLazyAdLoaded(AdView adView) {
                Clog.v("backIAD", "onLazyAdLoaded");
            }
        };

        badv.setAdListener(adListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                badv.loadAd();
            }
        }, 0);
    }



    // TODO - Adknowva SDK Library

    @Override
    protected void onResume() {
        super.onResume();
        // TODO- Huid
        HuidManager.onResume(this);
        SDKSettings.onResume(this);
        Sap_act_main_launcher.onResume(this);
        // TODO- Huid

        // TODO - Huvle SDK Library
        Sap_Func.setNotiBarLockScreen(this, false);
        Sap_act_main_launcher.initsapStart(this, "bynetwork", true, true);
        // TODO - Huvle SDK Library
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO- Huid
        HuidManager.onStop(this);
        SDKSettings.onStop(this);
        Sap_act_main_launcher.onStop(this);
        // TODO- Huid
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO - Adknowva SDK Library
        bav.destroy();
        // TODO - Adknowva SDK Library
    }
}


