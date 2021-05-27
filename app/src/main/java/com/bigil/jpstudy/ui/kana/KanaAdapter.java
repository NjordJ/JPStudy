package com.bigil.jpstudy.ui.kana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiAdapter;

import java.util.ArrayList;

public class KanaAdapter extends RecyclerView.Adapter<KanaAdapter.KanaViewHolder> {

    private ArrayList<KanaItem> mKanaList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onStartStudyClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class KanaViewHolder extends RecyclerView.ViewHolder {

        //Variables
        public TextView mTextViewBigKana;
        public TextView mTextViewSmallKana;
        public TextView mTextViewTranscriptionKana;

        public KanaViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewBigKana = itemView.findViewById(R.id.textViewBigKana);
            mTextViewSmallKana = itemView.findViewById(R.id.textViewSmallKana);
            mTextViewTranscriptionKana = itemView.findViewById(R.id.textViewTranscriptionKana);

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
    public KanaAdapter (ArrayList<KanaItem> kanaList) {
        mKanaList = kanaList;
    }

    @NonNull
    @Override
    public KanaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Paste layout to ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kana_item, parent, false);
        KanaAdapter.KanaViewHolder kanaViewHolder = new KanaViewHolder(v, mListener);
        return kanaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KanaViewHolder holder, int position) {

        KanaItem currentItem = mKanaList.get(position);

        //Get information for current position
        holder.mTextViewBigKana.setText(currentItem.getHiragana());
        holder.mTextViewSmallKana.setText(currentItem.getKatakana());
        holder.mTextViewTranscriptionKana.setText(currentItem.getTranscriptionKana());
    }

    @Override
    public int getItemCount() {
        return mKanaList.size();
    }
}
