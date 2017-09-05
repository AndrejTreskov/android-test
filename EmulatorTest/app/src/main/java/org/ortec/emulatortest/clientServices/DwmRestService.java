package org.ortec.emulatortest.clientServices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.ortec.emulatortest.models.DwmServerInfo;
import org.ortec.emulatortest.models.RequestResultModel;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by treskow on 04.09.2017.
 */

public class DwmRestService {
    public static String getServerUrl() {
        return "http://system.wine-trophy.com:51572";
    }

    public static void getData(final Context ctx, final String serviceUrl, final Type type, final Handler resultHandler, final int resultId) {

        //Internetverbindung Test
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo==null || !networkInfo.isConnectedOrConnecting()) {
            Message msg = resultHandler.obtainMessage(-3);
            msg.sendToTarget();
            return;
        }
        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {




                    URL githubEndpoint = new URL(String.format("%s/%s",getServerUrl(), serviceUrl));
                    // Create connection
                    HttpURLConnection myConnection =
                            (HttpURLConnection) githubEndpoint.openConnection();
                    myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
                    myConnection.setRequestProperty("content-type","application/json; charset=utf-8");
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        String json = convertStreamToString(responseBody);

                        //Type fooType = new TypeToken<RequestResultModel<DwmServerInfo>>() {}.getType();
                        Gson gson = new Gson();
                        RequestResultModel<DwmServerInfo> result = gson.fromJson(json, type);


                        Message msg = resultHandler.obtainMessage(resultId, result.Value);
                        msg.sendToTarget();
                    } else {
                        Message msg = resultHandler.obtainMessage(-2, "wrong response");
                        msg.sendToTarget();
                    }
                    myConnection.disconnect();
                } catch(Exception ex) {
                    Message msg = resultHandler.obtainMessage(-1, ex.getMessage());
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
}
