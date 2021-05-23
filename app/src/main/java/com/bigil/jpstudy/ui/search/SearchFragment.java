package com.bigil.jpstudy.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.utils.JSONUtils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;

public class SearchFragment extends Fragment {

    //Classes
    JSONUtils jsonUtils = new JSONUtils();

    //Variables


    private TextView textViewSearchResult;
    private EditText editText;
    private List<String> kanji;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        textViewSearchResult = root.findViewById(R.id.textViewSearchResult);
        editText = root.findViewById(R.id.editTextSearch);

        String json = jsonUtils.JsonDataFromAsset(getContext(), "kanjiapi_obj.json");
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

//        ReadContext ctx = JsonPath.read(document, "$..亜");
//
//        kanji = JsonPath.read(document, "$.kanjis[0]");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textViewSearchResult.setText((CharSequence) kanji);
            }
        });


        return root;
    }
}