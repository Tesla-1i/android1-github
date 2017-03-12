package mxy.com.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.view.View;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.Button;
//import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        //View button1 = findViewById(R.id.button_1);


//        Button button1 = (Button) findViewById(R.id.button_1);
//        button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(FirstActivity.this, "你点击了button1",Toast.LENGTH_SHORT).show();
//                Log.i("FirstActivity","你点击了button1");
//                //Intent intent = new Intent(FirstActivity.this,SecondActivity.class );
//                Intent intent = new Intent("android.intent.action.ANSWER");
//                intent.addCategory("android.intent.category.MY_CATEGORY");
//                startActivity(intent);
//            }
//        });

        //button12 启动 活动3
        Button button12 = (Button) findViewById(R.id.button_12);
        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"你点击了Button12",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

       Button button13 = (Button) findViewById(R.id.button_13);
        button13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String data = "Hello Activity2";
                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });




        Button button14 = (Button) findViewById(R.id.button_14);
        button14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    Log.i("返回数据到activity1",returnData);
                    Toast.makeText(FirstActivity.this, returnData, Toast.LENGTH_SHORT);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"add item",Toast.LENGTH_SHORT).show();
            case R.id.remove_item:
                Toast.makeText(this,"remove item", Toast.LENGTH_SHORT).show();
            default:

        }
        return true;
    }
}
