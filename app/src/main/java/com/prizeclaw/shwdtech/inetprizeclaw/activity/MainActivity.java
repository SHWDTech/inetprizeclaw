package com.prizeclaw.shwdtech.inetprizeclaw.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.prizeclaw.shwdtech.inetprizeclaw.R;
import com.prizeclaw.shwdtech.inetprizeclaw.bean.MachineOperateResultBean;
import com.prizeclaw.shwdtech.inetprizeclaw.http.HttpManager;
import com.prizeclaw.shwdtech.inetprizeclaw.http.JSONUtils;
import com.prizeclaw.shwdtech.inetprizeclaw.http.XHttpResponse;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private volatile boolean isRequesting = false;
    private Handler mHandler;
    private static final int GET_COINSTATUS = 1;
    static class MainHandler extends Handler {
        final WeakReference<MainActivity> reference;

        public MainHandler(MainActivity activity) {
            reference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = reference.get();
            if(activity == null) {
                return;
            }
            switch (msg.what) {
                case GET_COINSTATUS:
                    activity.getCoinStatus();
                    break;
            }
        }
    }

    @BindView(R.id.btnCoinIn)
    Button btnCoinIn;

    @OnClick(R.id.btnCoinIn)
    void CoinIn() {
        HttpManager.postControlCommand(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
                getCoinStatus();
            }

            @Override
            public void onError(Throwable e) {

            }
        }, "00000001", 6);
        btnCoinIn.setText("准备中...");
        btnCoinIn.setClickable(false);
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
        mHandler = new MainHandler(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    private void getCoinStatus() {
        if (isRequesting) return;
        isRequesting = true;
        HttpManager.postClientOperate(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
                isRequesting = false;
                MachineOperateResultBean machineOperateResult = JSONUtils.parseMachineOperateReulst(response);
                if (!machineOperateResult.getIsOperateResultOk()) {
                    mHandler.sendEmptyMessageDelayed(GET_COINSTATUS, 1000);
                } else {
                    Intent gameActivity = new Intent();
                    gameActivity.setClass(MainActivity.this, GamePlayActivity.class);
                    gameActivity.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(gameActivity);
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                isRequesting = false;
                // TODO 出错了你怎么办
            }
        }, "00000001", 0);
    }
}