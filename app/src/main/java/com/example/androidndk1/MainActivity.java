package com.example.androidndk1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.

    static {
        System.loadLibrary("native-lib");
    }

    public static final String EXTRA_MESSAGE = "YES!! Extra message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCenter.setLogLevel(Log.VERBOSE);
        AppCenter.start(getApplication(), "0748a395-56d6-4ae0-bda2-0efdf14b7dca",
                Analytics.class, Crashes.class);

        Crashes.getMinidumpDirectory().thenAccept(new AppCenterConsumer<String>() {
            @Override
            public void accept(String path) {

                // Path is null when Crashes is disabled.
                if (path != null) {
                    setupNativeCrashesListener(path);
                }
            }
        });

        setContentView(R.layout.activity_main);
        //TextView textView = findViewById(R.id.textView2);
        //textView.setText(stringFromJNI());

    }
    public void sendMessage(View view){

        //Crashes.generateTestCrash();
        //initialize the variable.
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();


        //track the event.
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date d=new Date();
        Analytics.trackEvent("button is clicked at "+sdf.format(d)+" Message: "+message);


        intent.putExtra(EXTRA_MESSAGE, message+"  :"+stringFromJNI());


        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String getMessage();

    public native String stringFromJNI();
    public native void setupNativeCrashesListener(String path);


}
