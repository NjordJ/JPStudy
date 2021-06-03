package com.bigil.jpstudy.ui.beginnerkanji.tests;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.anotherkanji.parent.AnotherKanjiParentFragment;
import com.bigil.jpstudy.ui.beginnerkanji.parent.*;
import com.bigil.jpstudy.ui.highkanji.parent.HighKanjiParentFragment;
import com.bigil.jpstudy.ui.middlekanji.parent.MiddleKanjiParentFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BeginnerKanjiTestsFragment extends Fragment implements View.OnClickListener {

    public static final String BEGINNER_SCORE = "beginnerScore";

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

    //Score
    TextView textViewScoreBeginnerKanji1;

    private SharedPreferences pref;

    //Variables
    private ArrayList<KanjiItem> arrayListKanjiItem;
    private List<KanjiItem> tempKanji;
    String answerKanjiItem;
    private String kanjiValue;
    private String kun_readings;
    private String on_readings;
    private String kanji1;
    private String kanji2;
    private String kanji3;
    private String kanji4;
    private String kanji5;
    private String kanji6;
    private String[] kun_readingsValue;
    private String[] on_readingsValue;
    private Integer correctAnswer = 0;
    private Integer wrongAnswer = 0;
    private Integer currentQuestion = 0;
    private Integer grade;
    private Double answersScore = 0.00;

    private Snackbar mToastCorrectAnswer;
    private Snackbar mToastWrongAnswer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kanji_tests, container, false);

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            arrayListKanjiItem = bundle.getParcelableArrayList("KanjiItemDataTests");
        }

        //Randomize elements
        Collections.shuffle(arrayListKanjiItem);

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

        InitPref();

        cardViewBeginnerKanjiTestsAnswer1.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer2.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer3.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer4.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer5.setOnClickListener(this);
        cardViewBeginnerKanjiTestsAnswer6.setOnClickListener(this);

        return root;
    }

    private void setTextsScreen(Integer number){

        //Collect n unique elements
        tempKanji = pickNRandom(arrayListKanjiItem, 5);

        kanjiValue = arrayListKanjiItem.get(number).getKanji();
        answerKanjiItem = kanjiValue;

        grade = arrayListKanjiItem.get(number).getGrade();
        kun_readingsValue = arrayListKanjiItem.get(number).getKunyomiReading();
        on_readingsValue = arrayListKanjiItem.get(number).getOnyomiReading();

        kanji1 = answerKanjiItem;
        kanji2 = tempKanji.get(0).getKanji();
        kanji3 = tempKanji.get(1).getKanji();
        kanji4 = tempKanji.get(2).getKanji();
        kanji5 = tempKanji.get(3).getKanji();
        kanji6 = tempKanji.get(4).getKanji();

        String[] answersKanji = {kanji1, kanji2, kanji3, kanji4, kanji5, kanji6};

        Collections.shuffle(Arrays.asList(answersKanji));

        kun_readings = TextUtils.join(",", kun_readingsValue);
        on_readings = TextUtils.join(",", on_readingsValue);

        textViewBeginnerKanjiTestsKunuomiValue.setText(kun_readings);
        textViewBeginnerKanjiTestsOnyomiValue.setText(on_readings);
        textViewBeginnerKanjiTestsCurrentAnswer.setText((currentQuestion+1)+"/"+ arrayListKanjiItem.size());

        textViewBeginnerKanjiTestsAnswer1.setText(answersKanji[0]);
        textViewBeginnerKanjiTestsAnswer2.setText(answersKanji[1]);
        textViewBeginnerKanjiTestsAnswer3.setText(answersKanji[2]);
        textViewBeginnerKanjiTestsAnswer4.setText(answersKanji[3]);
        textViewBeginnerKanjiTestsAnswer5.setText(answersKanji[4]);
        textViewBeginnerKanjiTestsAnswer6.setText(answersKanji[5]);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cardViewBeginnerKanjiTestsAnswer1:
                //Check if answer correct
                CheckAnswer(textViewBeginnerKanjiTestsAnswer1);
                break;
            case R.id.cardViewBeginnerKanjiTestsAnswer2:
                CheckAnswer(textViewBeginnerKanjiTestsAnswer2);
                break;
            case R.id.cardViewBeginnerKanjiTestsAnswer3:
                CheckAnswer(textViewBeginnerKanjiTestsAnswer3);
                break;
            case R.id.cardViewBeginnerKanjiTestsAnswer4:
                CheckAnswer(textViewBeginnerKanjiTestsAnswer4);
                break;
            case R.id.cardViewBeginnerKanjiTestsAnswer5:
                CheckAnswer(textViewBeginnerKanjiTestsAnswer5);
                break;
            case R.id.cardViewBeginnerKanjiTestsAnswer6:
                CheckAnswer(textViewBeginnerKanjiTestsAnswer6);
                break;

        }

    }

    private static List<KanjiItem> pickNRandom(List<KanjiItem> lst, int n) {
        List<KanjiItem> copy = new ArrayList<>(lst);
        //Randomize elements
        Collections.shuffle(copy);
        return n > copy.size() ? copy.subList(0, copy.size()) : copy.subList(0, n);
    }

    private void CheckAnswer (TextView textView){
        mToastCorrectAnswer = Snackbar.make(textView, R.string.correctText_Toast, 250);
        mToastWrongAnswer = Snackbar.make(textView, R.string.wrongText_Toast, 250);
        if (answerKanjiItem
                .equals(textView.getText().toString())) {
            correctAnswer++;
            mToastCorrectAnswer.show();
        }
        else {
            wrongAnswer++;
            mToastWrongAnswer.show();
        }

        //Load next question if any
        if(currentQuestion < arrayListKanjiItem.size()-1){
            currentQuestion++;
            setTextsScreen(currentQuestion);

        }else{
            //Score value after end tests
            answersScore = (double) ((correctAnswer * 100) / arrayListKanjiItem.size());
            //Save to SharedPreferences
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(getString(R.string.saved_high_score_key), String.valueOf(answersScore));
            edit.apply();
            //ShowDialog with results at end test
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle(R.string.resultText_Dialog);
            alertDialog.setMessage(getString(R.string.correctText_Dialog)+correctAnswer+"\n"+getString(R.string.wrongText_Dialog)+wrongAnswer+"\n"+getString(R.string.scoreText_Dialog)+answersScore+"%");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            switch (grade){
                case 1:
                    Fragment fragmentBeginnerKanjiParentFragment = new BeginnerKanjiParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiParentFragment);
                    break;
                case 2:
                    Fragment fragmentBeginnerKanjiTwoParentFragment = new BeginnerKanjiTwoParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiTwoParentFragment);
                    break;
                case 3:
                    Fragment fragmentBeginnerKanjiThreeParentFragment = new BeginnerKanjiThreeParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiThreeParentFragment);
                    break;
                case 4:
                    Fragment fragmentBeginnerKanjiFourParentFragment = new BeginnerKanjiFourParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiFourParentFragment);
                    break;
                case 5:
                    Fragment fragmentBeginnerKanjiFiveParentFragment = new BeginnerKanjiFiveParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiFiveParentFragment);
                    break;
                case 6:
                    Fragment fragmentBeginnerKanjiSixParentFragment = new BeginnerKanjiSixParentFragment();
                    OpenNewFragment(fragmentBeginnerKanjiSixParentFragment);
                    break;
                case 8:
                    Fragment fragmentMiddleKanjiParentFragment = new MiddleKanjiParentFragment();
                    OpenNewFragment(fragmentMiddleKanjiParentFragment);
                    break;
                case 9:
                    Fragment fragmentHighKanjiParentFragment = new HighKanjiParentFragment();
                    OpenNewFragment(fragmentHighKanjiParentFragment);
                    break;
                case 10:
                    Fragment fragmentAnotherKanjiParentFragment = new AnotherKanjiParentFragment();
                    OpenNewFragment(fragmentAnotherKanjiParentFragment);
                    break;

            }

        }
    }

    private void OpenNewFragment(Fragment newFragment){
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_out_bottom, R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_top)
                .replace(R.id.nav_host_fragment, newFragment)
                .addToBackStack(null)
                .commit();

    }

    private void InitPref(){
        pref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

}