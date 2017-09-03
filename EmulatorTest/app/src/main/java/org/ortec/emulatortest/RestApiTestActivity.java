package org.ortec.emulatortest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RestApiTestActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api_test);


        mProgressBar = (ProgressBar) findViewById(R.id.waitSpinner);
        mProgressBar.setVisibility(View.VISIBLE);


        Runnable r = new Runnable() {
           @Override
           public void run() {

               long ftr = System.currentTimeMillis() +10000;
               while(System.currentTimeMillis() < ftr) {
                   synchronized (this) {
                       try {
                           wait(ftr - System.currentTimeMillis());
                       } catch(Exception ex) {
                           int dbg = 0;
                       }
                   }
               }
               handler.sendEmptyMessage(0);
           }
       };
       Thread tr = new Thread(r);
       tr.start();

    }



    @Override
    protected void onStart() {
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT);
        super.onStart();
    }

    private void init() {
        Toast.makeText(this, "Init", Toast.LENGTH_SHORT).show();
        mProgressBar.setVisibility(View.GONE);
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            init();
        }
    };
}
