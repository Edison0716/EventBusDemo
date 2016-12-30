package com.example.mac.eventbusdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.mac.eventbusdemo.EventMap;
import com.example.mac.eventbusdemo.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mac on 16/12/30.
 */

public class BaseEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//EventBus注册
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//EventBus解注册
    }
}
