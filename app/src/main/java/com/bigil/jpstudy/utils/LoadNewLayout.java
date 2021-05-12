package com.bigil.jpstudy.utils;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.ui.beginnerkanji.info.BeginnerKanjiInfoFragment;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.util.ArrayList;

public class LoadNewLayout {

    public void LoadNewFragmentWithArrayList(Context context, String nameForData, ArrayList<Object> arrayList, Fragment fragmentB){

        //using Bundle to send data
        Bundle bundle = new Bundle();
        bundle.putParcelable(nameForData, (Parcelable) arrayList);
        fragmentB.setArguments(bundle);

        ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, fragmentB)
                        .addToBackStack(null)
                        .commit();

    }

}
