package com.bigil.jpstudy.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.utils.JSONParsingAsync;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class KanaFragment extends Fragment {

    private KanaViewModel kanaViewModel;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewKana;
    private RecyclerView.Adapter mAdapterKana;
    private RecyclerView.LayoutManager mLayoutManagerKana;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kanaViewModel =
                ViewModelProviders.of(this).get(KanaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kana, container, false);
        final //TextView textView = root.findViewById(R.id.textViewKanaResult);

            JSONParsingAsync jsonParsingAsync = new JSONParsingAsync();

            ArrayList<KanaItem> kanaItemArrayList = new ArrayList<>();

            try {
                JSONObject rootJson = new JSONObject(jsonParsingAsync.JsonDataFromAsset(getContext(), "japanese_alphabet.json"));
                JSONArray jsonArray = rootJson.getJSONArray("kana");
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


            mRecyclerViewKana = root.findViewById(R.id.recyclerViewKana);
            mRecyclerViewKana.setHasFixedSize(true);
            //mLayoutManagerKana = new LinearLayoutManager(getContext());
            mLayoutManagerKana = new GridLayoutManager(getActivity(),5);
            mAdapterKana = new KanaAdapter(kanaItemArrayList);

            mRecyclerViewKana.setLayoutManager(mLayoutManagerKana);
            mRecyclerViewKana.setAdapter(mAdapterKana);

            /*kanaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

            return root;


        }

}

