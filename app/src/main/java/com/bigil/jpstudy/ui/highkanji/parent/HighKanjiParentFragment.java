package com.bigil.jpstudy.ui.highkanji.parent;

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
import com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiAdapter;
import com.bigil.jpstudy.ui.beginnerkanji.tests.BeginnerKanjiTestsFragment;
import com.bigil.jpstudy.utils.JSONUtils;
import com.bigil.jpstudy.utils.LoadNewLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighKanjiParentFragment extends Fragment {

    //Classes
    JSONUtils jsonUtils = new JSONUtils();
    LoadNewLayout loadNewLayout = new LoadNewLayout();

    //Variables
    public ArrayList<KanjiItem> kanjiHighItemArrayList = new ArrayList<>();

    //RecyclerView for show information
    private RecyclerView mRecyclerViewHighKanji;
    private BeginnerKanjiAdapter mAdapterHighKanji;
    private RecyclerView.LayoutManager mLayoutManagerHighKanji;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_highlistofkanji, container, false);

        TextView textViewCountOfKanjiCards = root.findViewById(R.id.textViewCurrentNumberBeginnerKanji);
        TextView textViewScoreBeginnerKanji1 = root.findViewById(R.id.textViewScoreBeginnerKanji1);
        Button buttonStartLearningHighKanji = root.findViewById(R.id.buttonStartLearningHighKanji);
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

                if(Integer.valueOf("9").equals(grade)){
                    kanjiHighItemArrayList.add(new KanjiItem(kanji,grade,stroke_count, jsonUtils.toStringArray(meanings),heisig_en, jsonUtils.toStringArray(kun_readings),
                            jsonUtils.toStringArray(on_readings), jsonUtils.toStringArray(name_readings), jlpt,unicode, resourceId));
                }

            }

            Collections.sort(kanjiHighItemArrayList, new Comparator<KanjiItem>() {
                @Override
                public int compare(KanjiItem o1, KanjiItem o2) {
                    return Integer.compare(o1.getGrade(), o2.getGrade());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mRecyclerViewHighKanji = root.findViewById(R.id.recyclerViewHighKanji);
        mRecyclerViewHighKanji.setHasFixedSize(true);
        mLayoutManagerHighKanji = new LinearLayoutManager(getContext());
        //mLayoutManagerBeginnerKanji = new GridLayoutManager(getActivity(),5);
        mAdapterHighKanji = new BeginnerKanjiAdapter(kanjiHighItemArrayList);

        mRecyclerViewHighKanji.setLayoutManager(mLayoutManagerHighKanji);
        mRecyclerViewHighKanji.setAdapter(mAdapterHighKanji);

        mAdapterHighKanji.setOnItemClickListener(new BeginnerKanjiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiInfo = new BeginnerKanjiInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", kanjiHighItemArrayList.get(position));
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

        buttonStartLearningHighKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiTestsFragment = new BeginnerKanjiTestsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("KanjiItemDataTests", kanjiHighItemArrayList);
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
}