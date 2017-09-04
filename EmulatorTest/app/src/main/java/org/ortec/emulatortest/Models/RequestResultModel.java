package org.ortec.emulatortest.Models;

/**
 * Created by treskow on 04.09.2017.
 */

import com.google.gson.annotations.SerializedName;

public class RequestResultModel<T>
{
    @SerializedName("success")
    public Boolean success;

    @SerializedName("value")
    public T Value;

    @SerializedName("status")
    public int Status;

    @SerializedName("errorMessage")
    public String ErrorMessage;

    @SerializedName("modelErrors")
    public ModelError[] ModelErrors;

}