package com.bigil.jpstudy.ui.dictionary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.dictionary.DictionaryViewModel;

public class DictionaryFragment extends Fragment {

    private DictionaryViewModel dictionaryViewModel;
    //private WebView webViewDictionary;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dictionary, container, false);
        final WebView webViewDictionary = root.findViewById(R.id.webViewDictionary);
        webViewDictionary.setWebViewClient(new WebViewClient());
        WebSettings webSettingsDictionary = webViewDictionary.getSettings();
        webSettingsDictionary.setJavaScriptEnabled(true);
        webViewDictionary.loadUrl("https://akanji.ru/school/kyouiku-year-1");

        return root;
    }


}
