package mxy.com.startactivity1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by asus on 2016/12/25.
 */

public class Activity1 extends AppCompatActivity {

    private TextView txt;
    private Button btnButton;
    public final static int RESULT_CODE = 1;

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            //intent.setClass(Activity1.this,Activity1.class);
            intent.putExtra("back","第二个activity传过来的值");
            //startActivity(intent);
            //startActivityForResult(intent,REQUEST_CODE);
            setResult(RESULT_CODE,intent);
            finish();
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.link);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString("str");
        txt = (TextView)findViewById(R.id.Activity1_text);
        txt.setText(str);
        btnButton = (Button)findViewById(R.id.Activity1_btn);
        btnButton.setOnClickListener(listener);
    }
}
