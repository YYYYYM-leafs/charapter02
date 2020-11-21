package com.example.charapter02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    @Override
    //当用户不是点击按钮返回而是直接按下back键返回则会调用onBackPressed（）方法
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent();
        intent1.putExtra("data_return", "通过startActivityForResult，活动销毁返回参数");
        setResult(RESULT_OK, intent1);
        finish();
    }

    //standard启动模式
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.second_layout);
//
//        //首先可以通过getIntent()方 法获取到用于启动SecondActivity 的Intent， 然后调用getString Extra()方法，传入相应的键值，得到传递的数据。
//        // 传递的是字符串，所以使用getSt ringExtra()方法来获取传递的数据。
//        // 传递的是整型数据，则使用getIntExtra()方法;
//        // 如果传递的是布尔型数据，则使用getBooleanExtra()方法，
//        // 以此类推。
////        Intent intent = getIntent();
////        String data = intent.getStringExtra("extra_data");
////        Log.d("SecondActivity", data);
//
//        Button button_20 = findViewById(R.id.button_20);
//        button_20.setOnClickListener(new View.OnClickListener() {
//            @Override
//            //可以看到，我们还是构建了一个Intent，只不过这个Intent仅仅是用于传递数据而已，它没
//            //有指定任何的“意图”。紧接着把要传递的数据存放在Intent中，然后调用了setResult()方法。
//            //这个方法非常重要，是专门用于向上一个活动返回数据的。setResult()方法接收两个参数,
//            //第一个参数用于向上一个活动返回处理结果，一般只使用RESULT_ 0K或RESULT_ CANCELED这
//            //两个值，第二个参数则把带有数据的Intent 传递回去,然后调用了finish( )方法来销毁当前活动。
//            public void onClick(View v) {
//                Intent intent1 = new Intent();
//                intent1.putExtra("data_return", "通过startActivityForResult，活动销毁返回参数");
//                setResult(RESULT_OK, intent1);
//                finish();
//            }
//        });
//
//    }

//        //singleTop启动模式
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.second_layout);
//
//            Button button_21 = findViewById(R.id.button_21);
//            button_21.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
//                    startActivity(intent);
//
//               }
//            });
//        }

        //singleTask启动模式
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.second_layout);
//
//            Button button_22 = findViewById(R.id.button_22);
//            button_22.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d("SecondActivity", "onDestory + singleTask启动模式");
//    }


//    singleInstance启动模式
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Log.d("SecondActivity", "singleInstance启动模式" + "Task id is " + getTaskId());
        Button button_23 = findViewById(R.id.button_23);
        button_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThridActivity.class);
                startActivity(intent);

            }
        });


        //调用ActivityCollector.finishAll()方法结束所有活动
        Button button24 = findViewById(R.id.button_24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });
    }

    //对其他活动提供一个启动本活动的方法，声明启动本活动需要的参数列表
    public static void startActivity(Context context, String data1, String data2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("str1",data1);
        intent.putExtra("str2", data2);
        context.startActivity(intent);
    }
}