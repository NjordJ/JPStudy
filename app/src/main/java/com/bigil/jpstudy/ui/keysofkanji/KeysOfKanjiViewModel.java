package com.bigil.jpstudy.ui.keysofkanji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KeysOfKanjiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public KeysOfKanjiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is keys of kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}