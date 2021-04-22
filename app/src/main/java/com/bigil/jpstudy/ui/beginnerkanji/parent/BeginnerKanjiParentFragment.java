package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;
import com.bigil.jpstudy.ui.beginnerkanji.tests.BeginnerKanjiTestsFragment;
import com.bigil.jpstudy.utils.JSONParsingAsync;
import com.bigil.jpstudy.utils.KanjiStudyTests;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;

public class BeginnerKanjiParentFragment extends Fragment {
    public static final String EXTRA_KANJI = "kanji";
    public static final String EXTRA_GRADE = "grade";
    public static final String EXTRA_STROKECOUNT = "strokeCount";
    public static final String EXTRA_MEANINGS = "meanings";
    public static final String EXTRA_HEISIGEN = "heisigEn";
    public static final String EXTRA_KUNREADINGS = "kunReadings";
    public static final String EXTRA_ONREADINGS = "onReadings";
    public static final String EXTRA_NAMEREADINGS = "nameReadings";
    public static final String EXTRA_JLPT = "jlpt";
    public static final String EXTRA_UNICODE = "unicode";

    private BeginnerKanjiParentViewModel beginnerKanjiParentViewModel;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewBeginnerKanji;
    private BeginnerKanjiAdapter mAdapterBeginnerKanji;
    private RecyclerView.LayoutManager mLayoutManagerBeginnerKanji;

    //Items
    public ArrayList<KanjiItem> kanjiBeginnerItemArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        beginnerKanjiParentViewModel =
                ViewModelProviders.of(this).get(BeginnerKanjiParentViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_beginnerlistofkanji, container, false);

        TextView textViewCountOfKanjiCards = root.findViewById(R.id.textViewCurrentNumberBeginnerKanji);
        Button buttonStartLearningBeginnerKanji = root.findViewById(R.id.buttonStartLearningBeginnerKanji);
        Integer countOfKanjiCards = 1;

        //Implement classes
        JSONParsingAsync jsonParsingAsync  = new JSONParsingAsync();
        KanjiStudyTests kanjiStudyTests = new KanjiStudyTests();
        Gson gson = new Gson();

        //Def json parse
        try {
            JSONObject rootJson = new JSONObject(jsonParsingAsync.JsonDataFromAsset(getContext(), "kanjiapi_obj.json"));
            JSONObject jsonObjectKanjis = rootJson.getJSONObject("kanjis");

            Iterator keyNames = jsonObjectKanjis.keys();
            while(keyNames.hasNext()){
                String keyname = (String) keyNames.next();

                JSONObject kanjiData = jsonObjectKanjis.getJSONObject(keyname);
                String kanji = kanjiData.getString("kanji");
//                Integer grade = kanjiData.getInt("grade");
//                Integer stroke_count = kanjiData.getInt("stroke_count");
//                JSONArray meanings = kanjiData.getJSONArray("meanings");
//                String heisig_en = kanjiData.getString("heisig_en");
//                JSONArray kun_readings = kanjiData.getJSONArray("kun_readings");
//                JSONArray on_readings = kanjiData.getJSONArray("on_readings");
//                JSONArray name_readings = kanjiData.getJSONArray("name_readings");
//                Integer jlpt = kanjiData.getInt("jlpt");
//                String unicode = kanjiData.getString("unicode");

                //textViewCountOfKanjiCards.setText(countOfKanjiCards);

//                kanjiBeginnerItemArrayList.add(new KanjiItem(kanji,grade,stroke_count,null,null,null,
//                        null,null,jlpt,unicode));

                kanjiBeginnerItemArrayList.add(new KanjiItem(kanji,null,null,null,null,null,
                        null,null,null,null));

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
                /*BeginnerKanjiInfoFragment beginnerKanjiInfoFragment = new BeginnerKanjiInfoFragment();
                Bundle args = new Bundle();
                args.putSerializable("kanjiItemData", (Serializable)kanjiBeginnerItemArrayList.get(position));
                beginnerKanjiInfoFragment.setArguments(args);*/

                //using Bundle to send data
                /*Bundle bundle=new Bundle();
                bundle.putString("email",memail);
                bundle.putString("user_name",muser_name);
                bundle.putString("phone",mphone_number);
                mfragment.setArguments(bundle); //data being send to SecondFragment
                transection.replace(R.id.main_fragment, mfragment);
                transection.commit();*/

//                Intent kanjiInfoIntent = new Intent(getContext(), BeginnerKanjiInfoFragment.class);
//                KanjiItem clickedItem = kanjiBeginnerItemArrayList.get(position);

//                kanjiInfoIntent.putExtra(EXTRA_KANJI, clickedItem.getKanji());
//                kanjiInfoIntent.putExtra(EXTRA_GRADE, clickedItem.getGrade());
//                kanjiInfoIntent.putExtra(EXTRA_STROKECOUNT, clickedItem.getStrokeCount());
//                kanjiInfoIntent.putExtra(EXTRA_MEANINGS, clickedItem.getMeanings());
//                kanjiInfoIntent.putExtra(EXTRA_HEISIGEN, clickedItem.getHeisig_en());
//                kanjiInfoIntent.putExtra(EXTRA_KUNREADINGS, clickedItem.getKunyomiReading());
//                kanjiInfoIntent.putExtra(EXTRA_ONREADINGS, clickedItem.getOnyomiReading());
//                kanjiInfoIntent.putExtra(EXTRA_NAMEREADINGS, clickedItem.getNameReadings());
//                kanjiInfoIntent.putExtra(EXTRA_JLPT, clickedItem.getJlpt());
//                kanjiInfoIntent.putExtra(EXTRA_UNICODE, clickedItem.getUnicodeKanji());

//                startActivity(kanjiInfoIntent);

                Toast.makeText(getActivity(), "Successful click", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStartStudyClick(int position) {

            }
        });

        buttonStartLearningBeginnerKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openKanjiTestsFragment();
                //kanjiStudyTests.ChooseAllBeginnerKanji(kanjiBeginnerItemArrayList);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new BeginnerKanjiTestsFragment());
                fragmentTransaction.addToBackStack(null);
                //Toast.makeText(getActivity(), "toast sucessuful", Toast.LENGTH_LONG).show();
                fragmentTransaction.commit();

            }
        });


        return root;
    }

}

