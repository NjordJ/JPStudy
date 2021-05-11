package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;
import com.bigil.jpstudy.ui.beginnerkanji.tests.BeginnerKanjiTestsFragment;
import com.bigil.jpstudy.utils.JSONParsingAsync;
import com.bigil.jpstudy.utils.KanjiStudyTests;
import com.bigil.jpstudy.utils.LoadNewLayout;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

public class BeginnerKanjiParentFragment extends Fragment {

    private BeginnerKanjiParentViewModel beginnerKanjiParentViewModel;

    //Classes
    JSONParsingAsync jsonParsingAsync  = new JSONParsingAsync();
    KanjiStudyTests kanjiStudyTests = new KanjiStudyTests();
    Gson gson = new Gson();
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
        beginnerKanjiParentViewModel =
                ViewModelProviders.of(this).get(BeginnerKanjiParentViewModel.class);
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
                Integer stroke_count = jValue.getInt("stroke_count");
                JSONArray meanings = jValue.getJSONArray("meanings");
                JSONArray kun_readings = jValue.getJSONArray("kun_readings");
                JSONArray on_readings = jValue.getJSONArray("on_readings");
                JSONArray name_readings = jValue.getJSONArray("name_readings");
//                Integer jlpt = jValue.getInt("jlpt");
                String unicode = jValue.getString("unicode");
                String heisig_en = jValue.getString("heisig_en");

                kanjiBeginnerItemArrayList.add(new KanjiItem(kanji,grade,stroke_count,jsonParsingAsync.toStringArray(meanings),heisig_en,jsonParsingAsync.toStringArray(kun_readings),
                        jsonParsingAsync.toStringArray(on_readings),jsonParsingAsync.toStringArray(name_readings), null,unicode));
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

        mAdapterBeginnerKanji.setOnItemClickListener(new BeginnerKanjiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                //using Bundle to send data
                  Fragment fragmentBeginnerKanjiInfo = new BeginnerKanjiInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", kanjiBeginnerItemArrayList.get(position));
                fragmentBeginnerKanjiInfo.setArguments(bundle);

                //loadNewLayout.LoadNewFragmentWithArrayList(getActivity(),"KanjiItemData", kanjiBeginnerItemArrayList.get(position), fragmentBeginnerKanjiInfo);

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiInfo, null)
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

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiTestsFragment, null)
                        .addToBackStack(null)
                        .commit();

                //Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }
        });

        buttonStartLearningBeginnerKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //using Bundle to send data
                Fragment fragmentBeginnerKanjiTestsFragment = new BeginnerKanjiTestsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("KanjiItemData", (Parcelable) kanjiBeginnerItemArrayList);
                fragmentBeginnerKanjiTestsFragment.setArguments(bundle);

                //loadNewLayout.LoadNewFragmentWithArrayList(getActivity().getApplicationContext(),"KanjiItemData", kanjiBeginnerItemArrayList.get(position), fragmentBeginnerKanjiInfo);

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentBeginnerKanjiTestsFragment, null)
                        .addToBackStack(null)
                        .commit();

                //Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }
        });


        return root;
    }

}

