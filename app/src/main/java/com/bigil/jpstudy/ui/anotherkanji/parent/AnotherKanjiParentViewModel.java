package com.bigil.jpstudy.ui.anotherkanji.parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AnotherKanjiParentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AnotherKanjiParentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is another kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}