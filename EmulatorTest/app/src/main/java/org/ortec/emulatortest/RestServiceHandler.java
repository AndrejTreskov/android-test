package org.ortec.emulatortest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import org.ortec.emulatortest.models.DwmServerInfo;

import java.lang.ref.WeakReference;

/**
 * Created by treskow on 05.09.2017.
 */

public class RestServiceHandler extends Handler {

    // A weak reference to the enclosing context
    private WeakReference<Context> mContext;

    public RestServiceHandler (Context context, Looper looper){
        super(looper);
        mContext = new WeakReference<Context>(context);
    }

    @Override
    public void handleMessage(Message msg) {

        // Get an actual reference to the DownloadActivity
        // from the WeakReference.
        Context context=mContext.get();
        try {
            switch (msg.what) {
                case -1:
                    Toast.makeText(context, String.format("Exception: %s", (String) msg.obj), Toast.LENGTH_LONG).show();
                    break;
                case -2:
                    Toast.makeText(context, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
                case -3:
                    Toast.makeText(context, "keine Internetverbindung", Toast.LENGTH_LONG).show();
                    break;
            }
        } catch(Exception ex) {
            Toast.makeText(context, String.format("Exception: %s", ex.getMessage()), Toast.LENGTH_LONG).show();
        }
    }
}
