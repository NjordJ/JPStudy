package com.bigil.jpstudy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class KanaItem implements Parcelable {

    private String mHiragana;
    private String mKatakana;
    private String mTranscriptionKana;

    private Integer mImageResourceHiragana;
    private Integer mImageResourceKatakana;

    //Constructor
    public KanaItem(String hiragana, String katakana, String transcriptionKana, Integer imageResourceHiragana, Integer imageResourceKatakana){
        mHiragana = hiragana;
        mKatakana = katakana;
        mTranscriptionKana = transcriptionKana;
        mImageResourceHiragana = imageResourceHiragana;
        mImageResourceKatakana = imageResourceKatakana;
    }

    public KanaItem(Parcel in) {
        mHiragana = in.readString();
        mKatakana = in.readString();
        mTranscriptionKana = in.readString();
        if (in.readByte() == 0) {
            mImageResourceHiragana = null;
        } else {
            mImageResourceHiragana = in.readInt();
        }
        if (in.readByte() == 0) {
            mImageResourceKatakana = null;
        } else {
            mImageResourceKatakana = in.readInt();
        }
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
    public String getHiragana() {
        return mHiragana;
    }

    public String getKatakana() {
        return mKatakana;
    }

    public String getTranscriptionKana() {
        return mTranscriptionKana;
    }

    public Integer getImageResourceHiragana() {
        return mImageResourceHiragana;
    }

    public Integer getImageResourceKatakana() {
        return mImageResourceKatakana;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHiragana);
        dest.writeString(mKatakana);
        dest.writeString(mTranscriptionKana);
        if (mImageResourceHiragana == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mImageResourceHiragana);
        }
        if (mImageResourceKatakana == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mImageResourceKatakana);
        }
    }
}
