package com.bigil.jpstudy.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import com.bigil.jpstudy.models.KanjiItem;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONParsingAsync {

    private ProgressDialog progressDialog;
    public ArrayList<KanjiItem> kanjiItemArrayList = new ArrayList<>();

    Gson gson = new Gson();

    /*class JSONParseAsync extends AsyncTask<String, ArrayList<KanjiItem>, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            return "";
        }

        protected ArrayList<KanjiItem> onPostExecute(ArrayList<KanjiItem> kj) {
            super.onPostExecute(String.valueOf(kj));

            try {
                JSONObject rootJson = new JSONObject(JsonDataFromAsset(,"kanjiapi_obj.json"));
                JSONObject jsonObjectKanjis = rootJson.getJSONObject("kanjis");

                Iterator keyNames = jsonObjectKanjis.keys();
                while(keyNames.hasNext()){
                    String keyname = (String) keyNames.next();

                    JSONObject kanjiData = jsonObjectKanjis.getJSONObject(keyname);
                    String kanji = kanjiData.getString("kanji");

                    kanjiItemArrayList.add(new KanjiItem(kanji,null,null,null,null,null,
                            null,null,null,null));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return kanjiItemArrayList;

        }
    }*/


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
