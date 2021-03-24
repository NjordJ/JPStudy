package com.bigil.jpstudy.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bigil.jpstudy.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class KanaFragment extends Fragment {

    private KanaViewModel kanaViewModel;

    private RequestQueue mQueue;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewKana;
    private RecyclerView.Adapter mAdapterKana;
    private RecyclerView.LayoutManager mLayoutManagerKana;

    //Json lists
    ArrayList<String> hiraganaList = new ArrayList<>();
    ArrayList<String> transcriptionList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kanaViewModel =
                ViewModelProviders.of(this).get(KanaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kana, container, false);
        final /*TextView textView = root.findViewById(R.id.textViewKanaResult);
        Button buttonParseKana = root.findViewById(R.id.buttonParseKana);*/

                ArrayList<KanaItem> kanaItemArrayList = new ArrayList<>();
        /*kanaItemArrayList.add(new KanaItem("あ", "ア", "a"));
        kanaItemArrayList.add(new KanaItem("い", "イ", "i"));
        kanaItemArrayList.add(new KanaItem("う", "ウ", "u"));
        kanaItemArrayList.add(new KanaItem("え", "エ", "e"));
        kanaItemArrayList.add(new KanaItem("お", "オ", "o"));*/

        try {
            JSONObject jsonObject = new JSONObject(Jsondatafromasset());
            JSONArray jsonArray = jsonObject.getJSONArray("kana");
            for (int index = 0; index < jsonArray.length(); index++){
                JSONObject hiraganaData = jsonArray.getJSONObject(index);
                String hiragana = hiraganaData.getString("hiragana");
                String katakana = hiraganaData.getString("katakana");
                String transcription = hiraganaData.getString("transcription");

                kanaItemArrayList.add(new KanaItem(hiragana, katakana, transcription));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*kanaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

            mRecyclerViewKana = root.findViewById(R.id.recyclerViewKana);
            mRecyclerViewKana.setHasFixedSize(true);
            //mLayoutManagerKana = new LinearLayoutManager(getContext());
            mLayoutManagerKana = new GridLayoutManager(getActivity(),5);
            mAdapterKana = new KanaAdapter(kanaItemArrayList);

            mRecyclerViewKana.setLayoutManager(mLayoutManagerKana);
            mRecyclerViewKana.setAdapter(mAdapterKana);

            return root;


        }

        //Parsing json file
    private String Jsondatafromasset() {
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("hiragana_alphabet.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

}

