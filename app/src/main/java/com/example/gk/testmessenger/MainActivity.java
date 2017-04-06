package com.example.gk.testmessenger;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 原来服务不开启都可以被别的app绑定 来达到开启的目的
 * 毕竟start 和 bind 是开启服务的两种方式嘛
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startServer(View view) {
        Intent intent = new Intent(this, MessagerService.class);
        ComponentName componentName = startService(intent);
        Log.e("test", "服务开启状态:" + (componentName != null ? "成功" : "失败"));
    }

    public void stopServer(View view) {
        Intent intent = new Intent(this, MessagerService.class);
        boolean result = stopService(intent);
        Log.e("test", "服务关闭状态:" + (result ? "成功" : "失败"));
    }
}
