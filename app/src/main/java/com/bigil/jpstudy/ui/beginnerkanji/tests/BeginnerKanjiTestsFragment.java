package com.bigil.jpstudy.ui.beginnerkanji.tests;

import android.content.Intent;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.parent.BeginnerKanjiParentFragment;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class BeginnerKanjiTestsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_beginnerkanji_tests, container, false);

        BeginnerKanjiParentFragment beginnerKanjiParentFragment = new BeginnerKanjiParentFragment();

        ArrayList<KanjiItem> kanjiBeginnerArrayList = beginnerKanjiParentFragment.kanjiBeginnerItemArrayList;

        return root;
    }


}