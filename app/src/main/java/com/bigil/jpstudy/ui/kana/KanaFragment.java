package com.bigil.jpstudy.ui.kana;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bigil.jpstudy.R;
import com.bigil.jpstudy.models.KanaItem;
import com.bigil.jpstudy.utils.JSONUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KanaFragment extends Fragment implements View.OnClickListener {

    //Classes
    JSONUtils jsonUtils = new JSONUtils();

    //Variables
    private ArrayList<KanaItem> kanaItemArrayList = new ArrayList<>();

    //RecyclerView for show information
    private RecyclerView mRecyclerViewKana;
    private KanaAdapter mAdapterKana;
    private RecyclerView.LayoutManager mLayoutManagerKana;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kana, container, false);

        Button buttonStartLearningHiragana = root.findViewById(R.id.buttonStartLearningHiragana);
        Button buttonStartLearningKatakana = root.findViewById(R.id.buttonStartLearningKatakana);

        Resources resources = this.getResources();
            try {
                JSONObject rootJson = new JSONObject(jsonUtils.JsonDataFromAsset(getContext(), "japanese_alphabet.json"));
                JSONArray jsonArray = rootJson.getJSONArray("kana");
                for (int index = 0; index < jsonArray.length(); index++){
                    JSONObject hiraganaData = jsonArray.getJSONObject(index);
                    String hiragana = hiraganaData.getString("hiragana");
                    String katakana = hiraganaData.getString("katakana");
                    String transcription = hiraganaData.getString("transcription");
                    //Image name from drawable folder
                    String imageNameHiragana = "kn_"+stringToUnicode(hiragana);
                    String imageNameKatakana = "kn_"+stringToUnicode(katakana);
                    //Get resource by imageName from drawable
                    final int resourceIdHiragana = resources.getIdentifier(imageNameHiragana, "drawable", getContext().getPackageName());
                    final int resourceIdKatakana = resources.getIdentifier(imageNameKatakana, "drawable", getContext().getPackageName());

                    kanaItemArrayList.add(new KanaItem(hiragana, katakana, transcription, resourceIdHiragana, resourceIdKatakana));
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
                    //using Bundle to send data
                    Fragment fragmentKanaInfoFragment = new KanaInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("KanaItemData", kanaItemArrayList.get(position));
                    fragmentKanaInfoFragment.setArguments(bundle);

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                            .replace(R.id.nav_host_fragment, fragmentKanaInfoFragment)
                            .addToBackStack(null)
                            .commit();
                    //Toast.makeText(getActivity().getApplicationContext(), "Successful click", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onStartStudyClick(int position) {

                }
            });

            buttonStartLearningHiragana.setOnClickListener(this);
            buttonStartLearningKatakana.setOnClickListener(this);

            return root;
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonStartLearningHiragana:
                //using Bundle to send data
                Fragment fragmentKanaHiraganaTestsFragment = new KanaHiraganaTestsFragment();
                Bundle bundleHiragana = new Bundle();
                bundleHiragana.putParcelableArrayList("KanaDataTests", kanaItemArrayList);
                fragmentKanaHiraganaTestsFragment.setArguments(bundleHiragana);

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                        .replace(R.id.nav_host_fragment, fragmentKanaHiraganaTestsFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.buttonStartLearningKatakana:
                //using Bundle to send data
                Fragment fragmentKanaKatakanaTestsFragment = new KanaKatakanaTestsFragment();
                Bundle bundleKatakana = new Bundle();
                bundleKatakana.putParcelableArrayList("KanaDataTests", kanaItemArrayList);
                fragmentKanaKatakanaTestsFragment.setArguments(bundleKatakana);

                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                        .replace(R.id.nav_host_fragment, fragmentKanaKatakanaTestsFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    //Convert kana to unicode
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // Take out every character
            char c = string.charAt(i);
            // Convert to unicode
            //"\\u is just a code name, please add corresponding symbols according to specific needs"
            //unicode.append("\\u" + Integer.toHexString(c));
            unicode.append(Integer.toHexString(c));
        }
        System.out.println("stringToUnicode: " + unicode);
        return unicode.toString();
    }
}

