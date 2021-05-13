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
import java.util.*;

public class BeginnerKanjiTestsFragment extends Fragment implements View.OnClickListener {

    //Classes

    //From layout
    CardView cardViewBeginnerKanjiTestsAnswer1;
    CardView cardViewBeginnerKanjiTestsAnswer2;
    CardView cardViewBeginnerKanjiTestsAnswer3;
    CardView cardViewBeginnerKanjiTestsAnswer4;
    CardView cardViewBeginnerKanjiTestsAnswer5;
    CardView cardViewBeginnerKanjiTestsAnswer6;

    TextView textViewBeginnerKanjiTestsAnswer1;
    TextView textViewBeginnerKanjiTestsAnswer2;
    TextView textViewBeginnerKanjiTestsAnswer3;
    TextView textViewBeginnerKanjiTestsAnswer4;
    TextView textViewBeginnerKanjiTestsAnswer5;
    TextView textViewBeginnerKanjiTestsAnswer6;

    TextView textViewBeginnerKanjiTestsOnyomiValue;
    TextView textViewBeginnerKanjiTestsKunuomiValue;
    TextView textViewBeginnerKanjiTestsCurrentAnswer;

    //Variables
    private ArrayList<KanjiItem> arrayListKanjiItem;
    private List<KanjiItem> tempKanji;
    private ArrayList<KanjiItem> arrayListAnswerKanjiItem;
    private String kanjiValue;
    private String kun_readings;
    private String on_readings;
    private String[] kun_readingsValue;
    private String[] on_readingsValue;
    private Integer correctAnswer;
    private Integer wrongAnswer;
    private Integer currentQuestion = 0;

    private Random r = new Random();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_beginnerkanji_tests, container, false);

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            arrayListKanjiItem = bundle.getParcelableArrayList("KanjiItemDataTests");
        }

        //Randomize elements
        Collections.shuffle(arrayListKanjiItem);

        //Collect n unique elements
        tempKanji = arrayListKanjiItem.subList(0,6);

        //Find variables from layout
        cardViewBeginnerKanjiTestsAnswer1 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer1);
        cardViewBeginnerKanjiTestsAnswer2 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer2);
        cardViewBeginnerKanjiTestsAnswer3 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer3);
        cardViewBeginnerKanjiTestsAnswer4 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer4);
        cardViewBeginnerKanjiTestsAnswer5 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer5);
        cardViewBeginnerKanjiTestsAnswer6 = root.findViewById(R.id.cardViewBeginnerKanjiTestsAnswer6);

        textViewBeginnerKanjiTestsOnyomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsOnReadings);
        textViewBeginnerKanjiTestsKunuomiValue = root.findViewById(R.id.textViewBeginnerKanjiTestsKunReadings);
        textViewBeginnerKanjiTestsCurrentAnswer = root.findViewById(R.id.textViewBeginnerKanjiTestsCurrentAnswer);
        textViewBeginnerKanjiTestsAnswer1 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer1);
        textViewBeginnerKanjiTestsAnswer2 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer2);
        textViewBeginnerKanjiTestsAnswer3 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer3);
        textViewBeginnerKanjiTestsAnswer4 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer4);
        textViewBeginnerKanjiTestsAnswer5 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer5);
        textViewBeginnerKanjiTestsAnswer6 = root.findViewById(R.id.textViewBeginnerKanjiTestsAnswer6);

        setTextsScreen(currentQuestion);

        cardViewBeginnerKanjiTestsAnswer1.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer2.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer3.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer4.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer5.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer6.setOnClickListener(this);

        return root;
    }

    private void setTextsScreen(Integer number){
        kanjiValue = arrayListKanjiItem.get(number).getKanji();
        kun_readingsValue = arrayListKanjiItem.get(number).getKunyomiReading();
        on_readingsValue = arrayListKanjiItem.get(number).getOnyomiReading();

        String kanji1 = tempKanji.get(0).getKanji();
        String kanji2 = tempKanji.get(1).getKanji();
        String kanji3 = tempKanji.get(2).getKanji();
        String kanji4 = tempKanji.get(3).getKanji();
        String kanji5 = tempKanji.get(4).getKanji();
        String kanji6 = tempKanji.get(5).getKanji();

        kun_readings = TextUtils.join(",", kun_readingsValue);
        on_readings = TextUtils.join(",", on_readingsValue);

        textViewBeginnerKanjiTestsKunuomiValue.setText(kun_readings);
        textViewBeginnerKanjiTestsOnyomiValue.setText(on_readings);
        textViewBeginnerKanjiTestsCurrentAnswer.setText((currentQuestion+1)+"/"+ arrayListKanjiItem.size());
        textViewBeginnerKanjiTestsAnswer1.setText(kanji1);
        textViewBeginnerKanjiTestsAnswer2.setText(kanji2);
        textViewBeginnerKanjiTestsAnswer3.setText(kanji3);
        textViewBeginnerKanjiTestsAnswer4.setText(kanji4);
        textViewBeginnerKanjiTestsAnswer5.setText(kanji5);
        textViewBeginnerKanjiTestsAnswer6.setText(kanji6);
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
                //Check if answer correct
                if(arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(0).getKanji())
                        || arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(1).getKanji())
                        || arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(2).getKanji())
                        || arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(3).getKanji())
                        || arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(4).getKanji())
                        || arrayListKanjiItem.get(currentQuestion).getKanji()
                        .equals(tempKanji.get(5).getKanji())) {
                    correctAnswer++;
                    //Toast.makeText(getContext(), "Correct!", Toast.LENGTH_LONG).show();
                    System.out.println("Correct!");
                }else{
                    wrongAnswer++;
                    //Toast.makeText(getContext(), "Wrong!", Toast.LENGTH_LONG).show();
                    System.out.println("Wrong!");
                }

                //Load next question if any
                if(currentQuestion < arrayListKanjiItem.size()-1){
                    currentQuestion++;
                    //tempKanji.clear();
                }
                break;

        }
    }
}