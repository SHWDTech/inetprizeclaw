package android.prizeclaw.shwdtech.inetprizeclaw.http;

/**
 * Created by bb on 16/9/12.
 */
public interface XHttpResponse {
    void onResponse(String response);
    void onError(Throwable e);
}
