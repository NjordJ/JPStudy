package com.bigil.jpstudy.ui.kana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.utils.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KanaFragment extends Fragment {

    private KanaViewModel kanaViewModel;

    //RecyclerView for show information
    private RecyclerView mRecyclerViewKana;
    private KanaAdapter mAdapterKana;
    private RecyclerView.LayoutManager mLayoutManagerKana;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        kanaViewModel =
                ViewModelProviders.of(this).get(KanaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_kana, container, false);

            JSONUtils jsonUtils = new JSONUtils();

            ArrayList<KanaItem> kanaItemArrayList = new ArrayList<>();

            try {
                JSONObject rootJson = new JSONObject(jsonUtils.JsonDataFromAsset(getContext(), "japanese_alphabet.json"));
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

            mAdapterKana.setOnItemClickListener(new KanaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //Toast.makeText(getActivity().getApplicationContext(), "Successful click", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStartStudyClick(int position) {

                }
            });

            return root;


        }

}

