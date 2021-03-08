package com.bigil.jpstudy.ui.highkanji.parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HighKanjiParentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HighKanjiParentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is high kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}