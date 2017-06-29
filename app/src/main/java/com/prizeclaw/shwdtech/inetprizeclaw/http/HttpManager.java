package com.prizeclaw.shwdtech.inetprizeclaw.http;

import android.support.annotation.NonNull;
import android.util.Log;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by bb on 16/9/12.
 */
public class HttpManager {
    static final String TAG = "HttpManager";
    static final int CONNECT_TIMEOUT = 60000;

    public static void getAccessToken(@NonNull final XHttpResponse response){
        Log.d(TAG, "start getAccessToken");
        RequestParams requestParams = new RequestParams(HttpConStants.OPENYSHost);
        requestParams.addBodyParameter("appKey", "9f88209c239d4bf28156d3f880bb8321");
        requestParams.addBodyParameter("appSecret", "f013a79dd3c9966123fd408be34c557e");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getUpdateData onSuccess -->" + result);
                response.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "getUpdateData onError  -->" + ex.toString());
                response.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void postControlCommand(@NonNull final XHttpResponse response, String clientCode, Integer command){
        Log.d(TAG, "start getAccessToken");
        RequestParams requestParams = new RequestParams(HttpConStants.ControlCommand);
        requestParams.addBodyParameter("clientCode", clientCode);
        requestParams.addBodyParameter("command", command.toString());
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getUpdateData onSuccess -->" + result);
                response.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "getUpdateData onError  -->" + ex.toString());
                response.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
