package com.bigil.jpstudy.ui.kana;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.models.KanjiItem;

public class KanaInfoFragment extends Fragment {

    //Classes
    private KanaItem kanaItem;

    //Variables


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.kana_info_item, container, false);


        kanaItem = new KanaItem(Parcel.obtain());

        Bundle bundle = this.getArguments();

        //Get values from behind fragment
        if (bundle != null) {
            kanaItem = bundle.getParcelable("KanaItemData");
        }

        String hiraganaValue = kanaItem.getHiragana();
        String katakanaValue = kanaItem.getKatakana();
        String romanjiValue = kanaItem.getTranscriptionKana();
        Integer imgHiragana = kanaItem.getImageResourceHiragana();
        Integer imgKatakana = kanaItem.getImageResourceKatakana();

        //Find variables from layout
        TextView textViewHiraganaInfoValue = root.findViewById(R.id.textViewHiraganaInfoValue);
        TextView textViewKatakanaInfoValue = root.findViewById(R.id.textViewKatakanaInfoValue);
        TextView textViewRomanjiInfoValue = root.findViewById(R.id.textViewRomanjiInfoValue);
//        TextView textViewBigHiraganaInfo = root.findViewById(R.id.textViewBigHiraganaInfo);
//        TextView textViewBigKatakanaInfo = root.findViewById(R.id.textViewBigKatakanaInfo);
        ImageView imageViewBigHiraganaInfo = root.findViewById(R.id.imageViewBigHiraganaInfo);
        ImageView imageViewBigKatakanaInfo = root.findViewById(R.id.imageViewBigKatakanaInfo);

        //Set values to TextViews
        textViewHiraganaInfoValue.setText(hiraganaValue);
        textViewKatakanaInfoValue.setText(katakanaValue);
        textViewRomanjiInfoValue.setText(romanjiValue);
//        textViewBigHiraganaInfo.setText(hiraganaValue);
//        textViewBigKatakanaInfo.setText(katakanaValue);

        // get drawable by resource id
        if (imgHiragana == 0 || imgKatakana == 0)
        {
            imageViewBigHiraganaInfo.setImageResource(R.drawable.ic_kanji);
            imageViewBigKatakanaInfo.setImageResource(R.drawable.ic_kanji);
        }else{
            imageViewBigHiraganaInfo.setImageResource(imgHiragana);
            imageViewBigKatakanaInfo.setImageResource(imgKatakana);
        }

        return root;
    }

}