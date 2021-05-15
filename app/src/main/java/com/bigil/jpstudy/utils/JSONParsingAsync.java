package com.bigil.jpstudy.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.bigil.jpstudy.models.KanjiItem;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JSONParsingAsync {

    //Parsing json file
    public String JsonDataFromAsset(Context context, String jsonName) {
        AssetManager manager = context.getAssets();
        String json = null;
        InputStream inputStream = null;
        try {
            inputStream = manager.open(jsonName);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public String[] toStringArray(JSONArray array) {
        if (array == null)
            return null;

        String[] arr = new String[array.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array.optString(i);
        }
        return arr;

    }

}
