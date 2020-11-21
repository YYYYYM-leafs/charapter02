package com.example.lifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    //onCreate()。在活动第一次被创建的时候调用。在这个方法中完成活动的初始化操作，比如加载布局、绑定事件等。
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        //onCreate( )方法有一个Bundle类型的参数。这个参数在一般情况下都是null,
        //但是如果在活动被系统回收之前有通过onSaveInstanceState()方法来保存数据的话，
        // 这个参数就会带有之前所保存的全部数据，我们只需要再通过相应的取值方法将数据取出即可。
        if(savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
            Log.d(TAG, tempData);
        }

        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);
        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    //onStart()。在活动由不可见变为可见的时候调用。
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    //onResume()。在活动准备好和用户进行交互的时候调用。此时的活动一定位于返回栈的栈顶，并且处于运行状态。
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    //onPause()。方法在系统准备去启动或者恢复另一个活动的时候调用。
    // 通常会在这个方法中将一些消耗CPU的资源释放掉，以及保存一些关键数据，
    // 但这个方法的执行速度一定要快，不然会影响到新的栈顶活动的使用。
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    //onStop()。在活动完全不可见的时候调用。
    // onStop()和onPause( )方法的主要区别在于，如果启动的新活动是一个对话框式的活动，
    // 那么onPause()方法 会得到执行，而onStop()方法并不会执行。
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    //onDestroy()。在活动被销毁之前调用，之后活动的状态将变为销毁状态。
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    //onRestart()。在活动由停止状态变为运行状态之前调用，也就是活动被重新启动了。
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    //Activity 中还提供了一个onSaveIns tanceState()回调方法，这个方法可以保证在活动被回收之前一定会被调用,
    // 因此我们可以通过这个方法来解决活动被回收时临时数据得不到保存的问题。
    //onSaveInstanceState()方法会携带一个Bundle类型的参数，Bundle 提供了一系列的方法用于保存数据，
    // 比如可以使用putString()方法保存字符串，使用putInt()方法保存整型数据，以此类推。
    // 每个保存方法需要传入两个参数，第-一个参数是键，用于后面从Bundle中取值,第二个参数是真正要保存的内容。
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "something you just typed";
        outState.putString("data_key", tempData);
    }

}
