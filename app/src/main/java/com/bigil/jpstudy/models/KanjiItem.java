package com.bigil.jpstudy.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KanjiItem implements Parcelable, Serializable {

    //private Integer id;

    @SerializedName("kanji")
    public String mKanji;
    @SerializedName("grade")
    private Integer mGrade;
    @SerializedName("stroke_count")
    private Integer mStrokeCount;
    @SerializedName("meanings")
    private String[] mMeanings;
    @SerializedName("heisig_en")
    private String mHeisig_en;
    @SerializedName("kun_readings")
    public String[] mKunyomiReading;
    @SerializedName("on_readings")
    public String[] mOnyomiReading;
    @SerializedName("name_readings")
    private String[] mNameReadings;
    @SerializedName("jlpt")
    private Integer mJlpt;
    @SerializedName("unicode")
    private String mUnicodeKanji;

    private Integer mImageResource;

    //Constructor
    public KanjiItem(String kanji, Integer grade, Integer strokeCount, String[] meanings,
                     String heisig_en, String[] kunyomiReading, String[] onyomiReading,
                     String[] nameReadings, Integer jlpt, String unicodeKanji, Integer imageResource)
    {
        this.mKanji = kanji;
        this.mGrade = grade;
        this.mStrokeCount = strokeCount;
        this.mMeanings = meanings;
        this.mHeisig_en = heisig_en;
        this.mKunyomiReading = kunyomiReading;
        this.mOnyomiReading = onyomiReading;
        this.mNameReadings = nameReadings;
        this.mJlpt = jlpt;
        this.mUnicodeKanji = unicodeKanji;
        this.mImageResource = imageResource;
    }

    //Parcelable
    public KanjiItem(Parcel in) {
        mKanji = in.readString();
        if (in.readByte() == 0) {
            mGrade = null;
        } else {
            mGrade = in.readInt();
        }
        if (in.readByte() == 0) {
            mStrokeCount = null;
        } else {
            mStrokeCount = in.readInt();
        }
        mMeanings = in.createStringArray();
        mHeisig_en = in.readString();
        mKunyomiReading = in.createStringArray();
        mOnyomiReading = in.createStringArray();
        mNameReadings = in.createStringArray();
        if (in.readByte() == 0) {
            mJlpt = null;
        } else {
            mJlpt = in.readInt();
        }
        mUnicodeKanji = in.readString();
        if (in.readByte() == 0) {
            mImageResource = null;
        } else {
            mImageResource = in.readInt();
        }
    }

    public static final Creator<KanjiItem> CREATOR = new Creator<KanjiItem>() {
        @Override
        public KanjiItem createFromParcel(Parcel in) {
            return new KanjiItem(in);
        }

        @Override
        public KanjiItem[] newArray(int size) {
            return new KanjiItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mKanji);
        if (mGrade == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mGrade);
        }
        if (mStrokeCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mStrokeCount);
        }
        dest.writeStringArray(mMeanings);
        dest.writeString(mHeisig_en);
        dest.writeStringArray(mKunyomiReading);
        dest.writeStringArray(mOnyomiReading);
        dest.writeStringArray(mNameReadings);
        if (mJlpt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mJlpt);
        }
        dest.writeString(mUnicodeKanji);
        if (mImageResource == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mImageResource);
        }
    }

    //Getters
    public String getKanji() {
        return mKanji;
    }

    public Integer getGrade() {
        return mGrade;
    }

    public Integer getStrokeCount() {
        return mStrokeCount;
    }

    public String[] getMeanings() {
        return mMeanings;
    }

    public String getHeisig_en() {
        return mHeisig_en;
    }

    public String[] getKunyomiReading() {
        return mKunyomiReading;
    }

    public String[] getOnyomiReading() {
        return mOnyomiReading;
    }

    public String[] getNameReadings() {
        return mNameReadings;
    }

    public Integer getJlpt() {
        return mJlpt;
    }

    public String getUnicodeKanji() {
        return mUnicodeKanji;
    }

    public Integer getImageResource() {
        return mImageResource;
    }
}
