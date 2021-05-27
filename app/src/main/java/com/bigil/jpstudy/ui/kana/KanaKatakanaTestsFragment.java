package com.bigil.jpstudy.ui.kana;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KanaKatakanaTestsFragment extends Fragment implements View.OnClickListener {

    //Classes

    //From layout
    CardView cardViewKanaTestsAnswer1;
    CardView cardViewKanaTestsAnswer2;
    CardView cardViewKanaTestsAnswer3;
    CardView cardViewKanaTestsAnswer4;
    CardView cardViewKanaTestsAnswer5;
    CardView cardViewKanaTestsAnswer6;

    TextView textViewKanaTestsAnswer1;
    TextView textViewKanaTestsAnswer2;
    TextView textViewKanaTestsAnswer3;
    TextView textViewKanaTestsAnswer4;
    TextView textViewKanaTestsAnswer5;
    TextView textViewKanaTestsAnswer6;

    TextView textViewKanaRomanjiReading;
    TextView textViewKanaTestsCurrentAnswer;

    //Score
    TextView textViewScoreBeginnerKanji1;

    private SharedPreferences pref;

    //Variables
    private ArrayList<KanaItem> arrayListKanaItem;
    private List<KanaItem> tempKana;
    private String answerKanaItem;
    private String kanaValue;
    private String romanjiReading;
    private String kana1;
    private String kana2;
    private String kana3;
    private String kana4;
    private String kana5;
    private String kana6;
    private Integer correctAnswer = 0;
    private Integer wrongAnswer = 0;
    private Integer currentQuestion = 0;
    private Double answersScore = 0.00;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kana_tests, container, false);

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            arrayListKanaItem = bundle.getParcelableArrayList("KanaDataTests");
        }

        //Randomize elements
        Collections.shuffle(arrayListKanaItem);

        //Find variables from layout
        cardViewKanaTestsAnswer1 = root.findViewById(R.id.cardViewKanaTestsAnswer1);
        cardViewKanaTestsAnswer2 = root.findViewById(R.id.cardViewKanaTestsAnswer2);
        cardViewKanaTestsAnswer3 = root.findViewById(R.id.cardViewKanaTestsAnswer3);
        cardViewKanaTestsAnswer4 = root.findViewById(R.id.cardViewKanaTestsAnswer4);
        cardViewKanaTestsAnswer5 = root.findViewById(R.id.cardViewKanaTestsAnswer5);
        cardViewKanaTestsAnswer6 = root.findViewById(R.id.cardViewKanaTestsAnswer6);

        textViewKanaRomanjiReading = root.findViewById(R.id.textViewKanaRomanjiReading);
        textViewKanaTestsCurrentAnswer = root.findViewById(R.id.textViewKanaTestsCurrentAnswer);
        textViewKanaTestsAnswer1 = root.findViewById(R.id.textViewKanaTestsAnswer1);
        textViewKanaTestsAnswer2 = root.findViewById(R.id.textViewKanaTestsAnswer2);
        textViewKanaTestsAnswer3 = root.findViewById(R.id.textViewKanaTestsAnswer3);
        textViewKanaTestsAnswer4 = root.findViewById(R.id.textViewKanaTestsAnswer4);
        textViewKanaTestsAnswer5 = root.findViewById(R.id.textViewKanaTestsAnswer5);
        textViewKanaTestsAnswer6 = root.findViewById(R.id.textViewKanaTestsAnswer6);

        setTextsScreen(currentQuestion);

        InitPref();

        cardViewKanaTestsAnswer1.setOnClickListener(this);
        cardViewKanaTestsAnswer2.setOnClickListener(this);
        cardViewKanaTestsAnswer3.setOnClickListener(this);
        cardViewKanaTestsAnswer4.setOnClickListener(this);
        cardViewKanaTestsAnswer5.setOnClickListener(this);
        cardViewKanaTestsAnswer6.setOnClickListener(this);

        return root;
    }

    private void setTextsScreen(Integer number){

        //Collect n unique elements
        tempKana = pickNRandom(arrayListKanaItem, 5);

        kanaValue = arrayListKanaItem.get(number).getKatakana();
        answerKanaItem = kanaValue;

        romanjiReading = arrayListKanaItem.get(number).getTranscriptionKana();

        Integer tempKanjiSize = tempKana.size();

        kana1 = answerKanaItem;
        kana2 = tempKana.get(0).getKatakana();
        kana3 = tempKana.get(1).getKatakana();
        kana4 = tempKana.get(2).getKatakana();
        kana5 = tempKana.get(3).getKatakana();
        kana6 = tempKana.get(4).getKatakana();

        String[] answersKanji = {kana1, kana2, kana3, kana4, kana5, kana6};

        Collections.shuffle(Arrays.asList(answersKanji));

        textViewKanaRomanjiReading.setText(romanjiReading);
        textViewKanaTestsCurrentAnswer.setText((currentQuestion+1)+"/"+ arrayListKanaItem.size());

        textViewKanaTestsAnswer1.setText(answersKanji[0]);
        textViewKanaTestsAnswer2.setText(answersKanji[1]);
        textViewKanaTestsAnswer3.setText(answersKanji[2]);
        textViewKanaTestsAnswer4.setText(answersKanji[3]);
        textViewKanaTestsAnswer5.setText(answersKanji[4]);
        textViewKanaTestsAnswer6.setText(answersKanji[5]);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cardViewKanaTestsAnswer1:
                //Check if answer correct
                CheckAnswer(textViewKanaTestsAnswer1);
                break;
            case R.id.cardViewKanaTestsAnswer2:
                CheckAnswer(textViewKanaTestsAnswer2);
                break;
            case R.id.cardViewKanaTestsAnswer3:
                CheckAnswer(textViewKanaTestsAnswer3);
                break;
            case R.id.cardViewKanaTestsAnswer4:
                CheckAnswer(textViewKanaTestsAnswer4);
                break;
            case R.id.cardViewKanaTestsAnswer5:
                CheckAnswer(textViewKanaTestsAnswer5);
                break;
            case R.id.cardViewKanaTestsAnswer6:
                CheckAnswer(textViewKanaTestsAnswer6);
                break;

        }

    }

    private static List<KanaItem> pickNRandom(List<KanaItem> lst, int n) {
        List<KanaItem> copy = new ArrayList<>(lst);
        //Randomize elements
        Collections.shuffle(copy);
        return n > copy.size() ? copy.subList(0, copy.size()) : copy.subList(0, n);
    }

    private void CheckAnswer (TextView textView){
        Toast mToastCorrectAnswer = Toast.makeText(getContext(), "Correct!", Toast.LENGTH_SHORT);
        Toast mToastWrongAnswer = Toast.makeText(getContext(), "Wrong!", Toast.LENGTH_SHORT);
        if (answerKanaItem
                .equals(textView.getText().toString())) {
            correctAnswer++;
            //cardView.setCardBackgroundColor(Color.parseColor("#00a86b"));
            mToastCorrectAnswer.show();
        }
        else {
            wrongAnswer++;
            mToastWrongAnswer.show();
        }

        //Load next question if any
        if(currentQuestion < arrayListKanaItem.size()-1){
            currentQuestion++;
            //cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
//            mToastCorrectAnswer.cancel();
//            mToastWrongAnswer.cancel();
            setTextsScreen(currentQuestion);
        }else{
            //Score value after end tests
            answersScore = (double) ((correctAnswer * 100) / arrayListKanaItem.size());
            //Save to SharedPreferences
//            SharedPreferences.Editor edit = pref.edit();
//            edit.putString(getString(R.string.saved_high_score_key), String.valueOf(answersScore));
//            edit.apply();
            //ShowDialog with results at end test
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle("Result");
            alertDialog.setMessage("Correct: "+correctAnswer+"\n Wrong: "+wrongAnswer+"\n Score: "+answersScore+"%");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            Fragment fragmentKanaFragment = new KanaFragment();
            OpenNewFragment(fragmentKanaFragment);
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