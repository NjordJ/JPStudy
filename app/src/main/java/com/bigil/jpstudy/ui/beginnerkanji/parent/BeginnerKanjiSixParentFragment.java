package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;
import com.bigil.jpstudy.ui.beginnerkanji.tests.BeginnerKanjiTestsFragment;
import com.bigil.jpstudy.utils.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BeginnerKanjiSixParentFragment extends Fragment {

    //Classes
    JSONUtils jsonUtils = new JSONUtils();

    //Variables
    public ArrayList<KanjiItem> kanjiBeginnerItemArrayList = new ArrayList<>();

    private SharedPreferences pref;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewBeginnerKanji;
    private BeginnerKanjiAdapter mAdapterBeginnerKanji;
    private RecyclerView.LayoutManager mLayoutManagerBeginnerKanji;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_beginnerlistofkanjisix, container, false);

        TextView textViewCountOfKanjiCards = root.findViewById(R.id.textViewCurrentNumberBeginnerKanji);
        TextView textViewScoreBeginnerKanji1 = root.findViewById(R.id.textViewScoreBeginnerKanji1);
        Button buttonStartLearningBeginnerKanji = root.findViewById(R.id.buttonStartLearningBeginnerKanji);
        Integer countOfKanjiCards = 1;
        Resources resources = this.getResources();

        //Def json parse
        try {
            JSONObject rootJson = new JSONObject(jsonUtils.JsonDataFromAsset(getContext(), "kanjiapi_obj.json"));
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
                final int resourceId = resources.getIdentifier(imageName, "drawable", getContext().getPackageName());

                if(Integer.valueOf("6").equals(grade)){
                    kanjiBeginnerItemArrayList.add(new KanjiItem(kanji,grade,stroke_count, jsonUtils.toStringArray(meanings),heisig_en, jsonUtils.toStringArray(kun_readings),
                            jsonUtils.toStringArray(on_readings), jsonUtils.toStringArray(name_readings), jlpt,unicode, resourceId));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerViewBeginnerKanji = root.findViewById(R.id.recyclerViewBeginnerKanji);
        mRecyclerViewBeginnerKanji.setHasFixedSize(true);
        mLayoutManagerBeginnerKanji = new LinearLayoutManager(getContext());
        //mLayoutManagerBeginnerKanji = new GridLayoutManager(getActivity(),5);
        mAdapterBeginnerKanji = new BeginnerKanjiAdapter(kanjiBeginnerItemArrayList);

        mRecyclerViewBeginnerKanji.setLayoutManager(mLayoutManagerBeginnerKanji);
        mRecyclerViewBeginnerKanji.setAdapter(mAdapterBeginnerKanji);

        InitPreferences();

//        Integer defScoreValue = getResources().getInteger(R.string.saved_high_score_key);
//        Integer highScore = pref.getInt(getString(R.string.saved_high_score_key), defScoreValue);
//
//        if(highScore >= defScoreValue){
//            textViewScoreBeginnerKanji1.setText(String.valueOf(highScore));
//        }else{
//            textViewScoreBeginnerKanji1.setText(String.valueOf(defScoreValue));
//        }

        //textViewScoreBeginnerKanji1.setText((pref.getString(getString(R.string.saved_high_score_key), getString(R.string.saved_high_score_default_key)))+"%");

        mAdapterBeginnerKanji.setOnItemClickListener(new BeginnerKanjiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiInfo = new BeginnerKanjiInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", kanjiBeginnerItemArrayList.get(position));
                fragmentBeginnerKanjiInfo.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiInfo)
                        .addToBackStack(null)
                        .commit();

            }

            @Override
            public void onStartStudyClick(int position) {

            }
        });

        buttonStartLearningBeginnerKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiTestsFragment = new BeginnerKanjiTestsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("KanjiItemDataTests", kanjiBeginnerItemArrayList);
                fragmentBeginnerKanjiTestsFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiTestsFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });


        return root;
    }

    public void InitPreferences(){
        pref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

}

