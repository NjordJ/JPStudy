package com.bigil.jpstudy.ui.dictionary;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.ui.dictionary.DictionaryViewModel;

public class DictionaryFragment extends Fragment {

    private DictionaryViewModel dictionaryViewModel;
    private static WebView webViewDictionary;

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

        View root = inflater.inflate(R.layout.fragment_dictionary, container, false);

        webViewDictionary = root.findViewById(R.id.webViewDictionary);

        webViewDictionary.getSettings().setJavaScriptEnabled(true);
        webViewDictionary.setWebViewClient(new WebViewClient());
        webViewDictionary.loadUrl("https://akanji.ru/school/kyouiku-year-1");
        webViewDictionary.setOnKeyListener(new View.OnKeyListener(){

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && webViewDictionary.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }

                return false;
            }

        });

        return root;
    }

    private void webViewGoBack(){
        webViewDictionary.goBack();
    }


}
