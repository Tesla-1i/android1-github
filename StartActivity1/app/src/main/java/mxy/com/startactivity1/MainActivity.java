package mxy.com.startactivity1;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE = 1;



    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main2Activity.class);
            intent.putExtra("str","第一个activity传过来的值");
            //startActivity(intent);
            startActivityForResult(intent,REQUEST_CODE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE){
            if (requestCode == Main2Activity.RESULT_CODE){
               Bundle bundle = data.getExtras();
                String str = bundle.getString("back");

                Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnButton = (Button) findViewById(R.id.btnStart);
        btnButton.setOnClickListener(listener);
    }

}
