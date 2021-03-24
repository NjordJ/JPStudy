package com.bigil.jpstudy.ui.beginnerkanji.parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BeginnerKanjiParentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BeginnerKanjiParentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is branchfeature beginner kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}