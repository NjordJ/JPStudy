package com.bigil.jpstudy.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bigil.jpstudy.R;

public class KanaFragment extends Fragment {

    private KanaViewModel kanaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kanaViewModel =
                ViewModelProviders.of(this).get(KanaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kana, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        kanaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}