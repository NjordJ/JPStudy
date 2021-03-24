package com.bigil.jpstudy.ui.kana;

public class KanaItem {

    private String mBigKana;
    private String mSmallKana;
    private String mTranscriptionKana;

    //Constructor
    public KanaItem(String bigKana, String smallKana, String transcriptionKana){
        mBigKana = bigKana;
        mSmallKana = smallKana;
        mTranscriptionKana = transcriptionKana;
    }

    //Getters
    public String getmBigKana() {
        return mBigKana;
    }

    public String getmSmallKana() {
        return mSmallKana;
    }

    public String getmTranscriptionKana() {
        return mTranscriptionKana;
    }


}
