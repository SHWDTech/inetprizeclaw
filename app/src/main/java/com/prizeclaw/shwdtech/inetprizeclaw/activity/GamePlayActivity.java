package com.prizeclaw.shwdtech.inetprizeclaw.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.prizeclaw.shwdtech.inetprizeclaw.R;
import com.prizeclaw.shwdtech.inetprizeclaw.application.MainApplication;
import com.prizeclaw.shwdtech.inetprizeclaw.bean.AccessTokenBean;
import com.prizeclaw.shwdtech.inetprizeclaw.http.HttpManager;
import com.prizeclaw.shwdtech.inetprizeclaw.http.JSONUtils;
import com.prizeclaw.shwdtech.inetprizeclaw.http.XHttpResponse;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class GamePlayActivity extends AppCompatActivity {
    private static final String TAG = "bb";
    // SP name
    private static final String ACCESS_TOKEN_SP = "AccessTokenSp";
    // token 过期时间 key
    private static final String TOKEN_EXPIRE_DATE_KEY = "TokenExpireDate";

    private static final int LEFT_DEVICE_INFO = 1;
    private static final int RIGHT_DEVICE_INFO = 2;

    private EZOpenSDK mEZOpenSDK;
    private EZPlayer ezPlayerRight;
    private EZPlayer ezPlayerLeft;

    private LinearLayout.LayoutParams m1pxLayoutParams = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
    private LinearLayout.LayoutParams mFillLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

    private boolean _isRightOpen = false;
    private Button _onHoldButton;

    @BindView(R.id.surfaceViewLeft)
    SurfaceView mSurfaceLeft;
    @BindView(R.id.surfaceViewRight)
    SurfaceView mSurfaceRight;
    @BindView(R.id.btnForward)
    Button btnForward;
    @BindView(R.id.btnBackup)
    Button btnBackup;
    @BindView(R.id.btnLeft)
    Button btnLeft;
    @BindView(R.id.btnRight)
    Button btnRight;
    @BindView(R.id.btnCatch)
    Button btnCatch;

    // 记录AccessToken是否有效
    private boolean isAccessTokenValid = false;
    private boolean isGameStarted = false;
    private EZDeviceInfo mLeftDeviceInfo;
    private EZDeviceInfo mRightDeviceInfo;
    private SurfaceHolder mSurfaceHolderRight;
    private SurfaceHolder mSurfaceHolderLeft;
    private MainHandler mainHandler;

    @OnTouch(R.id.btnForward)
    boolean Forward(View v, MotionEvent event) {
        if (_onHoldButton != null && _onHoldButton != btnForward) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isGameStarted) {
                GameStart();
            }
            btnForward.setBackgroundResource(R.drawable.control_forward_72_gray);
            _onHoldButton = btnForward;
            _isRightOpen = true;
            ChangeSurfaceLayout(_isRightOpen);
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 0);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            btnForward.setBackgroundResource(R.drawable.control_forward_72);
            _onHoldButton = null;
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 5);
        }

        return true;
    }

    @OnTouch(R.id.btnBackup)
    boolean Backward(View v, MotionEvent event) {
        if (_onHoldButton != null && _onHoldButton != btnBackup) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isGameStarted) {
                GameStart();
            }
            btnBackup.setBackgroundResource(R.drawable.control_backward_72_gray);
            _onHoldButton = btnBackup;
            _isRightOpen = true;
            ChangeSurfaceLayout(_isRightOpen);
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 1);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            btnBackup.setBackgroundResource(R.drawable.control_backward_72);
            _onHoldButton = null;
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 5);
        }
        return true;
    }

    @OnTouch(R.id.btnLeft)
    boolean Left(View v, MotionEvent event) {
        if (_onHoldButton != null && _onHoldButton != btnLeft) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isGameStarted) {
                GameStart();
            }
            btnLeft.setBackgroundResource(R.drawable.control_left_72_gray);
            _onHoldButton = btnLeft;
            _isRightOpen = false;
            ChangeSurfaceLayout(_isRightOpen);
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 2);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            btnLeft.setBackgroundResource(R.drawable.control_left_72);
            _onHoldButton = null;
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 5);
        }
        return true;
    }

    @OnTouch(R.id.btnRight)
    boolean Right(View v, MotionEvent event) {
        if (_onHoldButton != null && _onHoldButton != btnRight) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isGameStarted) {
                GameStart();
            }
            btnRight.setBackgroundResource(R.drawable.control_right_72_gray);
            _onHoldButton = btnRight;
            _isRightOpen = false;
            ChangeSurfaceLayout(_isRightOpen);
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 3);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            btnRight.setBackgroundResource(R.drawable.control_right_72);
            _onHoldButton = null;
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 5);
        }
        return true;
    }

    @OnTouch(R.id.btnCatch)
    boolean Catch(View v, MotionEvent event) {
        if (_onHoldButton != null && _onHoldButton != btnCatch) return true;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            btnCatch.setBackgroundResource(R.drawable.control_catch_72_gray);
            _onHoldButton = btnCatch;
            HttpManager.postControlCommand(new XHttpResponse() {
                @Override
                public void onResponse(String response) {
                }

                @Override
                public void onError(Throwable e) {

                }
            }, "00000001", 4);
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            btnCatch.setBackgroundResource(R.drawable.control_catch_72);
            _onHoldButton = null;
        }
        return true;
    }

    private void ChangeSurfaceLayout(boolean isRight) {
        if (isRight) {
            mSurfaceRight.setLayoutParams(mFillLayoutParams);
            mSurfaceLeft.setLayoutParams(m1pxLayoutParams);
        } else {
            mSurfaceRight.setLayoutParams(m1pxLayoutParams);
            mSurfaceLeft.setLayoutParams(mFillLayoutParams);
        }
    }

    private SurfaceHolder.Callback mLeftCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mSurfaceHolderLeft = holder;
            bindLeftDeviceToSurfaceView();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mSurfaceHolderLeft = null;
        }
    };

    private SurfaceHolder.Callback mRightCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mSurfaceHolderRight = holder;
            bindRightDeviceToSurfaceView();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mSurfaceHolderRight = null;
        }
    };

    private void GameStart() {
        HttpManager.postClientOperate(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
            }

            @Override
            public void onError(Throwable e) {

            }
        }, "00000001", 1);
        isGameStarted = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(15000);
                    //在这里执行切换显示内容的操作。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
        setContentView(R.layout.activity_gameplay);
        ButterKnife.bind(this);
        mainHandler = new MainHandler(this);
        mEZOpenSDK = MainApplication.getApplicationEZOpenSDK();

        mSurfaceLeft.getHolder().addCallback(mLeftCallback);
        mSurfaceRight.getHolder().addCallback(mRightCallback);

        isAccessTokenValid = checkLocalAccessTokenValid();
        if (isAccessTokenValid) {
            Log.d(TAG, "isAccessTokenValid");
            fetchLeftDeviceInfo();
            fetchRightDeviceInfo();
        } else {
            fetchAccessToken();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHandler.removeCallbacksAndMessages(null);
        mainHandler = null;
    }

    /**
     * 检查本地AccessToken是否有效
     *
     * @return 是否有效
     */
    private boolean checkLocalAccessTokenValid() {
        SharedPreferences sp = getSharedPreferences(ACCESS_TOKEN_SP, Context.MODE_PRIVATE);
        String tokenExpireDate = sp.getString(TOKEN_EXPIRE_DATE_KEY, "2000-01-01 00:00:00 000");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
        try {
            Date date = parser.parse(tokenExpireDate);
            return !date.before(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取远端AccessToken
     */
    private void fetchAccessToken() {
        HttpManager.getAccessToken(new XHttpResponse() {
            @Override
            public void onResponse(String response) {
                AccessTokenBean token = JSONUtils.parseAccesstokenBean(response);
                mEZOpenSDK.setAccessToken(token.getData().getAccessToken());
                Date expireDate = new Date(Long.parseLong(token.getData().getExpireTime()));
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
                String expireDateStr = parser.format(expireDate);
                saveAccessTokenToLocal(expireDateStr);
                // 加载TOKEN完毕 开始加载Info 信息
                fetchLeftDeviceInfo();
                fetchRightDeviceInfo();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    /**
     * 保存AccessToken过期时间到本地
     *
     * @param expireDateStr 要存储的AccessToken过期时间
     */
    private void saveAccessTokenToLocal(String expireDateStr) {
        SharedPreferences sp = getSharedPreferences(ACCESS_TOKEN_SP, Context.MODE_PRIVATE);
        sp.edit().putString(TOKEN_EXPIRE_DATE_KEY, expireDateStr).apply();
    }

    /**
     * 获取左摄像头Info
     */
    private void fetchLeftDeviceInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mLeftDeviceInfo = mEZOpenSDK.getDeviceInfo("779567543");
                    if (mainHandler != null) {
                        mainHandler.sendEmptyMessage(LEFT_DEVICE_INFO);
                    }
                    Log.d(TAG, "fetchLeftDeviceInfo success");
                } catch (BaseException e) {
                    Log.d(TAG, "fetchLeftDeviceInfo failed");
                }
            }
        }).start();
    }

    /**
     * 获取右摄像头Info
     */
    private void fetchRightDeviceInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mRightDeviceInfo = mEZOpenSDK.getDeviceInfo("779567540");
                    if (mainHandler != null) {
                        mainHandler.sendEmptyMessage(RIGHT_DEVICE_INFO);
                    }
                    Log.d(TAG, "fetchRightDeviceInfo Success");
                } catch (BaseException e) {
                    Log.d(TAG, "fetchRightDeviceInfo failed");
                }
            }
        }).start();
    }

    /**
     * 绑定左面的摄像头info 到SurfaceView.
     */
    @MainThread
    private void bindLeftDeviceToSurfaceView() {
        Log.d(TAG, "bindLeftDeviceToSurfaceView");
        if (mSurfaceHolderLeft == null) {
            Log.d(TAG, "bindLeftDeviceToSurfaceView mSurfaceHolderLeft == null");
            return;
        }
        if (mLeftDeviceInfo == null) {
            Log.d(TAG, "bindLeftDeviceToSurfaceView mLeftDeviceInfo == null");
            return;
        }

        int camNum = mLeftDeviceInfo.getCameraNum();
        String serial = mLeftDeviceInfo.getDeviceSerial();
        Log.d(TAG, "bindLeftDeviceToSurfaceView camNum : " + camNum + " , serial : " + serial);
        ezPlayerLeft = mEZOpenSDK.createPlayer(serial, camNum);
        ezPlayerLeft.setSurfaceHold(mSurfaceHolderLeft);
        Boolean ret = ezPlayerLeft.startRealPlay();
        Log.d(TAG, "bindLeftDeviceToSurfaceView ret == " + ret);
    }

    /**
     * 绑定右面的摄像头info 到SurfaceView.
     */
    @MainThread
    private void bindRightDeviceToSurfaceView() {
        Log.d(TAG, "bindRightDeviceToSurfaceView");

        if (mSurfaceHolderRight == null) {
            Log.d(TAG, "bindRightDeviceToSurfaceView mSurfaceHolderRight == null");
            return;
        }
        if (mRightDeviceInfo == null) {
            Log.d(TAG, "bindRightDeviceToSurfaceView mRightDeviceInfo == null");
            return;
        }

        int camNum = mRightDeviceInfo.getCameraNum();
        String serial = mRightDeviceInfo.getDeviceSerial();
        ezPlayerRight = mEZOpenSDK.createPlayer(serial, camNum);
        ezPlayerRight.setSurfaceHold(mSurfaceHolderRight);
        Boolean ret = ezPlayerRight.startRealPlay();
        Log.d(TAG, "bindRightDeviceToSurfaceView ret == " + ret);

    }

    static class MainHandler extends Handler {
        private final WeakReference<GamePlayActivity> mainActivityWeakReference;

        public MainHandler(GamePlayActivity activity) {
            mainActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            GamePlayActivity activity = mainActivityWeakReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case LEFT_DEVICE_INFO:
                    activity.bindLeftDeviceToSurfaceView();
                    break;
                case RIGHT_DEVICE_INFO:
                    activity.bindRightDeviceToSurfaceView();
                    break;
            }
        }
    }
}
