package com.bigil.jpstudy.ui.beginnerkanji.info;

import android.content.Intent;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiParentFragment;
import com.bigil.jpstudy.models.KanjiItem;

import java.util.ArrayList;

import static com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiParentFragment.*;

public class BeginnerKanjiInfoFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_beginnerkanji_info, container, false);

        //kanjiBeginnerItemArrayList = (ArrayList<KanjiItem>)getArguments().get("kanjiItemData");

        Intent intent = new Intent();

        String kanji = intent.getStringExtra(EXTRA_KANJI);
//        Integer grade = intent.getIntExtra(EXTRA_GRADE, 0);
//        Integer strokeCount = intent.getIntExtra(EXTRA_STROKECOUNT, 0);
//        String meanings = intent.getStringExtra(EXTRA_MEANINGS);
//        String heisigEn = intent.getStringExtra(EXTRA_HEISIGEN);
//        String kunReadings = intent.getStringExtra(EXTRA_KUNREADINGS);
//        String onReadings = intent.getStringExtra(EXTRA_ONREADINGS);
//        String nameReadings = intent.getStringExtra(EXTRA_NAMEREADINGS);
//        Integer jlpt = intent.getIntExtra(EXTRA_JLPT, 0);
//        String unicode = intent.getStringExtra(EXTRA_UNICODE);

//        VideoView videoViewBeginnerKanjiInfo = root.findViewById(R.id.videoViewBeginnerKanjiInfoHowToStroke);
        TextView textViewBeginnerKanjiInfoKanji = root.findViewById(R.id.textViewBeginnerKanjiInfoKanjiValue);
//        TextView textViewBeginnerKanjiInfoGradeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoGradeValue);
//        TextView textViewBeginnerKanjiInfoStrokeCountValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeCountValue);
//        TextView textViewBeginnerKanjiInfoStrokeUnicodeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeUnicodeValue);
//        TextView textViewBeginnerKanjiInfoJlptValue = root.findViewById(R.id.textViewBeginnerKanjiInfoJlptValue);

        textViewBeginnerKanjiInfoKanji.setText(kanji);
//        textViewBeginnerKanjiInfoGradeValue.setText(grade);
//        textViewBeginnerKanjiInfoStrokeCountValue.setText(strokeCount);
//        textViewBeginnerKanjiInfoJlptValue.setText(jlpt);
//        textViewBeginnerKanjiInfoStrokeUnicodeValue.setText(unicode);

        return root;
    }

}