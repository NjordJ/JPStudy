package com.bigil.jpstudy.ui.grammar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.dictionary.DictionaryViewModel;
import com.bigil.jpstudy.ui.grammar.GrammarViewModel;

import java.util.Locale;


public class GrammarFragment extends Fragment{

    private GrammarViewModel grammarViewModel;
    private static WebView webViewGrammar;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:{
                    webViewGoBack();
                }break;
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_grammar, container, false);

        String language = Locale.getDefault().toString();

        webViewGrammar = root.findViewById(R.id.webViewGrammar);

        webViewGrammar.getSettings().setJavaScriptEnabled(true);
        webViewGrammar.setWebViewClient(new WebViewClient());
        switch (language){
            case "en_US":
                webViewGrammar.loadUrl("http://www.guidetojapanese.org/learn/grammar/writing");
                break;
            case "ru_RU":
                webViewGrammar.loadUrl("https://vandal.sdf-eu.org/JapaneseGuide/index.html");
                break;
        }
        webViewGrammar.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webViewGrammar.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });

        return root;
    }

    private void webViewGoBack(){
        webViewGrammar.goBack();
    }

}
