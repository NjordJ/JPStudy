package com.bigil.jpstudy.ui.beginnerkanji.info;

import android.content.Intent;
import android.os.Parcel;
import android.util.Log;
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

    //Classes
    private KanjiItem kanjiItem;

    //Variables


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.beginnerkanji_info_item, container, false);

        kanjiItem = new KanjiItem(Parcel.obtain());

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            kanjiItem = bundle.getParcelable("KanjiItemData");
        }

        String kanjiValue = kanjiItem.getKanji();
        Integer gradeValue = kanjiItem.getGrade();
        Integer strokeountValue = kanjiItem.getStrokeCount();
        Integer jlptValue = kanjiItem.getJlpt();
        String unicodeValue = kanjiItem.getKanji();

//        VideoView videoViewBeginnerKanjiInfo = root.findViewById(R.id.videoViewBeginnerKanjiInfoHowToStroke);
        TextView textViewBeginnerKanjiInfoKanji = root.findViewById(R.id.textViewBeginnerKanjiInfoKanjiValue);
        TextView textViewBeginnerKanjiInfoGradeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoGradeValue);
        TextView textViewBeginnerKanjiInfoStrokeCountValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeCountValue);
        TextView textViewBeginnerKanjiInfoStrokeUnicodeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeUnicodeValue);
        TextView textViewBeginnerKanjiInfoJlptValue = root.findViewById(R.id.textViewBeginnerKanjiInfoJlptValue);

        textViewBeginnerKanjiInfoKanji.setText(kanjiValue);
        textViewBeginnerKanjiInfoGradeValue.setText(String.valueOf(gradeValue));
        textViewBeginnerKanjiInfoStrokeCountValue.setText(String.valueOf(strokeountValue));
        textViewBeginnerKanjiInfoJlptValue.setText(String.valueOf(jlptValue));
        textViewBeginnerKanjiInfoStrokeUnicodeValue.setText(String.valueOf(unicodeValue));

        return root;
    }

}