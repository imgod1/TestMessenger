package com.example.gk.testmessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 项目名称：TestMessenger
 * 类描述：
 * 创建人：gk
 * 创建时间：2017/4/6 9:31
 * 修改人：gk
 * 修改时间：2017/4/6 9:31
 * 修改备注：
 */
public class MessagerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("test", "MessagerService Messenger handleMessage:" + msg.toString());
        }
    });
}
