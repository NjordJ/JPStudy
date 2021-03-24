package com.bigil.jpstudy.repositories;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.bigil.jpstudy.models.Kanji;

import java.util.List;


@Dao
public interface KanjiDao {

    @Insert
    void insert(Kanji kanji);

    @Delete
    void delete(Kanji kanji);

    //Show kanjis
    @Query("SELECT * FROM kanji_data")
    LiveData<List<Kanji>> getAllKanjis();

}
