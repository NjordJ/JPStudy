package com.bigil.jpstudy.ui.userlistofkanji;

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

public class UserListOfKanjiFragment extends Fragment {

    private UserListOfKanjiViewModel userListOfKanjiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userListOfKanjiViewModel =
                ViewModelProviders.of(this).get(UserListOfKanjiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        userListOfKanjiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}