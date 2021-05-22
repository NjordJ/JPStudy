package com.bigil.jpstudy.ui.kana;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
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
    private ImageView imageViewKanaInfoHowToStroke;

    //private Resources resources = getContext().getResources();

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

        String hiraganaValue = kanaItem.getmBigKana();
        String katakanaValue = kanaItem.getmSmallKana();
        String romanjiValue = kanaItem.getmTranscriptionKana();



        //Find variables from layout
        //imageViewKanaInfoHowToStroke = root.findViewById(R.id.imageViewKanaInfoHowToStroke);
        TextView textViewHiraganaInfoValue = root.findViewById(R.id.textViewHiraganaInfoValue);
        TextView textViewKatakanaInfoValue = root.findViewById(R.id.textViewKatakanaInfoValue);
        TextView textViewRomanjiInfoValue = root.findViewById(R.id.textViewRomanjiInfoValue);
        TextView textViewBigHiraganaInfo = root.findViewById(R.id.textViewBigHiraganaInfo);
        TextView textViewBigKatakanaInfo = root.findViewById(R.id.textViewBigKatakanaInfo);

        //Set values to TextViews
        textViewHiraganaInfoValue.setText(hiraganaValue);
        textViewKatakanaInfoValue.setText(katakanaValue);
        textViewRomanjiInfoValue.setText(romanjiValue);
        textViewBigHiraganaInfo.setText(hiraganaValue);
        textViewBigKatakanaInfo.setText(katakanaValue);

        // get drawable by resource id

//        if (img == 0)
//        {
//            imageViewKanjiInfoHowToStroke.setImageResource(R.drawable.ic_kanji);
//        }else{
//            imageViewKanjiInfoHowToStroke.setImageResource(img);
//        }

        return root;
    }

}