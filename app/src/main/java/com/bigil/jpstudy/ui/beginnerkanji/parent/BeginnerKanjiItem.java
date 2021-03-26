package com.bigil.jpstudy.ui.beginnerkanji.parent;

public class BeginnerKanjiItem {

    //private Integer id;

    private String mKanji;
    private Integer mGrade;
    private Integer mStrokeCount;
    private String [] mMeanings;
    private String mHeisig_en;
    private String [] mKunyomiReading;
    private String [] mOnyomiReading;
    private String [] mNameReadings;
    private Integer mJlpt;
    private String mUnicodeKanji;

    //Constructor
    public BeginnerKanjiItem(String kanji, Integer grade, Integer strokeCount, String[] meanings,
                 String heisig_en,  String[] kunyomiReading, String[] onyomiReading,
                 String[] nameReadings, Integer jlpt, String unicodeKanji)
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
    }


    //Getters
    /*public Integer getId() {
        return id;
    }*/

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

    //Setters
    /*public void setId(Integer id) {
        this.id = id;
    }*/



}
