package com.bigil.jpstudy.ui.beginnerkanji.info;

import android.os.Parcel;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;

import java.util.ArrayList;

public class BeginnerKanjiInfoFragment extends Fragment {

    //Classes
    private KanjiItem kanjiItem;

    //Variables
    private ArrayAdapter<String> adapter;
    private ArrayList<String[]> arrayList;

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
//        String[] meanings = kanjiItem.getMeanings();
//        String[] kun_readings = kanjiItem.getKunyomiReading();
//        String[] on_readings = kanjiItem.getOnyomiReading();
//        String[] name_readings = kanjiItem.getNameReadings();
        Integer jlptValue = kanjiItem.getJlpt();
        String unicodeValue = kanjiItem.getKanji();
        String heisigenValue = kanjiItem.getHeisig_en();

//        VideoView videoViewBeginnerKanjiInfo = root.findViewById(R.id.videoViewBeginnerKanjiInfoHowToStroke);
        TextView textViewBeginnerKanjiInfoKanji = root.findViewById(R.id.textViewBeginnerKanjiInfoKanjiValue);
        TextView textViewBeginnerKanjiInfoGradeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoGradeValue);
        TextView textViewBeginnerKanjiInfoStrokeCountValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeCountValue);
        TextView textViewBeginnerKanjiInfoStrokeUnicodeValue = root.findViewById(R.id.textViewBeginnerKanjiInfoStrokeUnicodeValue);
        TextView textViewBeginnerKanjiInfoJlptValue = root.findViewById(R.id.textViewBeginnerKanjiInfoJlptValue);
        TextView textViewBeginnerKanjiInfoHeisigEnValue = root.findViewById(R.id.textViewBeginnerKanjiInfoHeisigEnValue);

        //ListView listViewKanjiInfo = root.findViewById(R.id.listViewKanjiInfo);

        // Adapter: You need three parameters 'the context, id of the layout (it will be where the data is shown),
        // and the array that contains the data
        //adapter = new ArrayAdapter<String[]>(getContext(), root.findViewById(R.id.listViewKanjiInfo), arrayList);

        textViewBeginnerKanjiInfoKanji.setText(kanjiValue);
        textViewBeginnerKanjiInfoGradeValue.setText(String.valueOf(gradeValue));
        textViewBeginnerKanjiInfoStrokeCountValue.setText(String.valueOf(strokeountValue));
        //textViewBeginnerKanjiInfoJlptValue.setText(String.valueOf(jlptValue));
        textViewBeginnerKanjiInfoStrokeUnicodeValue.setText(String.valueOf(unicodeValue));
        textViewBeginnerKanjiInfoHeisigEnValue.setText(heisigenValue);

        return root;
    }

}