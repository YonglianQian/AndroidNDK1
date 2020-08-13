package com.example.androidndk1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("myjni");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView=new TextView(this);
        textView.setText(getMessage());

        setContentView(textView);

        // Example of a call to a native method

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String getMessage();
}
