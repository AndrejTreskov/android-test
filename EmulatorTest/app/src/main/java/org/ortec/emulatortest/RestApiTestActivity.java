package org.ortec.emulatortest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.ortec.emulatortest.clientServices.DwmRestService;
import org.ortec.emulatortest.clientServices.SystemService;
import org.ortec.emulatortest.models.DwmServerInfo;
import org.ortec.emulatortest.models.RequestResultModel;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

        SystemService service = new SystemService();
        service.getServerInfo(this, msgHandler, 0);

    }

    RestServiceHandler msgHandler = new RestServiceHandler(this, Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            mProgressBar.setVisibility(View.GONE);
            mTextView.setVisibility(View.VISIBLE);
            super.handleMessage(msg);
            try {
                switch (msg.what) {
                    case 0: {
                        DwmServerInfo serverInfo = (DwmServerInfo) msg.obj;
                        mTextView.setText(String.format("Server version: %s", serverInfo.Version));
                    }
                    break;
                }
            } catch(Exception ex) {
                mTextView.setText(String.format("Exception: %s", ex.getMessage()));
            }
        }
    };


}
