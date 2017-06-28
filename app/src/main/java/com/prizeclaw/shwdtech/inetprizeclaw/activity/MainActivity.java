package com.prizeclaw.shwdtech.inetprizeclaw.activity;

import android.os.Build;
import android.os.Bundle;

import com.prizeclaw.shwdtech.inetprizeclaw.R;
import com.prizeclaw.shwdtech.inetprizeclaw.bean.AccessTokenBean;
import com.prizeclaw.shwdtech.inetprizeclaw.http.HttpManager;
import com.prizeclaw.shwdtech.inetprizeclaw.http.JSONUtils;
import com.prizeclaw.shwdtech.inetprizeclaw.http.XHttpResponse;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private EZOpenSDK mEZOpenSDK;

    private EZPlayer ezPlayerRight;

    private EZPlayer ezPlayerLeft;

    private SurfaceView mSurfaceLeft;

    private SurfaceView mSurfaceRight;

    private SurfaceHolder mSurfaceHolderRight;

    private SurfaceHolder mSurfaceHolderLeft;

    private AccessTokenBean token;

    private LinearLayout.LayoutParams m1pxLayoutParams = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);

    private LinearLayout.LayoutParams mFillLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    private boolean _isRightOpen = false;

    @BindView(R.id.btnForward) Button btnForward;

    @BindView(R.id.btnBackup) Button btnBackup;

    @BindView(R.id.btnLeft) Button btnLeft;

    @BindView(R.id.btnRight) Button btnRight;

    @OnClick(R.id.btnForward) void Forward(){
        _isRightOpen = true;
        ChangeSuraceLayout(_isRightOpen);
    }

    @OnClick(R.id.btnBackup) void Backup(){
        _isRightOpen = true;
        ChangeSuraceLayout(_isRightOpen);
    }

    @OnClick(R.id.btnLeft) void Left(){
        _isRightOpen = false;
        ChangeSuraceLayout(_isRightOpen);
    }

    @OnClick(R.id.btnRight) void Right(){
        _isRightOpen = false;
        ChangeSuraceLayout(_isRightOpen);
    }

    private void ChangeSuraceLayout(boolean isRight){
        if(isRight){
            mSurfaceRight.setLayoutParams(mFillLayoutParams);
            mSurfaceLeft.setLayoutParams(m1pxLayoutParams);
        }else{
            mSurfaceRight.setLayoutParams(m1pxLayoutParams);
            mSurfaceLeft.setLayoutParams(mFillLayoutParams);
        }
    }

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
        ButterKnife.bind(this);
        mEZOpenSDK = EZOpenSDK.getInstance();
        HttpManager.getAccessToken(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
                token = JSONUtils.parseAccesstokenBean(response);
                mEZOpenSDK.setAccessToken(token.getData().getAccessToken());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
        mSurfaceRight = (SurfaceView)findViewById(R.id.surfaceViewRight);
        mSurfaceLeft = (SurfaceView)findViewById(R.id.surfaceViewLeft);
        mSurfaceHolderRight = mSurfaceRight.getHolder();
        mSurfaceHolderLeft = mSurfaceLeft.getHolder();
        mSurfaceHolderRight.addCallback(this);
        mSurfaceHolderLeft.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(surfaceHolder == mSurfaceHolderRight){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        final EZDeviceInfo info = mEZOpenSDK.getDeviceInfo("779567540");
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                int camNum = info.getCameraNum();
                                String serial = info.getDeviceSerial();
                                ezPlayerRight = mEZOpenSDK.createPlayer(serial, camNum);
                                ezPlayerRight.setSurfaceHold(mSurfaceHolderRight);
                                Boolean ret = ezPlayerRight.startRealPlay();
                                Log.i("TAG", ret.toString());
                            }
                        });
                    } catch (BaseException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }else if(surfaceHolder == mSurfaceHolderLeft){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        final EZDeviceInfo info = mEZOpenSDK.getDeviceInfo("779567543");
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                int camNum = info.getCameraNum();
                                String serial = info.getDeviceSerial();
                                ezPlayerLeft = mEZOpenSDK.createPlayer(serial, camNum);
                                ezPlayerLeft.setSurfaceHold(mSurfaceHolderLeft);
                                Boolean ret = ezPlayerLeft.startRealPlay();
                                Log.i("TAG", ret.toString());
                            }
                        });
                    } catch (BaseException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
