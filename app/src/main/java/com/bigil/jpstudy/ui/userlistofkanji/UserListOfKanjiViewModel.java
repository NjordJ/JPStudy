package com.bigil.jpstudy.ui.userlistofkanji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserListOfKanjiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserListOfKanjiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is user list of kanji fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}