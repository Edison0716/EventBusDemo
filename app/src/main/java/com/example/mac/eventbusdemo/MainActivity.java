package com.example.mac.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.mac.eventbusdemo.base.BaseEventActivity;
import com.example.mac.eventbusdemo.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseEventActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }
    public void sendEventBus(View v){
        //发送自定义Toast
        EventBus.getDefault().post(new EventMap.HExceptionEvent("你有病吧！"));
    }
    public void sendCustomEventBus(View v){
        //发送特定code对应的Toast内容
        EventBus.getDefault().post(new EventMap.HExceptionEvent(213,""));
    }
    public void turnToMain2Activity(View v){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        EventBus.getDefault().postSticky(new EventMap.HExceptionEvent("你有病吧！"));
    }

    /**
     * 事件观察者
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMainThread(EventMap.BaseEvent event){
        if (event != null && event instanceof EventMap.HExceptionEvent){
            if (!TextUtils.isEmpty(event.message)){
                textView.setText(event.message);
                if (event.message.equals("finish")){
                    finish();
                }
            }
        }
    }
}
