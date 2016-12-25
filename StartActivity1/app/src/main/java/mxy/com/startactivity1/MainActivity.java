package mxy.com.startactivity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Activity1.class);
            startActivity(intent);
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
