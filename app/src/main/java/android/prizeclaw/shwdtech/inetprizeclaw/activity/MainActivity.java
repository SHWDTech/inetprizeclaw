package android.prizeclaw.shwdtech.inetprizeclaw.activity;

import android.os.Build;
import android.os.Bundle;
import android.prizeclaw.shwdtech.inetprizeclaw.R;
import android.prizeclaw.shwdtech.inetprizeclaw.bean.AccessTokenBean;
import android.prizeclaw.shwdtech.inetprizeclaw.http.HttpManager;
import android.prizeclaw.shwdtech.inetprizeclaw.http.JSONUtils;
import android.prizeclaw.shwdtech.inetprizeclaw.http.XHttpResponse;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private EZOpenSDK mEZOpenSDK;

    private EZPlayer ezPlayer;

    private SurfaceView mSurfaceLeft;

    private SurfaceView mSurfaceRight;

    private List<EZDeviceInfo> mEZDeviceInfos;

    private AccessTokenBean token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        setContentView(R.layout.activity_main);

        mEZOpenSDK = EZOpenSDK.getInstance();
        HttpManager.getAccessToken(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
                token = JSONUtils.parseAccesstokenBean(response);
                mEZOpenSDK.setAccessToken(token.getData().getAccessToken());
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            final EZDeviceInfo  info = mEZOpenSDK.getDeviceInfo("577070480");
                            runOnUiThread(new Runnable(){
                                @Override
                                public void run(){
                                    int camNum = info.getCameraNum();
                                    String serial = info.getDeviceSerial();
                                    ezPlayer = mEZOpenSDK.createPlayer(serial, camNum);
                                    mSurfaceRight = (SurfaceView)findViewById(R.id.surfaceViewRight);
                                    ezPlayer.setSurfaceHold(mSurfaceRight.getHolder());
                                    Boolean ret = ezPlayer.startRealPlay();
                                    Log.i("TAG", ret.toString());
                                }
                            });
                        } catch (BaseException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
