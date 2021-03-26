package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.icu.text.Edits;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.kana.KanaAdapter;
import com.bigil.jpstudy.ui.kana.KanaItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class BeginnerKanjiParentFragment extends Fragment {

    private BeginnerKanjiParentViewModel beginnerKanjiParentViewModel;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewBeginnerKanji;
    private RecyclerView.Adapter mAdapterBeginnerKanji;
    private RecyclerView.LayoutManager mLayoutManagerBeginnerKanji;

    //Json lists
    ArrayList<String> beginnerKanjiList = new ArrayList<>();
    ArrayList<String> onyomiList = new ArrayList<>();
    ArrayList<String> kunyomiList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        beginnerKanjiParentViewModel =
                ViewModelProviders.of(this).get(BeginnerKanjiParentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_beginnerlistofkanji, container, false);
        final //TextView textView = root.findViewById(R.id.text_home);

        ArrayList<BeginnerKanjiItem> beginnerKanjiItemArrayList = new ArrayList<>();

        try {
            JSONObject rootJson = new JSONObject(JsonDataFromAsset());
            JSONObject jsonObjectKanjis = rootJson.getJSONObject("kanjis");

            Iterator keyNames = jsonObjectKanjis.keys();
            while(keyNames.hasNext()){
                String keyname = (String) keyNames.next();

                JSONObject kanjiData = jsonObjectKanjis.getJSONObject(keyname);
                String kanji = kanjiData.getString("kanji");
//                Integer grade = kanjiData.getInt("grade");
//                Integer jlpt = kanjiData.getInt("jlpt");


                    beginnerKanjiItemArrayList.add(new BeginnerKanjiItem(kanji,null,null,null,null,null,
                            null,null,null,null));





            }



            /*for (int index = 0,  indexCurKanjiObj = 0; index < jsonArray.length(); index++){
                JSONObject kanjiDataTitle = jsonArray.getJSONObject(indexCurKanjiObj);
                JSONArray kanjiData = kanjiDataTitle.getJSONArray(index);

                String kanji = kanjiData.getString("kanji");


                *//*beginnerKanjiItemArrayList.add(new BeginnerKanjiItem("",null,null,null,null,null,
                        null,null,null,null));*//*

                beginnerKanjiItemArrayList.add(new BeginnerKanjiItem(kanji,null,null,null,null,null,
                                                                    null,null,null,null));
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerViewBeginnerKanji = root.findViewById(R.id.recyclerViewBeginnerKanji);
        mRecyclerViewBeginnerKanji.setHasFixedSize(true);
        mLayoutManagerBeginnerKanji = new LinearLayoutManager(getContext());
        //mLayoutManagerBeginnerKanji = new GridLayoutManager(getActivity(),5);
        mAdapterBeginnerKanji = new BeginnerKanjiAdapter(beginnerKanjiItemArrayList);

        mRecyclerViewBeginnerKanji.setLayoutManager(mLayoutManagerBeginnerKanji);
        mRecyclerViewBeginnerKanji.setAdapter(mAdapterBeginnerKanji);

        /*beginnerKanjiParentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        return root;
    }


    //Parsing json file
    private String JsonDataFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("kanjiapi_obj.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}