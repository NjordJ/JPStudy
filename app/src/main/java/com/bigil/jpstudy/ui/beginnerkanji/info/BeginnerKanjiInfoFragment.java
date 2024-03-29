package com.bigil.jpstudy.ui.beginnerkanji.info;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.text.TextUtils;
import android.widget.ImageView;
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

public class BeginnerKanjiInfoFragment extends Fragment {

    //Classes
    private KanjiItem kanjiItem;

    AnimatedVectorDrawable imgAnimation;

    //Variables
    private ImageView imageViewKanjiInfoHowToStroke;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.kanji_info_item, container, false);


        kanjiItem = new KanjiItem(Parcel.obtain());

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            kanjiItem = bundle.getParcelable("KanjiItemData");
        }

        String kanjiValue = kanjiItem.getKanji();
        Integer gradeValue = kanjiItem.getGrade();
        Integer strokecountValue = kanjiItem.getStrokeCount();
        String[] meaningsValue = kanjiItem.getMeanings();
        String[] kun_readingsValue = kanjiItem.getKunyomiReading();
        String[] on_readingsValue = kanjiItem.getOnyomiReading();
        String[] name_readingsValue = kanjiItem.getNameReadings();
        Integer jlptValue = kanjiItem.getJlpt();
        String unicodeValue = kanjiItem.getUnicodeKanji();
        String heisigenValue = kanjiItem.getHeisig_en();
        Integer img = kanjiItem.getImageResource();

        //Convert String[] to single String
        String meanings = TextUtils.join(",", meaningsValue);
        String kun_readings = TextUtils.join(",", kun_readingsValue);
        String on_readings = TextUtils.join(",", on_readingsValue);
        String name_readings = TextUtils.join(",", name_readingsValue);

        //Find variables from layout
        imageViewKanjiInfoHowToStroke = root.findViewById(R.id.imageViewKanjiInfoHowToStroke);
        TextView textViewKanjiInfoKanji = root.findViewById(R.id.textViewKanjiInfoKanjiValue);
        TextView textViewKanjiInfoGradeValue = root.findViewById(R.id.textViewKanjiInfoGradeValue);
        TextView textViewKanjiInfoStrokeCountValue = root.findViewById(R.id.textViewKanjiInfoStrokeCountValue);
        TextView textViewKanjiInfoMeaningsValue = root.findViewById(R.id.textViewKanjiInfoMeaningsValue);
        TextView textViewKanjiInfoKunyomiValue = root.findViewById(R.id.textViewKanjiInfoKunyomiValue);
        TextView textViewKanjiInfoOnyomiValue = root.findViewById(R.id.textViewKanjiInfoOnyomiValue);
        TextView textViewKanjiInfoNameReadingsValue = root.findViewById(R.id.textViewKanjiInfoNameReadingsValue);
        TextView textViewKanjiInfoStrokeUnicodeValue = root.findViewById(R.id.textViewKanjiInfoUnicodeValue);
        TextView textViewBKanjiInfoJlptValue = root.findViewById(R.id.textViewKanjiInfoJlptValue);
        TextView textViewKanjiInfoHeisigEnValue = root.findViewById(R.id.textViewKanjiInfoHeisigEnValue);

        //Set values to TextViews
        textViewKanjiInfoKanji.setText(kanjiValue);
        textViewKanjiInfoGradeValue.setText(String.valueOf(gradeValue));
        textViewKanjiInfoStrokeCountValue.setText(String.valueOf(strokecountValue));
        textViewKanjiInfoMeaningsValue.setText(meanings);
        textViewKanjiInfoKunyomiValue.setText(kun_readings);
        textViewKanjiInfoOnyomiValue.setText(on_readings);
        textViewKanjiInfoNameReadingsValue.setText(name_readings);
        textViewKanjiInfoStrokeUnicodeValue.setText(unicodeValue);
        textViewBKanjiInfoJlptValue.setText(String.valueOf(jlptValue));
        textViewKanjiInfoHeisigEnValue.setText(heisigenValue);

        // get drawable by resource id
        if (img == 0)
        {
            imageViewKanjiInfoHowToStroke.setImageResource(R.drawable.ic_kanji);
        }else{
            imageViewKanjiInfoHowToStroke.setImageResource(img);
        }

        return root;
    }

}