package com.example.mac.eventbusdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.mac.eventbusdemo.base.BaseEventActivity;
import com.example.mac.eventbusdemo.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends BaseEventActivity {

    private TextView textView;
    private String message;
    private static final int CODE_REFRESH=0;//刷新页面标记位
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_REFRESH:
                    textView.setText(message);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void finish(View v){
        EventBus.getDefault().post(new EventMap.HExceptionEvent("finish"));
        finish();
    }

    /**
     * 事件观察者
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMainThread(EventMap.BaseEvent event){
        if (event != null && event instanceof EventMap.HExceptionEvent){
            if (!TextUtils.isEmpty(event.message)){
                ToastUtils.showToast(this,event.message);
                message = event.message;
                Message message = new Message();
                message.what = CODE_REFRESH;
                Main2Activity.this.handler.sendMessage(message);
            }
        }
    }
}
