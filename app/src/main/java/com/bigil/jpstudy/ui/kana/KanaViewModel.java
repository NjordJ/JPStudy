package com.bigil.jpstudy.ui.kana;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KanaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KanaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is kana fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}