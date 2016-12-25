package mxy.com.edittext1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    public static final String[] autoinfo = new String[]{
            "明日科技","C#从入门到精通","java"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autoinfo);
        AutoCompleteTextView actxTextView = (AutoCompleteTextView)findViewById(R.id.actxt);
        actxTextView.setAdapter(adapter);
    }
}
