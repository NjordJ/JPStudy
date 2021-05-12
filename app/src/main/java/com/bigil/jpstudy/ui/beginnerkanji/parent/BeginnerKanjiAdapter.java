package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class BeginnerKanjiAdapter extends RecyclerView.Adapter<BeginnerKanjiAdapter.BeginnerKanjiViewHolder> {

    private ArrayList<KanjiItem> mBeginnerKanjiList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onStartStudyClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class BeginnerKanjiViewHolder extends RecyclerView.ViewHolder {

        //Variables
        public TextView mTextViewKanji;
//        public TextView mTextViewGrade;
//        public TextView mTextViewStrokeCount;
//        public TextView mTextViewMeanings;
//        public TextView mTextViewHeisigEn;
//        public TextView mTextViewKunReadings;
//        public TextView mTextViewOnReadings;
//        public TextView mTextViewNameReadings;
//        public TextView mTextViewJlpt;
//        public TextView mTextViewUnicode;

        public TextView mTextViewCurrentNumberBeginnerKanji;
        public TextView mTextViewClickedLastTimeBeginnerKanji;

        public BeginnerKanjiViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTextViewKanji = itemView.findViewById(R.id.textViewFirstKanjiCardInBeginnerKanji);
//            mTextViewGrade = null;
//            mTextViewStrokeCount = null;
//            mTextViewMeanings = null;
//            mTextViewHeisigEn = null;
//            mTextViewKunReadings = null;
//            mTextViewOnReadings = null;
//            mTextViewNameReadings = null;
//            mTextViewJlpt = null;
//            mTextViewUnicode = null;

            mTextViewCurrentNumberBeginnerKanji = itemView.findViewById(R.id.textViewCurrentNumberBeginnerKanji);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    //Constructor
    public BeginnerKanjiAdapter (ArrayList<KanjiItem> beginnerKanjiList) {
        mBeginnerKanjiList = beginnerKanjiList;
    }

    @NonNull
    @Override
    public BeginnerKanjiAdapter.BeginnerKanjiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Paste layout to ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kanji_item, parent, false);
        BeginnerKanjiAdapter.BeginnerKanjiViewHolder beginnerKanjiViewHolder = new BeginnerKanjiAdapter.BeginnerKanjiViewHolder(v, mListener);
        return beginnerKanjiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeginnerKanjiViewHolder holder, int position) {

        KanjiItem currentItem = mBeginnerKanjiList.get(position);

        int allKanjiCount = mBeginnerKanjiList.size();
        for(int i = 1; i < mBeginnerKanjiList.size(); i++){
            holder.mTextViewCurrentNumberBeginnerKanji.setText(i+"/"+allKanjiCount);
            i++;
        }

        //Get information for current position
        holder.mTextViewKanji.setText(currentItem.getKanji());
//        holder.mTextViewGrade.setText(currentItem.getGrade());
//        holder.mTextViewStrokeCount.setText(currentItem.getStrokeCount());
//        holder.mTextViewMeanings.setText(currentItem.getMeanings());
//        holder.mTextViewKunReadings.setText(currentItem.getKunyomiReading());
//        holder.mTextViewOnReadings.setText(currentItem.getOnyomiReading());
//        holder.mTextViewNameReadings.setText(currentItem.getNameReadings());
//        holder.mTextViewJlpt.setText(currentItem.getJlpt());
//        holder.mTextViewUnicode.setText(currentItem.getUnicodeKanji());
//        holder.mTextViewHeisigEn.setText(currentItem.getHeisig_en());

        //holder.mTextViewCurrentNumberBeginnerKanji.setText(kanjiCount+"/"+allKanjiCount);

    }

    @Override
    public int getItemCount() {
        return mBeginnerKanjiList.size();
    }

}
