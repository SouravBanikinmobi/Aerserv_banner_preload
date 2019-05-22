package myproductname.mycompany.com.aerserv_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aerserv.sdk.AerServBanner;
import com.aerserv.sdk.AerServConfig;
import com.aerserv.sdk.AerServEvent;
import com.aerserv.sdk.AerServEventListener;
import com.aerserv.sdk.AerServSdk;
import com.aerserv.sdk.AerServTransactionInformation;
import com.aerserv.sdk.AerServVirtualCurrency;

import java.util.List;

import static com.aerserv.sdk.utils.WebViewJSRunner.LOG_TAG;

public class MainActivity extends AppCompatActivity {
    private static final String APP_ID = "1000473";
    private static final String PLACEMENT_ID = "1024876";
    private AerServBanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AerServSdk.init(this, APP_ID);
        banner = (AerServBanner) findViewById(R.id.banner);


    }

    public void loadBannerAd(View V) {

        final AerServEventListener bannerListener = new AerServEventListener(){
            @Override
            public void onAerServEvent(final AerServEvent event, final List<Object> args){
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String msg = null;

                        switch (event) {
                            case PRELOAD_READY:
                               // msg = "PRELOAD_READY event fired with args: " + args.toString();
                                Log.d("ad", "Preload_ready");
                                findViewById(R.id.showBanner).setVisibility(View.VISIBLE);
                                findViewById(R.id.loadBanner).setVisibility(View.INVISIBLE);

                                break;

                            case AD_FAILED:
                                Log.d("ad", "ad_failed");

                                break;

                            case AD_IMPRESSION:
                                Log.d("ad", "ad_impression");
                                findViewById(R.id.showBanner).setVisibility(View.INVISIBLE);
                                findViewById(R.id.loadBanner).setVisibility(View.VISIBLE);

                                break;

                        }

                    }
                });
            }
        };

        AerServConfig config = new AerServConfig(this, PLACEMENT_ID).setPreload(true).setEventListener(bannerListener);

        banner.configure(config);

    }

    public void showBannerAd(View V){

        banner.show();



    }
}
