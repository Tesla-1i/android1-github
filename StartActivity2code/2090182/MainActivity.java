package com.example.nete.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //1.定义三个子控件，也可以用私有的属性private，这样别的类无法直接访问
    Button bt;
    Button bt1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化三个子控件，没什么说的，这里的TextView初始化是为了要显示返回值
        bt=(Button)findViewById(R.id.button1);
        bt1= (Button) findViewById(R.id.button);
        tv= (TextView) findViewById(R.id.textView2);
        //一.好，下面进入第一个无返回值的跳转，设置第一个Button的监听事件，匿名内部类实现
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*这里实现意图并实现Avtivity的跳转，值得注意的是MainActivity.this的用法，
                这里用默认的this无法访问外类的MainActivity,
                还有种方法，就是先在匿名内部类外定义一个
                context=ct;
                ct=this;这样用ct就可以直接访问了
                */
                Intent in=new Intent(MainActivity.this,SActivity.class);
                startActivity(in);
                Log.i("日志","Button被点击了");
            }
        });
        //二.第二步，实现有返回值的跳转，设置第二个按钮的监听事件，匿名内部类
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //前面定义意图，与无返回值的一样
                Intent in=new Intent(MainActivity.this,SActivity.class);
                //实现意图的时候，有返回值，所以调用startActivityForResult方法，后面的整型数值是请求码，可以任意设置，但不能重复
                startActivityForResult(in,1);

            }
        });

    }
//然后是获取返回值，这里的onActivityResult方法是在onCreate方法外实现的，注意这一点
//关于该方法的三个参数，第一个是判断请求码，第二个是判断返回码，data是是第二个页面的传回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //设置一个判断语句来判断，这里意思是当请求码和返回码均相同的话才会返回数据
        if(requestCode==1&&resultCode==2){
            //这里的intent是第二个Activity的Intent的id
            String at=data.getStringExtra("intent");
            tv.setText(at);
        }
    }
}