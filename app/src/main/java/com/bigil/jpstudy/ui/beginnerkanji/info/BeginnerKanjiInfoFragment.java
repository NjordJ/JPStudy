package com.bigil.jpstudy.ui.beginnerkanji.info;

import android.os.Parcel;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.activity.MainActivity;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.utils.JSONParsingAsync;

import java.util.ArrayList;

public class BeginnerKanjiInfoFragment extends Fragment {

    //Classes
    private KanjiItem kanjiItem;

    //Variables

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.beginnerkanji_info_item, container, false);


        kanjiItem = new KanjiItem(Parcel.obtain());

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            kanjiItem = bundle.getParcelable("KanjiItemData");
        }

        String kanjiValue = kanjiItem.getKanji();
        Integer gradeValue = kanjiItem.getGrade();
        Integer strokeountValue = kanjiItem.getStrokeCount();
        String[] meaningsValue = kanjiItem.getMeanings();
        String[] kun_readingsValue = kanjiItem.getKunyomiReading();
        String[] on_readingsValue = kanjiItem.getOnyomiReading();
        String[] name_readingsValue = kanjiItem.getNameReadings();
        Integer jlptValue = kanjiItem.getJlpt();
        String unicodeValue = kanjiItem.getKanji();
        String heisigenValue = kanjiItem.getHeisig_en();

        //Convert String[] to single String
        String meanings = TextUtils.join(",", meaningsValue);
        String kun_readings = TextUtils.join(",", kun_readingsValue);
        String on_readings = TextUtils.join(",", on_readingsValue);
        String name_readings = TextUtils.join(",", name_readingsValue);

        //Find variables from layout
//        VideoView videoViewBeginnerKanjiInfo = root.findViewById(R.id.videoViewBeginnerKanjiInfoHowToStroke);
        TextView textViewBeginnerKanjiInfoKanji = root.findViewById(R.id.textViewBeginnerKanjiInfoKanjiValue);
        TextView textViewBeginnerKanjiInfoGradeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoGradeValue);
        TextView textViewBeginnerKanjiInfoStrokeCountValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeCountValue);
        TextView textViewBeginnerKanjiInfoMeaningsValue = root.findViewById(R.id.textViewBeginnerKanjiInfoMeaningsValue);
        TextView textViewBeginnerKanjiInfoKunyomiValue = root.findViewById(R.id.textViewBeginnerKanjiInfoKunyomiValue);
        TextView textViewBeginnerKanjiInfoOnyomiValue = root.findViewById(R.id.textViewBeginnerKanjiInfoOnyomiValue);
        TextView textViewBeginnerKanjiInfoNameReadingsValue = root.findViewById(R.id.textViewBeginnerKanjiInfoNameReadingsValue);
        TextView textViewBeginnerKanjiInfoStrokeUnicodeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeUnicodeValue);
        TextView textViewBeginnerKanjiInfoJlptValue = root.findViewById(R.id.textViewBeginnerKanjiInfoJlptValue);
        TextView textViewBeginnerKanjiInfoHeisigEnValue = root.findViewById(R.id.textViewBeginnerKanjiInfoHeisigEnValue);

        //Set values to TextViews
        textViewBeginnerKanjiInfoKanji.setText(kanjiValue);
        textViewBeginnerKanjiInfoGradeValue.setText(String.valueOf(gradeValue));
        textViewBeginnerKanjiInfoStrokeCountValue.setText(String.valueOf(strokeountValue));
        textViewBeginnerKanjiInfoMeaningsValue.setText(meanings);
        textViewBeginnerKanjiInfoKunyomiValue.setText(kun_readings);
        textViewBeginnerKanjiInfoOnyomiValue.setText(on_readings);
        textViewBeginnerKanjiInfoNameReadingsValue.setText(name_readings);
        textViewBeginnerKanjiInfoJlptValue.setText(String.valueOf(jlptValue));
        textViewBeginnerKanjiInfoStrokeUnicodeValue.setText(String.valueOf(unicodeValue));
        textViewBeginnerKanjiInfoHeisigEnValue.setText(heisigenValue);

        return root;
    }

}