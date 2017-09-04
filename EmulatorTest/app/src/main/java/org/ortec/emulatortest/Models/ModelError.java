package org.ortec.emulatortest.models;

/**
 * Created by treskow on 04.09.2017.
 */

import com.google.gson.annotations.SerializedName;

public class ModelError
{
	/*
    public ModelError()
    {
    }

    public ModelError(string key, string message)
    {
        Key = key;
        Message = message;
    }*/

    //[JsonProperty(PropertyName = "key")]
    @SerializedName("key")
    public String Key;

    //[JsonProperty(PropertyName = "message")]
    @SerializedName("message")
    public String Message;
}