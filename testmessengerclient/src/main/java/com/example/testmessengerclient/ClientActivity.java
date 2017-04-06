package com.example.testmessengerclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ClientActivity extends AppCompatActivity {

    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }

    public void bindService(View view) {
        //绑定服务
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.gk.testmessenger", "com.example.gk.testmessenger.MessagerService"));
        boolean bindResult = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        Log.e("test", "Client bindService:" + bindResult);
    }

    public void sendMessage(View view) {
        if (messenger != null) {
            //创建空消息
            Message message = Message.obtain(null, 999);//what = 999
            Bundle bundle = new Bundle();
            bundle.putString("data", "你好 来自客户端");
            //往消息中存数据
            message.setData(bundle);
            message.replyTo = replyMessenger;
            //消息者发送消息
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "消息发送成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请先绑定服务", Toast.LENGTH_SHORT).show();
        }

    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("test", "ClientActivity onServiceDisconnected");
        }
    };

    private Messenger replyMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("test", "ClientActivity 收到服务器的返回消息:" + msg.toString());
        }
    });

}
