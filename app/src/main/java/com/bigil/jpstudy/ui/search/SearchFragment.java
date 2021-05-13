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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanjiItem;
import com.bigil.jpstudy.utils.JSONParsingAsync;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchFragment extends Fragment {

    //Classes
    JSONParsingAsync jsonParsingAsync = new JSONParsingAsync();

    //Variables


    private TextView textViewSearchResult;
    private EditText editText;
    private List<String> kanji;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        textViewSearchResult = root.findViewById(R.id.textViewSearchResult);
        editText = root.findViewById(R.id.editTextSearch);

        String json = jsonParsingAsync.JsonDataFromAsset(getContext(), "kanjiapi_obj.json");
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);

        //ReadContext ctx = JsonPath.read(document, "$..亜");

        //kanji = JsonPath.read(document, "$.kanjis[0]");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textViewSearchResult.setText(String.valueOf(kanji));
            }
        });


        return root;
    }
}