package com.bigil.jpstudy.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "kanji_data")
public class Kanji {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String kanji;
    private Integer grade;
    private Integer strokeCount;
    private String [] meanings;
    private String [] kunyomiReading;
    private String [] onyomiReading;
    private String [] nameReadings;
    private Integer jlpt;
    private String unicodeKanji;

    //Constructor

    public Kanji(String kanji, Integer grade, Integer strokeCount, String[] meanings,
                 String[] kunyomiReading, String[] onyomiReading, String[] nameReadings,
                 Integer jlpt, String unicodeKanji)
    {
        this.kanji = kanji;
        this.grade = grade;
        this.strokeCount = strokeCount;
        this.meanings = meanings;
        this.kunyomiReading = kunyomiReading;
        this.onyomiReading = onyomiReading;
        this.nameReadings = nameReadings;
        this.jlpt = jlpt;
        this.unicodeKanji = unicodeKanji;
    }


    //Getters
    public Integer getId() {
        return id;
    }

    public String getKanji() {
        return kanji;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getStrokeCount() {
        return strokeCount;
    }

    public String[] getMeanings() {
        return meanings;
    }

    public String[] getKunyomiReading() {
        return kunyomiReading;
    }

    public String[] getOnyomiReading() {
        return onyomiReading;
    }

    public String[] getNameReadings() {
        return nameReadings;
    }

    public Integer getJlpt() {
        return jlpt;
    }

    public String getUnicodeKanji() {
        return unicodeKanji;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }


}
