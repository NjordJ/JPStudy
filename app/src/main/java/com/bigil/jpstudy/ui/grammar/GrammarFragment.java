package com.bigil.jpstudy.ui.grammar;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import androidx.annotation.NonNull;
import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.grammar.GrammarViewModel;



public class GrammarFragment extends Fragment{

    private GrammarViewModel grammarViewModel;
    //private WebView webViewGrammar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_grammar, container, false);
        final WebView webViewGrammar = root.findViewById(R.id.webViewGrammar);
        webViewGrammar.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewGrammar.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewGrammar.loadUrl("https://vandal.sdf-eu.org/JapaneseGuide/index.html");

        return root;
    }

}
