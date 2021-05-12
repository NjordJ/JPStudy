package com.bigil.jpstudy.utils;

import com.bigil.jpstudy.models.KanjiItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class KanjiStudyTests {

    //public static ArrayList<KanjiItem> kanjiItems = new ArrayList<>();
    public static Integer n;
    private static ArrayList<String> tempRandomKanjis;

    public static String ChooseAllBeginnerKanji(ArrayList<KanjiItem> kanjiBeginnerArrayList){
        boolean isWasKanji = kanjiHasDuplicates(kanjiBeginnerArrayList);

        Random random = new Random();
        Integer number = random.nextInt();
        Integer index = Integer.valueOf(String.valueOf(kanjiBeginnerArrayList.size()));

        for(Integer i = 0; i < kanjiBeginnerArrayList.size(); i++){

            i++;
        }
        return String.valueOf(kanjiBeginnerArrayList);
    }

    private static boolean kanjiHasDuplicates(ArrayList<KanjiItem> placeList){
        for (int i=0; i<placeList.size(); i++) {
            KanjiItem firstPlaceCell = placeList.get(i);
            for (int j=i+1; j<placeList.size(); j++) {
                KanjiItem secondPlaceCell = placeList.get(j);
                if (Objects.equals(firstPlaceCell.getKanji(), secondPlaceCell.getKanji())) {
                    return true;
                }
            }
        }
        return false;
    }

}

