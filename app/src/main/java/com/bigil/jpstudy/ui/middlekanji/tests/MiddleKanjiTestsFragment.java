package com.bigil.jpstudy.ui.middlekanji.tests;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.middlekanji.parent.MiddleKanjiParentFragment;

import java.util.*;

public class MiddleKanjiTestsFragment extends Fragment implements View.OnClickListener {

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

    private Random r = new Random();

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
        //String answerKanji = tempKanji.get(number).getKanji();
        kun_readingsValue = arrayListKanjiItem.get(number).getKunyomiReading();
        on_readingsValue = arrayListKanjiItem.get(number).getOnyomiReading();

        Integer tempKanjiSize = tempKanji.size();

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

        switch (tempKanjiSize) {
//            case 6:
//                textViewBeginnerKanjiTestsAnswer1.setText(answersKanji[0]);
//                textViewBeginnerKanjiTestsAnswer2.setText(answersKanji[1]);
//                textViewBeginnerKanjiTestsAnswer3.setText(answersKanji[2]);
//                textViewBeginnerKanjiTestsAnswer4.setText(answersKanji[3]);
//                textViewBeginnerKanjiTestsAnswer5.setText(answersKanji[4]);
//                textViewBeginnerKanjiTestsAnswer6.setText(answersKanji[5]);
//                break;
            case 5:
                textViewBeginnerKanjiTestsAnswer1.setText(answersKanji[0]);
                textViewBeginnerKanjiTestsAnswer2.setText(answersKanji[1]);
                textViewBeginnerKanjiTestsAnswer3.setText(answersKanji[2]);
                textViewBeginnerKanjiTestsAnswer4.setText(answersKanji[3]);
                textViewBeginnerKanjiTestsAnswer5.setText(answersKanji[4]);
                textViewBeginnerKanjiTestsAnswer6.setText(answersKanji[5]);
                break;
//            case 4:
//                textViewBeginnerKanjiTestsAnswer1.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer2.setText(kanji2);
//                textViewBeginnerKanjiTestsAnswer3.setText(kanji3);
//                textViewBeginnerKanjiTestsAnswer4.setText(kanji4);
//                textViewBeginnerKanjiTestsAnswer5.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer6.setText(kanji1);
//                break;
//            case 3:
//                textViewBeginnerKanjiTestsAnswer1.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer2.setText(kanji2);
//                textViewBeginnerKanjiTestsAnswer3.setText(kanji3);
//                textViewBeginnerKanjiTestsAnswer4.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer5.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer6.setText(kanji1);
//                break;
//            case 2:
//                textViewBeginnerKanjiTestsAnswer1.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer2.setText(kanji2);
//                textViewBeginnerKanjiTestsAnswer3.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer4.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer5.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer6.setText(kanji1);
//                break;
//            case 1:
//                textViewBeginnerKanjiTestsAnswer1.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer2.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer3.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer4.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer5.setText(kanji1);
//                textViewBeginnerKanjiTestsAnswer6.setText(kanji1);
//                break;
        }

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
                if (answerKanjiItem
                        .equals(textViewBeginnerKanjiTestsAnswer1.getText().toString()) ||
                        answerKanjiItem
                                .equals(textViewBeginnerKanjiTestsAnswer2.getText().toString()) ||
                        answerKanjiItem
                                .equals(textViewBeginnerKanjiTestsAnswer3.getText().toString()) ||
                        answerKanjiItem
                                .equals(textViewBeginnerKanjiTestsAnswer4.getText().toString()) ||
                        answerKanjiItem
                                .equals(textViewBeginnerKanjiTestsAnswer5.getText().toString()) ||
                        answerKanjiItem
                                .equals(textViewBeginnerKanjiTestsAnswer6.getText().toString())) {
                    System.out.println("KANJI: "+answerKanjiItem+" TEXTVIEW1: "+textViewBeginnerKanjiTestsAnswer1.getText().toString()+
                            " TEXTVIEW2: "+textViewBeginnerKanjiTestsAnswer2.getText().toString()+" TEXTVIEW3: "+textViewBeginnerKanjiTestsAnswer3.getText().toString()+
                            " TEXTVIEW4: "+textViewBeginnerKanjiTestsAnswer4.getText().toString()+" TEXTVIEW5: "+textViewBeginnerKanjiTestsAnswer5.getText().toString()+
                            " TEXTVIEW6: "+textViewBeginnerKanjiTestsAnswer6.getText().toString());
                correctAnswer++;
                //Toast.makeText(getContext().getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                //System.out.println("Correct!");
            } else {
                wrongAnswer++;
                //Toast.makeText(getContext(), "Wrong!", Toast.LENGTH_LONG).show();
                //System.out.println("Wrong!");
            }

                //Load next question if any
                if(currentQuestion < arrayListKanjiItem.size()-1){
                    currentQuestion++;

                    //Collections.shuffle(tempKanji);
                    setTextsScreen(currentQuestion);
                }else{
                    //ShowDialog with results at end test
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Correct: "+correctAnswer+" Wrong: "+wrongAnswer);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                    Fragment fragmentMiddleKanjiParentFragment = new MiddleKanjiParentFragment();

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_top)
                            .replace(R.id.nav_host_fragment, fragmentMiddleKanjiParentFragment)
                            .addToBackStack(null)
                            .commit();
                }
                break;

        }

    }

    public static List<KanjiItem> pickNRandom(List<KanjiItem> lst, int n) {
        List<KanjiItem> copy = new ArrayList<>(lst);
        //Randomize elements
        Collections.shuffle(copy);
        return n > copy.size() ? copy.subList(0, copy.size()) : copy.subList(0, n);
    }

}