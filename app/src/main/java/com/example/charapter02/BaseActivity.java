package com.example.charapter02;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//在BaseActivity的onCreate()方法中调用了ActivityCollector 的addActivity()方
//法,表明将当前正在创建的活动添加到活动管理器里。然后在BaseActivity中重写onDestroy()
//方法，并调用了ActivityCollector的removeActivity()方法，表明将- -个 马上要销毁的活
//动从活动管理器里移除。
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("BaseActivity", getClass().getSimpleName()+"*************************");
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
