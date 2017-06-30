package com.prizeclaw.shwdtech.inetprizeclaw.application;

import android.app.Application;
import android.util.Log;

import com.prizeclaw.shwdtech.inetprizeclaw.bean.AccessTokenBean;
import com.prizeclaw.shwdtech.inetprizeclaw.http.HttpManager;
import com.prizeclaw.shwdtech.inetprizeclaw.http.JSONUtils;
import com.prizeclaw.shwdtech.inetprizeclaw.http.XHttpResponse;
import com.videogo.openapi.EZOpenSDK;

import org.xutils.x;

/**
 * Created by autyan on 6/27/2017.
 */

public class MainApplication extends Application {
    private static EZOpenSDK mEZOpenSDK;

    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
        EZOpenSDK.showSDKLog(true);
        EZOpenSDK.initLib(this, "9f88209c239d4bf28156d3f880bb8321", "");
        mEZOpenSDK = EZOpenSDK.getInstance();
    }

    public static EZOpenSDK getApplicationEZOpenSDK(){
        return mEZOpenSDK;
    }
}
