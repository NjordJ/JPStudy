package com.bigil.jpstudy.ui.middlekanji.parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MiddleKanjiParentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MiddleKanjiParentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is middle kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}