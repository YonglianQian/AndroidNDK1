package com.example.androidndk1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("myjni");
    }
    public static final String EXTRA_MESSAGE = "YES!! Extra message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.start(getApplication(), "0748a395-56d6-4ae0-bda2-0efdf14b7dca",
                Analytics.class, Crashes.class);

//        TextView textView=new TextView(this);
//        textView.setText(getMessage());

        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView2);
        textView.setText(getMessage());

    }
    public void sendMessage(View view){
        Date d=new Date();
        Analytics.trackEvent("button is clicked at "+ d.getTime());
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String getMessage();

}
