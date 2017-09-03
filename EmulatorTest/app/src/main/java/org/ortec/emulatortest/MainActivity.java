package org.ortec.emulatortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void onClickToast(View v) {
        Toast.makeText(this, "Hallo Toast!", Toast.LENGTH_LONG).show();
    }

}
