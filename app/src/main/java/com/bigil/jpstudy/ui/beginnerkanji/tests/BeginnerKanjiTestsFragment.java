package com.bigil.jpstudy.ui.beginnerkanji.tests;

import android.content.Intent;
import android.os.Parcel;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
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

public class BeginnerKanjiTestsFragment extends Fragment implements View.OnClickListener {

    //Classes
    private ArrayList<KanjiItem> arrayListKanjiItem;

    //Variables
    private ArrayList<String> arrayListTempKanji;
    private ArrayList<KanjiItem> arrayListAnswerKanjiItem;
    private String kanjiValue;
    private String[] kun_readingsValue;
    private String[] on_readingsValue;
    private String kun_readings;
    private String on_readings;
    private Integer correctAnswer;
    private Integer wrongAnswer;
    private Integer currentAnswer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_beginnerkanji_tests, container, false);

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            arrayListKanjiItem = bundle.getParcelableArrayList("KanjiItemDataTests");
        }

        for (int i = 0; i < arrayListKanjiItem.size(); i++) {
            kanjiValue = arrayListKanjiItem.get(i).getKanji();
            kun_readingsValue = arrayListKanjiItem.get(i).getKunyomiReading();
            on_readingsValue = arrayListKanjiItem.get(i).getOnyomiReading();

            //Convert String[] to single String
            kun_readings = TextUtils.join(",", kun_readingsValue);
            on_readings = TextUtils.join(",", on_readingsValue);

        }

        //Find variables from layout
        CardView cardViewBeginnerKanjiTestsAnswer1 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer1);
        CardView cardViewBeginnerKanjiTestsAnswer2 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer2);
        CardView cardViewBeginnerKanjiTestsAnswer3 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer3);
        CardView cardViewBeginnerKanjiTestsAnswer4 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer4);
        CardView cardViewBeginnerKanjiTestsAnswer5 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer5);
        CardView cardViewBeginnerKanjiTestsAnswer6 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer6);

        TextView textViewBeginnerKanjiTestsOnyomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsOnReadings);
        TextView textViewBeginnerKanjiTestsKunuomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsKunReadings);
        TextView textViewBeginnerKanjiTestsCurrentAnswer = root.findViewById(R.id.textViewBeginnerKanjiTestsCurrentAnswer);
        TextView textViewBeginnerKanjiTestsAnswer1 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer1);
        TextView textViewBeginnerKanjiTestsAnswer2 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer2);
        TextView textViewBeginnerKanjiTestsAnswer3 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer3);
        TextView textViewBeginnerKanjiTestsAnswer4 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer4);
        TextView textViewBeginnerKanjiTestsAnswer5 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer5);
        TextView textViewBeginnerKanjiTestsAnswer6 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer6);

        //Set values to TextViews
        //textViewBeginnerKanjiTestsAnswer1.setText(kanjiValue);
        textViewBeginnerKanjiTestsKunuomiValue.setText(kun_readings);
        textViewBeginnerKanjiTestsOnyomiValue.setText(on_readings);

        cardViewBeginnerKanjiTestsAnswer1.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer2.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer3.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer4.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer5.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer6.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cardViewBeginnerKanjiTestsAnswer1:
            case R.id.cardViewBeginnerKanjiTestsAnswer2:
            case R.id.cardViewBeginnerKanjiTestsAnswer3:
            case R.id.cardViewBeginnerKanjiTestsAnswer4:
            case R.id.cardViewBeginnerKanjiTestsAnswer5:
            case R.id.cardViewBeginnerKanjiTestsAnswer6:
                System.out.println("SUCCESSFUL CLICK");
                break;

        }
    }
}