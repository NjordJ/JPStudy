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

    private ProgressDialog progressDialog;
    public ArrayList<KanjiItem> kanjiItemArrayList = new ArrayList<>();

    Gson gson = new Gson();

    //Parse json
    public ArrayList<KanjiItem> ParseJsonWithGradeAndJlpt(ArrayList<KanjiItem> arrayList, String[] gradeClause, String[] jlptClause){
        try {
            JSONObject rootJson = new JSONObject(JsonData("kanjiapi_obj.json"));
            JSONObject jsonObjectKanjis = rootJson.getJSONObject("kanjis");

            JSONArray jArray = jsonObjectKanjis.names();
            int len = jsonObjectKanjis.length();

            for (int i=0; i<len; i++) {
                String keyName = (String)jArray.get(i);
                JSONObject jValue = jsonObjectKanjis.getJSONObject(keyName);
                String kanji = jValue.optString("kanji");
                Integer grade = jValue.optInt("grade");
                Integer stroke_count = jValue.optInt("stroke_count");
                JSONArray meanings = jValue.getJSONArray("meanings");
                JSONArray kun_readings = jValue.getJSONArray("kun_readings");
                JSONArray on_readings = jValue.getJSONArray("on_readings");
                JSONArray name_readings = jValue.getJSONArray("name_readings");
                Integer jlpt = jValue.optInt("jlpt");
                String unicode = jValue.optString("unicode");
                String heisig_en = jValue.optString("heisig_en");

                String gradeCl = TextUtils.join(",", gradeClause);
                String jlptCl = TextUtils.join(",", jlptClause);

                if(Integer.valueOf(gradeCl).equals(grade) && Integer.valueOf(jlptCl).equals(jlpt)){
                    arrayList.add(new KanjiItem(kanji,grade,stroke_count,toStringArray(meanings),heisig_en,toStringArray(kun_readings),
                            toStringArray(on_readings),toStringArray(name_readings), jlpt,unicode));
                }

            }

            Collections.sort(arrayList, new Comparator<KanjiItem>() {
                @Override
                public int compare(KanjiItem o1, KanjiItem o2) {
                    return Integer.compare(o1.getGrade(), o2.getGrade());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String JsonData(String jsonName) {
        AssetManager manager = null;
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
