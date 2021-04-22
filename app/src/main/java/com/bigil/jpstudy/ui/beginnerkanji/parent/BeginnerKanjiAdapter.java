package com.bigil.jpstudy.ui.beginnerkanji.parent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;

import java.util.ArrayList;

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

        //Parent field in card
        public TextView mTextViewFirstKanjiBeginnerKanji;
        public TextView mTextViewCurrentNumberBeginnerKanji;
        public TextView mTextViewClickedLastTimeBeginnerKanji;

        //Field to show kanjis in child of beginnerkanji

        //Child fields to show lists of kanjis
//        public TextView mTextViewBeginnerKanji;
//        public TextView mTextViewKunyomiReading;
//        public TextView mTextViewOnyomiReading;

        public BeginnerKanjiViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTextViewFirstKanjiBeginnerKanji = itemView.findViewById(R.id.textViewFirstKanjiCardInBeginnerKanji);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.beginnerkanji_item, parent, false);
        BeginnerKanjiAdapter.BeginnerKanjiViewHolder beginnerKanjiViewHolder = new BeginnerKanjiAdapter.BeginnerKanjiViewHolder(v, mListener);
        return beginnerKanjiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeginnerKanjiViewHolder holder, int position) {

        KanjiItem currentItem = mBeginnerKanjiList.get(position);



        int allKanjiCount = mBeginnerKanjiList.size();
        for(int i = 0; i < mBeginnerKanjiList.size(); i++){
            holder.mTextViewCurrentNumberBeginnerKanji.setText(i+"/"+allKanjiCount);
            i++;
        }

        //Get information for current position
        holder.mTextViewFirstKanjiBeginnerKanji.setText(currentItem.getKanji());

        //holder.mTextViewCurrentNumberBeginnerKanji.setText(kanjiCount+"/"+allKanjiCount);

    }

    @Override
    public int getItemCount() {
        return mBeginnerKanjiList.size();
    }

}
