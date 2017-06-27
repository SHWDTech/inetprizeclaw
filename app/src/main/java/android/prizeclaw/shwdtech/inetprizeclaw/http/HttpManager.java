package android.prizeclaw.shwdtech.inetprizeclaw.http;

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
    public static String city = "上海";

    static final String ACCEPT_KEY = "Accept";
    static final String ACCEPT_VALUE = "application/json";
    static final String CONTENT_TYPE_KEY = "Content-Type";
    static final String CONTENT_TYPE_VAULE = "application/x-www-form-urlencoded";
    static final String AUTHORIZATION = "Authorization";

    static final String GRANT_TYPE = "grant_type";
    static final String CLIENT_ID_KEY = "client_id";
    static final String CLIENT_ID_VALUE = "nativeAndroid";
    static final String SECRET_KEY = "client_secret";
    static final String SECRET_VALUE = "7E0C829B32A6";

    /**
     * 根据密码获取token
     */
    public static void getTokenByPassword(String username, String password, @NonNull final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.TOKEN);
        requestParams.addBodyParameter(GRANT_TYPE, "password");
        requestParams.addBodyParameter("username", username);
        requestParams.addBodyParameter("password", password);
        requestParams.addBodyParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        requestParams.addBodyParameter(SECRET_KEY, SECRET_VALUE);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getTokenByPassword  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getTokenByPassword  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 根据refreshToken 获取AccessToken
     * @param refreshToken
     * @param xHttpResponse
     */
    public static void getTokenByRefreshToken(String refreshToken, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.TOKEN);
        requestParams.addBodyParameter(GRANT_TYPE, "refresh_token");
        requestParams.addBodyParameter("refresh_token", refreshToken);
        requestParams.addBodyParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        requestParams.addBodyParameter(SECRET_KEY, SECRET_VALUE);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(SECRET_KEY, SECRET_VALUE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getTokenByRefreshToken  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getTokenByRefreshToken  ex is " + ex.toString());
                // TODO 处理被顶的情况.
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 根据ProjectType 获得所有监测设备（Device）的位置（Location）信息
     * @param projectType 可以为空
     */
    public static void getDeviceLocationByProjectType(String projectType, @NonNull final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.MAP);
        requestParams.addBodyParameter("projectType", projectType);
        requestParams.addBodyParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        requestParams.addBodyParameter(SECRET_KEY, SECRET_VALUE);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getDeviceLocationByProjectType  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDeviceLocationByProjectType  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * DistrictId 获得所有监测设备（Device）的位置（Location）信息
     * @param projectType 可以为空
     */
    public static void getDeviceLocationByDistrictId(String projectType, String districtId, @NonNull final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.MAP);
        requestParams.addBodyParameter("projectType", projectType);
        requestParams.addBodyParameter("district", districtId);
        requestParams.addBodyParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        requestParams.addBodyParameter(SECRET_KEY, SECRET_VALUE);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getDeviceLocationByDistrictId  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDeviceLocationByDistrictId  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取设备当前的数据
     * @param device
     * @param xHttpResponse
     */
    public static void getDeviceCurrentData(String device, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.DEVICE_CURRENTDATA);
        requestParams.addBodyParameter("device", device);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getDeviceCurrentData  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDeviceCurrentData  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取设备的历史数据
     * @param device
     * @param xHttpResponse
     */
    public static void getDeviceHistoryData(String device, String dataType, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.DEVICE_HISTORYDATA);
        requestParams.addBodyParameter("device", device);
        requestParams.addBodyParameter("dataType", dataType);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getDeviceHistoryData  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDeviceHistoryData  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取区县统计信息
     */
    public static void getDistrictAvg(String projectType, String dataType, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.DISTRICTAVG);
        requestParams.addBodyParameter("projectType", projectType);
        requestParams.addBodyParameter("dataType", dataType);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                xHttpResponse.onResponse(result);
                Log.d(TAG, "getDistrictAvg  result is " + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDistrictAvg  ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取指定区县下所有设备的统计信息
     * @param projectType
     * @param districtId
     */
    public static void getDistrictDetail(String projectType, String districtId, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.DISTRICTDETAIL);
        requestParams.addBodyParameter("projectType", projectType);
        requestParams.addBodyParameter("district", districtId);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getDistrictDetail  result is " + result);
                xHttpResponse.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getDistrictDetail ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static void getCascadeElement(String CascadeElementLevel, String CascadeElementId, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.CASCADEELEMENT);
        requestParams.addBodyParameter("CascadeElementLevel", CascadeElementLevel);
        requestParams.addBodyParameter("CascadeElementId", CascadeElementId);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getCascadeElement  result is " + result);
                xHttpResponse.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getCascadeElement ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static void getSearch(String search, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.SEARCH);
        requestParams.addBodyParameter("searchName", search);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getSearch  result is " + result);
                xHttpResponse.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getSearch ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static void getStatisticsDetail(String dataType, String elementType, String elementId, final XHttpResponse xHttpResponse) {
        RequestParams requestParams = new RequestParams(HttpConStants.STATISTICSDETAIL);
        requestParams.addBodyParameter("dataType", dataType);
        requestParams.addBodyParameter("elementType", elementType);
        requestParams.addBodyParameter("elementId", elementId);
        requestParams.addHeader(ACCEPT_KEY, ACCEPT_VALUE);
        requestParams.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAULE);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "getSearch  result is " + result);
                xHttpResponse.onResponse(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                xHttpResponse.onError(ex);
                Log.d(TAG, "getSearch ex is " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /**
     * 获取更新信息
     */
    public static void getUpdateData(@NonNull final XHttpResponse response) {
        Log.d(TAG, "start getUpdateData");
        RequestParams requestParams = new RequestParams(HttpConStants.UPDATE_URL);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
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
}
