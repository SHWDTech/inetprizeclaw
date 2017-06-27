package android.prizeclaw.shwdtech.inetprizeclaw.application;

import android.app.Application;

import com.videogo.openapi.EZOpenSDK;

import org.xutils.x;

/**
 * Created by autyan on 6/27/2017.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
        EZOpenSDK.showSDKLog(true);
        EZOpenSDK.initLib(this, "9f88209c239d4bf28156d3f880bb8321", "");
    }
}
