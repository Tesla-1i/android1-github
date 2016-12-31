package com.example.nete.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SActivity extends AppCompatActivity {
    //设置Button和要返回的字符
private Button bt;
    String ct="第二个的Activity的值已返回";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        bt= (Button) findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里设置意图并获取返回的字符
                Intent intent=new Intent();
                intent.putExtra("intent",ct);
                setResult(2,intent);//这里的2是返回码，
                finish();//finish();意思是关闭此Activity

            }
        });
    }
}
