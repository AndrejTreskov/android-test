package org.ortec.emulatortest.clientServices;

import android.os.Handler;

import com.google.gson.reflect.TypeToken;

import org.ortec.emulatortest.models.DwmServerInfo;
import org.ortec.emulatortest.models.RequestResultModel;

import java.lang.reflect.Type;

/**
 * Created by treskow on 04.09.2017.
 */

public class SystemService extends DwmRestService {
    public DwmServerInfo getServerInfo(Handler resultHandler, int resultId) {
        String serverUrl = "api/system/serverInfo";
        Type fooType = new TypeToken<RequestResultModel<DwmServerInfo>>() {}.getType();
        getData(serverUrl, fooType, resultHandler, resultId);
        return null;
    }
}
