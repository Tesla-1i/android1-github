package mxy.com.startactivity1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView txt;/////////////////是textview不是view
    private Button  btn;
    public static final int RESULT_CODE = 1;

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent();
            intent.putExtra("back","第二个返回的值");
            setResult(RESULT_CODE,intent);
            finish();
        }
    };

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == RESULT_CODE){
//            if (requestCode == Main2Activity.REQUEST_CODE){
//                Bundle bundle = data.getExtras();
//                String str = bundle.getString("back");
//
//                Toast.makeText(Main2Activity.this,str,Toast.LENGTH_LONG).show();
//
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        //Bundle bundle= intent.getExtras();
        Bundle bundle= this.getIntent().getExtras();
        String str = bundle.getString("str");
        txt=(TextView)findViewById(R.id.txt1);
        txt.setText(str);
        //txt.setTextDirection(str);
        btn=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(listener);
    }
}
