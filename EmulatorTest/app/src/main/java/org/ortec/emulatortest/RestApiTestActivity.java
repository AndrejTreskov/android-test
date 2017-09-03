package org.ortec.emulatortest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApiTestActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api_test);


        mProgressBar = (ProgressBar) findViewById(R.id.waitSpinner);
        mProgressBar.setVisibility(View.VISIBLE);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setVisibility(View.GONE);


        Runnable r = new Runnable() {
           @Override
           public void run() {

               try {
                   URL githubEndpoint = new URL("http://system.wine-trophy.com:51572/api/system/serverInfo");
                   // Create connection
                   HttpURLConnection myConnection =
                           (HttpURLConnection) githubEndpoint.openConnection();
                   myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                   myConnection.setRequestProperty("content-type","application/json; charset=utf-8");
                   if (myConnection.getResponseCode() == 200) {
                       InputStream responseBody = myConnection.getInputStream();
                       String value = convertStreamToString(responseBody);
                       Message msg = msgHandler.obtainMessage(0, value);
                       msg.sendToTarget();
                   } else {
                       Message msg = msgHandler.obtainMessage(-2, "wrong response");
                       msg.sendToTarget();
                   }
                   myConnection.disconnect();
               } catch(Exception ex) {
                   Message msg = msgHandler.obtainMessage(-1, ex.getMessage());
                   msg.sendToTarget();
               }
           }
       };
       Thread tr = new Thread(r);
       tr.start();

    }

    static String convertStreamToString(java.io.InputStream is) {
        if (is == null) {
            return "";
        }

        java.util.Scanner s = new java.util.Scanner(is);
        s.useDelimiter("\\A");

        String streamString = s.hasNext() ? s.next() : "";

        s.close();

        return streamString;
    }



    @Override
    protected void onStart() {
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT);
        super.onStart();
    }

    Handler msgHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            mProgressBar.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            String messageText = (String)msg.obj;
            switch(msg.what) {
                case -1:
                    mTextView.setText(String.format("Exception: %s", messageText));
                    break;
                case -2:
                    mTextView.setText(messageText);
                    break;
                case 0:
                    mTextView.setText(messageText);
                    break;
            }
        }
    };


}
