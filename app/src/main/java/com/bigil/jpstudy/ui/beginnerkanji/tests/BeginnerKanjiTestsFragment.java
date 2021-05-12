package com.bigil.jpstudy.ui.beginnerkanji.tests;

import android.content.Intent;
import android.os.Parcel;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiParentFragment;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class BeginnerKanjiTestsFragment extends Fragment {

    //Classes
    private KanjiItem kanjiItem;

    //Variables
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_beginnerkanji_tests, container, false);

        //kanjiItem = new KanjiItem(Parcel.obtain);

//        Bundle bundle = this.getArguments();
//
//        //Get values from behind fragment
//        if (bundle != null) {
//            kanjiItem = (KanjiItem) bundle.getSerializable("KanjiItemDataTests");
//        }

        String kanjiValue = kanjiItem.getKanji();
        String[] kun_readingsValue = kanjiItem.getKunyomiReading();
        String[] on_readingsValue = kanjiItem.getOnyomiReading();

        //Convert String[] to single String
        String kun_readings = TextUtils.join(",", kun_readingsValue);
        String on_readings = TextUtils.join(",", on_readingsValue);



        //Find variables from layout
//        VideoView videoViewBeginnerKanjiInfo = root.findViewById(R.id.videoViewBeginnerKanjiInfoHowToStroke);
        TextView textViewBeginnerKanjiTestsKanji = root.findViewById(R.id.textViewBeginnerKanjiTestsKanjis);
        TextView textViewBeginnerKanjiTestsKunyomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsOnReadings);
        TextView textViewBeginnerKanjiTestsOnyomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsKunReadings);
        ListView listViewBeginnerKanjiTestsRandomKanjis = root.findViewById(R.id.listViewBeginnerKanjiTestsRandomKanjis);

        //Set values to TextViews
//        textViewBeginnerKanjiTestsKanji.setText(kanjiValue);
//        textViewBeginnerKanjiTestsKunyomiValue.setText(kun_readings);
//        textViewBeginnerKanjiTestsOnyomiValue.setText(on_readings);

        return root;
    }


}