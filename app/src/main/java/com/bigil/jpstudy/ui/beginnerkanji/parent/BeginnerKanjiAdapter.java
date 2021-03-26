package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;

import java.util.ArrayList;

public class BeginnerKanjiAdapter extends RecyclerView.Adapter<BeginnerKanjiAdapter.BeginnerKanjiViewHolder> {

    private ArrayList<BeginnerKanjiItem> mBeginnerKanjiList;

    public static class BeginnerKanjiViewHolder extends RecyclerView.ViewHolder {

        //Parent field in card
        public TextView mTextViewFirstKanjiBeginnerKanji;
        //Field to show kanjis in child of beginnerkanji

        //Child fields to show lists of kanjis
        public TextView mTextViewBeginnerKanji;
        public TextView mTextViewKunyomiReading;
        public TextView mTextViewOnyomiReading;

        public BeginnerKanjiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewFirstKanjiBeginnerKanji = itemView.findViewById(R.id.textViewFirstKanjiCardInBeginnerKanji);
        }
    }

    //Constructor
    public BeginnerKanjiAdapter (ArrayList<BeginnerKanjiItem> beginnerKanjiList) {
        mBeginnerKanjiList = beginnerKanjiList;
    }

    @NonNull
    @Override
    public BeginnerKanjiAdapter.BeginnerKanjiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Paste layout to ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beginnerkanji_item, parent, false);
        BeginnerKanjiAdapter.BeginnerKanjiViewHolder beginnerKanjiViewHolder = new BeginnerKanjiAdapter.BeginnerKanjiViewHolder(v);
        return beginnerKanjiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeginnerKanjiAdapter.BeginnerKanjiViewHolder holder, int position) {

        BeginnerKanjiItem currentItem = mBeginnerKanjiList.get(position);

        //Get information for current position
        holder.mTextViewFirstKanjiBeginnerKanji.setText(currentItem.getKanji());

    }

    @Override
    public int getItemCount() {
        return mBeginnerKanjiList.size();
    }

}
