package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;
import com.bigil.jpstudy.ui.beginnerkanji.tests.BeginnerKanjiTestsFragment;
import com.bigil.jpstudy.utils.JSONParsingAsync;
import com.bigil.jpstudy.utils.LoadNewLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

public class BeginnerKanjiParentFragment extends Fragment {

    //Classes
    JSONParsingAsync jsonParsingAsync  = new JSONParsingAsync();
    LoadNewLayout loadNewLayout = new LoadNewLayout();

    //Variables
    public ArrayList<KanjiItem> kanjiBeginnerItemArrayList = new ArrayList<>();
    public ArrayList<KanjiItem> kanjiBeginnerItems = new ArrayList<>();

    //RecyclerView for show information
    private RecyclerView mRecyclerViewBeginnerKanji;
    private BeginnerKanjiAdapter mAdapterBeginnerKanji;
    private RecyclerView.LayoutManager mLayoutManagerBeginnerKanji;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_beginnerlistofkanji, container, false);

        TextView textViewCountOfKanjiCards = root.findViewById(R.id.textViewCurrentNumberBeginnerKanji);
        Button buttonStartLearningBeginnerKanji = root.findViewById(R.id.buttonStartLearningBeginnerKanji);
        Integer countOfKanjiCards = 1;

        //Def json parse
        try {
            JSONObject rootJson = new JSONObject(jsonParsingAsync.JsonDataFromAsset(getContext(), "kanjiapi_obj.json"));
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

                if(Integer.valueOf("1").equals(grade)){
                    kanjiBeginnerItemArrayList.add(new KanjiItem(kanji,grade,stroke_count,jsonParsingAsync.toStringArray(meanings),heisig_en,jsonParsingAsync.toStringArray(kun_readings),
                            jsonParsingAsync.toStringArray(on_readings),jsonParsingAsync.toStringArray(name_readings), jlpt,unicode));
                }

            }

            Collections.sort(kanjiBeginnerItemArrayList, new Comparator<KanjiItem>() {
                @Override
                public int compare(KanjiItem o1, KanjiItem o2) {
                    return Integer.compare(o1.getGrade(), o2.getGrade());
                }
            });

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

        mAdapterBeginnerKanji.setOnItemClickListener(new BeginnerKanjiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiInfo = new BeginnerKanjiInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", kanjiBeginnerItemArrayList.get(position));
                fragmentBeginnerKanjiInfo.setArguments(bundle);

//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment, fragmentBeginnerKanjiInfo);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiInfo)
                        .addToBackStack(null)
                        .commit();

                //Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStartStudyClick(int position) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiTestsFragment = new BeginnerKanjiTestsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", kanjiBeginnerItemArrayList.get(position));
                fragmentBeginnerKanjiTestsFragment.setArguments(bundle);

                //loadNewLayout.LoadNewFragmentWithArrayList(getActivity().getApplicationContext(),"KanjiItemData", kanjiBeginnerItemArrayList.get(position), fragmentBeginnerKanjiInfo);

//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiTestsFragment)
//                        .addToBackStack(null)
//                        .commit();


                //Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }
        });

        buttonStartLearningBeginnerKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiTestsFragment = new BeginnerKanjiTestsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("KanjiItemDataTests", kanjiBeginnerItemArrayList);
//                fragmentBeginnerKanjiTestsFragment.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiTestsFragment)
                        .addToBackStack(null)
                        .commit();

                //Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }
        });


        return root;
    }

}

