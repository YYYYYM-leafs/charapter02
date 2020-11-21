package com.example.charapter02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThridActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thrid_activity);
        Log.d("ThridActivity", "singleInstance启动模式" + "Task id is " + getTaskId());

        //调用ActivityCollector.finishAll()方法结束所有活动
        Button button32 = findViewById(R.id.button_32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });

    }


}