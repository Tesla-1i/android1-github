package mxy.com.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.i("活动2",data);
//        Toast.makeText(SecondActivity.this,"你点击了button13",Toast.LENGTH_SHORT).show();
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","Hello activity2");
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
}
