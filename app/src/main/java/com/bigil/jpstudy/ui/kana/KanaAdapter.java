package com.bigil.jpstudy.ui.kana;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;

import java.util.ArrayList;

public class KanaAdapter extends RecyclerView.Adapter<KanaAdapter.KanaViewHolder> {

    private ArrayList<KanaItem> mKanaList;

    public static class KanaViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewBigKana;
        public TextView mTextViewSmallKana;
        public TextView mTextViewTranscriptionKana;

        public KanaViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewBigKana = itemView.findViewById(R.id.textViewBigKana);
            mTextViewSmallKana = itemView.findViewById(R.id.textViewSmallKana);
            mTextViewTranscriptionKana = itemView.findViewById(R.id.textViewTranscriptionKana);
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
        KanaViewHolder kanaViewHolder = new KanaViewHolder(v);
        return kanaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KanaViewHolder holder, int position) {

        KanaItem currentItem = mKanaList.get(position);

        //Get information for current position
        holder.mTextViewBigKana.setText(currentItem.getmBigKana());
        holder.mTextViewSmallKana.setText(currentItem.getmSmallKana());
        holder.mTextViewTranscriptionKana.setText(currentItem.getmTranscriptionKana());
    }

    @Override
    public int getItemCount() {
        return mKanaList.size();
    }
}
