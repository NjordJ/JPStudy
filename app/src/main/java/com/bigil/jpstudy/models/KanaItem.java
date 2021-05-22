package com.bigil.jpstudy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class KanaItem implements Parcelable {

    private String mBigKana;
    private String mSmallKana;
    private String mTranscriptionKana;

    //Constructor
    public KanaItem(String bigKana, String smallKana, String transcriptionKana){
        mBigKana = bigKana;
        mSmallKana = smallKana;
        mTranscriptionKana = transcriptionKana;
    }

    public KanaItem(Parcel in) {
        mBigKana = in.readString();
        mSmallKana = in.readString();
        mTranscriptionKana = in.readString();
    }

    public static final Creator<KanaItem> CREATOR = new Creator<KanaItem>() {
        @Override
        public KanaItem createFromParcel(Parcel in) {
            return new KanaItem(in);
        }

        @Override
        public KanaItem[] newArray(int size) {
            return new KanaItem[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mBigKana);
        dest.writeString(mSmallKana);
        dest.writeString(mTranscriptionKana);
    }
}
