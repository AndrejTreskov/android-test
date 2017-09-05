package org.ortec.emulatortest;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mButton;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    public void onClickSpinner(View v) {
        //spinner für 10 sek starten
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
                spinnerHandler.sendEmptyMessage(0);
            }
        };
        Thread tr = new Thread(r);
        tr.start();

    }

    Handler spinnerHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            mProgressBar.setVisibility(View.GONE);
        }
    };

    public void onClickToast(View v) {
        Toast.makeText(this, "Hallo Toast!", Toast.LENGTH_LONG).show();
    }

    public void onClickRestApiTest(View v) {
       Intent i = new Intent(this, RestApiTestActivity.class);
       startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public boolean OnCreateOptionsMenu(Menu menu) {
        //getMenuInflater(R.menu.menu_activity_main, menu);
        return true;
    }
*/

}
