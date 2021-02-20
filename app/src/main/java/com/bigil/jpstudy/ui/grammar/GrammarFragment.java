package com.bigil.jpstudy.ui.grammar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.grammar.GrammarViewModel;

public class GrammarFragment extends Fragment {

    private GrammarViewModel grammarViewModel;
    private WebView webViewGrammar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        grammarViewModel =
                ViewModelProviders.of(this).get(GrammarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grammar, container, false);
        final WebView webView = root.findViewById(R.id.webViewGrammar);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://vandal.sdf-eu.org/JapaneseGuide/index.html");
        /*homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

}
