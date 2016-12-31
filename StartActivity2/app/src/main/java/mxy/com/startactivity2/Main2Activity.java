package mxy.com.startactivity2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    public Button btn2_1;
    String str = "第二个activity的值已经返回";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn2_1 = (Button) findViewById(R.id.btn2_1);
        btn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent();
                intent3.putExtra("in",str);         //注意"in"
                setResult(3,intent3);       //3是结果码
                finish();
            }
        });
    }
}
