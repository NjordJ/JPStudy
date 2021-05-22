package com.bigil.jpstudy.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.bigil.jpstudy.models.KanjiItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class JSONUtils {

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

    public void GetValuesFromKanjiJSON(Context context, ArrayList<KanjiItem> arrayList){
        //Def json parse
        try {
            Resources resources = context.getResources();
            JSONObject rootJson = new JSONObject(JsonDataFromAsset(context, "kanjiapi_obj.json"));
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
                //Image name from drawable folder
                String imageName = "kj_"+unicode;
                //Get resource by imageName from drawable
                final int resourceId = resources.getIdentifier(imageName, "drawable", context.getPackageName());

                if(Integer.valueOf(String.valueOf(1)).equals(grade)){
                    arrayList.add(new KanjiItem(kanji,grade,stroke_count, toStringArray(meanings),heisig_en, toStringArray(kun_readings),
                            toStringArray(on_readings), toStringArray(name_readings), jlpt,unicode, resourceId));
                }

            }

//            Collections.sort(arrayList, new Comparator<KanjiItem>() {
//                @Override
//                public int compare(KanjiItem o1, KanjiItem o2) {
//                    return Integer.compare(o1.getGrade(), o2.getGrade());
//                }
//            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
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
