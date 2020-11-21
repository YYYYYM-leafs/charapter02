package com.example.charapter02;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;

public class FirstActivity extends BaseActivity {

    @Override
    //通过getMenuInflater()方法能够得到MenuInflater对象，调用inflate()方法给当前活动创建菜单。
    // inflate()方法接收两个参数，第一个参数用于指定我们通过哪一个资源文件来创建菜单，
    // 第二个参数用于指定我们的菜单项将添加到哪一个Menu对象当中,
    // 使用onCreateOptionsMenu( )方法中传人的menu参数。
    // onCreateOptionsMenu( )返回true,表示允许创建的菜单显示出来，如果返回了false, 创建的菜单将无法显示。
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    //菜单响应事件
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "click addItem", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "click removeItem", Toast.LENGTH_SHORT).show();
                break;
        }
        return true ;
    }

    @Override
    //由于我们是使用startActivityForResult()方法来启动SecondActivity的,在SecondActivity
    //被销毁之后会回调上一个活动的onActivityResult()方法，因此我们需要在FirstActivity 中重
    //写这个方法来得到返回的数据，如下所示:
    //onActivityResult()方法带有三个参数，第一个参数requestCode,即我们在启动活动时
    //传人的请求码。第二个参数rresultCode， 即我们在返回数据时传人的处理结果。第三个参数
    //data,即携带着返回数据的Intent。 由于在一个活动中有可能调用startActivityForResult()
    //方法去启动很多不同的活动,每一个活动返回的数据都会回调到onActivityResult()这个方法
    //中，因此我们首先要做的就是通过检查requestCode 的值来判断数据来源。确定数据是从
    //SecondActivity返回的之后,我们再通过resultCode的值来判断处理结果是否成功。最后从data
    //中取值并打印出来，这样就完成了向上一个活动返回数据的工作。
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnData);
                }
                break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();加载布局，传入布局文件的id
        setContentView(R.layout.first_layout);
        //通过findViewById（）获取view对象，向下转型为Button
        Button button1 = (Button) findViewById(R.id.button_1);
        //setOnClickListener（）通过这个方法为按钮注册一个监听器，点击按钮就会触发监听器中的onClick方法
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "you onClick button1", Toast.LENGTH_SHORT).show();
                // makeText()方法需要传入3个参数。第-一个参数是Context,也就是Toast要求的上下文，
                // 由于活动本身就是-一个 Context对象，因此这里直接传入FirstActivity.this即可。
                // 第二个参数是Toast显示的文本内容，
                // 第三个参数是Toast显示的时长，有两个内置常量可以选择Toast. LENGTH_ SHORT 和Toast . LENGTH_ LONG。
            }
        });

        //setContentView();加载布局，传入布局文件的id
        Button button2 = (Button) findViewById(R.id.button_2);
        //通过findViewById（）获取view对象，向下转型为Button
        button2.setOnClickListener(new View.OnClickListener() {
            //setOnClickListener（）通过这个方法为按钮注册一个监听器，点击按钮就会触发监听器中的onClick方法
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this, "you onClick button1", Toast.LENGTH_LONG).show();
                // makeText()方法需要传入3个参数。第-一个参数是Context,也就是Toast要求的上下文，
                // 由于活动本身就是-一个 Context对象，因此这里直接传入FirstActivity.this即可。
                // 第二个参数是Toast显示的文本内容，
                // 第三个参数是Toast显示的时长，有两个内置常量可以选择Toast. LENGTH_ SHORT 和Toast . LENGTH_ LONG。
            }
        });


        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //活动销毁
                finish();
            }
        });

        //Intent大致可以分为两种:显式Intent和隐式Intent。
        //显式Intent有多个构造函数的重载，其中一个是Intent (Context packageContext, Class<?> cls)。
        // 第-个参数Context要求提供一个启动活动的上下文
        // 第二个参数Class则是指定想要启动的目标活动,通过这个构造函数就可以构建出Intent的“意图”
        Button button4 = (Button) findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //我们首先构建出了一个Intent， 传人FirstActivity.this 作为上下文，传入SecondActivity. class作为目标活动，
                // 这样我们的“意图”就非常明显了，即在FirstActivity 这个活动的基础上打开SecondActivity这个活动。
                // 然后通过startActivity( )方法来执行这个Intent。
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        //隐式Intent
        //android.intent.category.DEFAULT是一种默认的category,在调用startActivity()方法的时候会自动将这个category添加到Intent中。
        //所以可以不设置category
        Button button5 = (Button) findViewById(R.id.button_5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent("com.example.charapter02.ACTION_START");
                startActivity(intent);
            }
        });
        //设置category
        Button button6 = (Button) findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.charapter02.ACTION_START");
                intent.addCategory("com.example.charapter02.MY_CATEGORY");
                startActivity(intent);
            }
        });

        //隐式intent启动其他程序，以浏览器为例
        //这里我们首先指定了Intent 的action是Intent . ACTION_ VIEW, 这是一个Android系统内置的动作，
        // 其常量值为android. intent.action. VIEW。
        // 然后通过Uri. parse()方法，将一个网址字符串解析成-个Uri对象，再调用Intent的setData()方法将这个Uri对象传递进去。
        Button button7 = (Button) findViewById(R.id.button_7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setData()接收一个Uri对象，主要用于指定当前Intent正在操作的数据，而这些数据
                //通常都是以字符串的形式传人到Uri. parse()方法中解析产生的。，
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button button8 = (Button) findViewById(R.id.button_8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setData()接收一个Uri对象，主要用于指定当前Intent正在操作的数据，而这些数据
                //通常都是以字符串的形式传人到Uri. parse()方法中解析产生的。，
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:15585563788"));
//                startActivity(intent);

                if (ContextCompat.checkSelfPermission(FirstActivity.this, Manifest.
                        permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    /**
                     * 调用ActivityCompat.requestPermissions 申请授权
                     * 参数1：上下文
                     * 参数2：要申请的权限名数组
                     * 参数3：请求码，传入唯一值 1 即可
                     */
                    ActivityCompat.requestPermissions(FirstActivity.this, new String[]{Manifest.permission.CALL_PHONE} ,1);
                }else {
                    call();
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:15585563788"));
//                    startActivity(intent);
                }


            }
        });

        Button button9 = (Button) findViewById(R.id.button_9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            //使用显式Intent的方式来启动SecondActivity,并通过putExtra()方法传递了一个字符串。
            // putExtra()方法接收两个参数，第一个参数是键，用于后面从Intent 中取值，第二个参数才是真正要传递的数据。
            public void onClick(View v) {
                String data = "通过显式Intent中putExtra传参";
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

        Button button10 = (Button) findViewById(R.id.button_10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            //使用显式Intent的方式来启动SecondActivity,并通过putExtra()方法传递了一个字符串。
            // putExtra()方法接收两个参数，第一个参数是键，用于后面从Intent 中取值，第二个参数才是真正要传递的数据。
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //standard启动模式
        //Android是使用返回栈来管理活动的，在standard模式(即默认情况)下，每当启动一个新的活动，
        // 它就会在返回栈中人栈，并处于栈顶的位置。对于使用standard模式的活动,
        //系统不会在乎这个活动是否已经在返回栈中存在，每次启动都会创建该活动的一个新的实例。
        Log.d("FirstActivity", this.toString()+"standard启动模式");
        Button button11 = (Button) findViewById(R.id.button_11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });

        //singleTop启动模式
        //singleTop 模式。当活动的启动模式指定为singleTop,在启动活动时如果发现返回栈的栈顶已经是该活动，
        // 则认为可以直接使用它，不会再创建新的活动实例。
        Log.d("FirstActivity", this.toString()+"singleTop启动模式");
        Button button12 = (Button) findViewById(R.id.button_12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });


        //singleTask启动模式
        //使用singleTop 模式可以很好地解决重复创建栈顶活动的问题，
        // 如果该活动并没有处于栈顶的位置，还是可能会创建多个活动实例的。那么有没有什么办法
        //可以让某个活动在整个应用程序的上下文中只存在一一个实例呢?这就要借助singleTask模式来实
        //现了。当活动的启动模式指定为singleTask, 每次启动该活动时系统首先会在返回栈中检查是否
        //存在该活动的实例，如果发现已经存在则直接使用该实例，并把在这个活动之上的所有活动统统
        //出栈，如果没有发现就会创建一个新的活动实例。
        Log.d("FirstActivity", this.toString()+"singleTask启动模式");
        Button button13 = (Button) findViewById(R.id.button_13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        //singleInstance启动模式
        //指定为singleInstance模式的活动会启用一个新的返回栈来管理这个活动
        // (其实如果single Task模式指定了不同的taskAffinity，也会启动-一个新的返回栈)。
        // 那么这样做有什么意义呢?想象以下场景，假设我们的程序中有一个活动是允许其他程序调用的，
        // 如果我们想实现其他程序和我们的程序可以共享这个活动的实例，应该如何实现呢?
        //使用前面3种启动模式肯定是做不到的,因为每个应用程序都会有自己的返回栈，同一个活动在
        //不同的返回栈中人栈时必然是创建了新的实例。而使用singleInstance模式就可以解决这个问题，
        //在这种模式下会有一个单独的返回栈来管理这个活动，不管是哪个应用程序来访问这个活动，都
        //共用的同一个返回栈，也就解决了共享活动实例的问题。
        Log.d("FirstActivity", "singleInstance启动模式" + "Task id is " + getTaskId());
        Button button14 = (Button) findViewById(R.id.button_14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        //调用ActivityCollector.finishAll()方法结束所有活动
        Button button15 = findViewById(R.id.button_15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });

        //程序多人开发时适用的启动活动的方法，方便别人调用，在需要启动的活动中写一个startActivity（），
        //使需要启动这个活动的开发者，直接调用方法启动，并且可以清楚知道需要传递哪些参数
        Button button16 = findViewById(R.id.button_16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.startActivity(FirstActivity.this, "第一个参数", "第二个参数");
            }
        });

    }

    /**
     * 用户点击是否同意申请权限之后，会将结果返回到 onRequestPermissionsResult() 方法中
     *
     * @param requestCode
     * @param permissions
     * @param grantResults 封装了授权的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                /**
                 * 判断用户的授权结果，如果同意就拨打电话，
                 * 不同意就放弃，弹出失败提示
                 */
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "用户拒绝拨打电话", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 拨打电话的逻辑操作
     */
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity", "onRestart + singleTask启动模式");
    }
}