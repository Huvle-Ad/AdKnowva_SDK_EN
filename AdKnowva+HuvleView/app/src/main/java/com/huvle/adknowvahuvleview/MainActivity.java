package com.huvle.adknowvahuvleview;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
import com.byappsoft.huvleadlib.utils.Clog;
import com.byappsoft.sap.launcher.Sap_act_main_launcher;
import com.byappsoft.sap.utils.Sap_Func;

public class MainActivity extends AppCompatActivity {

    // TODO - Adknowva SDK Library
    private BannerAdView bav;
    // TODO - Adknowva SDK Library

    private RelativeLayout layout; // Dynamic view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(!checkPermission()){
                requestSapPermissions();
            }
        }

        // TODO - Adknowva SDK Library
        setHuvleAD(); // AdKnowva SDK init - Apply to Activivty onCreate position.
        // TODO - Adknowva SDK Library

        findViewById(R.id.load_iad_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // InterstitialAd sample
                launchInterstitialAd();
            }
        });

    }

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
        bav.loadAd();
    }

    //InterstitialAd
    private void launchInterstitialAd() {
        final InterstitialAdView iadv = new InterstitialAdView(this);
        //bav.setBackgroundColor(0xffffffff); // background color
        iadv.setCloseButtonDelay(3 * 1000);  // Activate close button after 3 seconds
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
        iadv.loadAd();

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
        badv.setCloseButtonDelay(0);           // Activate close button immediately
//        badv.setCloseButtonDelay(-1);            // Disable close Button


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
                }, 0);
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
                }, 0);
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
        badv.loadAd();

    }
    // TODO - Adknowva SDK Library

    @Override
    protected void onPause() {
        // TODO - Adknowva SDK Library
        if (bav != null) {
            bav.activityOnPause();
        }
        // TODO - Adknowva SDK Library
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO - Adknowva SDK Library
        if (bav != null) {
            bav.activityOnResume();
        }
        // TODO - Adknowva SDK Library
        super.onResume();

        // TODO - Huvle SDK Library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkPermission()) {
                // 동의창 후 미디어 권한
                Sap_Func.setNotiBarLockScreen(this, false);
                Sap_act_main_launcher.initsapStart(this, "bynetwork", true, true, new Sap_act_main_launcher.OnLauncher() {

                    @Override
                    public void onDialogOkClicked() {
                        // 동의창 확인후 동작
                        checkDrawOverlayPermission();

                    }

                    @Override
                    public void onDialogCancelClicked() {
                        // 동의창 취소후 다음 앱 접속시 동의창을 계속 띄울때는 아래코드 주석제거 할것.
                        // Sap_Func.setNotibarPopState(getBaseContext(), false);

                    }

                    @Override
                    public void onInitSapStartapp() {
                        // 노티바를 띄우지 않는경우(NOTIBA : false) 그 후 동작.

                    }

                    @Override
                    public void onUnknown() {

                    }
                });
            }
        } else {
            // 동의창 후 미디어 권한
            Sap_Func.setNotiBarLockScreen(this, false);
            Sap_act_main_launcher.initsapStart(this, "bynetwork", true, true, new Sap_act_main_launcher.OnLauncher() {

                @Override
                public void onDialogOkClicked() {
                    // 동의창 확인후 동작.
                    checkDrawOverlayPermission();

                }

                @Override
                public void onDialogCancelClicked() {
                    // 동의창 취소후 다음 앱 접속시 동의창을 계속 띄울때는 아래코드 주석제거 할것.
                    // Sap_Func.setNotibarPopState(getBaseContext(), false);

                }

                @Override
                public void onInitSapStartapp() {
                    // 노티바를 띄우지 않는경우(NOTIBA : false) 그 후 동작.

                }

                @Override
                public void onUnknown() { }
            });
        }
        // TODO - Huvle SDK Library

    }


    @Override
    protected void onDestroy() {
        // TODO - Adknowva SDK Library
        if (bav != null) {
            bav.destroy();
        }
        // TODO - Adknowva SDK Library
        super.onDestroy();
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestSapPermissions() {
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 0);
        }catch (Exception ignored){
        }
    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            new AlertDialog.Builder(this)
                    .setTitle("다른앱위에 그리기")
                    .setMessage("다른 앱 위에 그리기 권한을 허용해주세요.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            Uri uri = Uri.parse("package:" + getPackageName());
                            intent.setData(uri);
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create()
                    .show();
            return false;
        } else {
            return true;
        }
    }

}


