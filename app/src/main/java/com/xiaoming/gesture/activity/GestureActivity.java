package com.xiaoming.gesture.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.gesture.R;
import com.xiaoming.gesture.view.GestureLockViewGroup;

/**
 * 手势密码登录界面
 */
public class GestureActivity extends Activity {
    private GestureLockViewGroup mGestureLockViewGroup;

    private TextView login_textView_pormpt;
    private int errCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        init();
    }

    private void init() {
        login_textView_pormpt = (TextView) findViewById(R.id.login_textView_pormpt);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
        // 设置最大限制错误次数
        mGestureLockViewGroup.setUnMatchExceedBoundary(5);
        // 设置正确密码
        mGestureLockViewGroup.setAnswer(new int[]{1, 2, 3, 6, 9});

        mGestureLockViewGroup.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {

            @Override
            public void onUnmatchedExceedBoundary() {
                // 输入错误数超限
                login_textView_pormpt.setVisibility(View.VISIBLE);
                login_textView_pormpt.setText("您已输错5次");
            }

            @Override
            public void onGestureEvent(boolean matched) {
                Toast.makeText(GestureActivity.this, matched ? "登录成功" : "错误，请重试！", Toast.LENGTH_SHORT).show();
                if (matched) {
                    // TODO 登录成功处理
                } else {
                    errCount++;
                    login_textView_pormpt.setVisibility(View.VISIBLE);
                    login_textView_pormpt.setText("您已输错" + errCount + "次，还有" + (5 - errCount) + "次机会");
                }
            }

            @Override
            public void onBlockSelected(int cId) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        errCount = 0;
        login_textView_pormpt.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
