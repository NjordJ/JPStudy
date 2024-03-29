package com.bigil.jpstudy.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.bigil.jpstudy.R;

public class SettingsFragment extends PreferenceFragmentCompat{

    private static final String EMAIL_BUGREPORT = "jpstudy.issue@gmail.com";
    private static final String EMAIL_FEEDBACK = "jpstudy.feedback@gmail.com";

    private SharedPreferences pref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        InitPreferences();
    }

    public void InitPreferences(){
        pref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();

        switch (key) {
            case "resetScore":
                SharedPreferences.Editor edit = pref.edit();
                edit.remove(getString(R.string.saved_high_score_key));
                edit.apply();
                Toast.makeText(getContext(), R.string.successfulScoreReset_Toast, Toast.LENGTH_SHORT).show();
                break;
            case "feedback":
                Intent sendIntentFeedBack = new Intent(Intent.ACTION_SENDTO);
                sendIntentFeedBack.setData(Uri.parse("mailto:"));
                sendIntentFeedBack.putExtra(Intent.EXTRA_EMAIL, EMAIL_FEEDBACK);
                startActivity(sendIntentFeedBack);
                break;
            case "bugReport":
                String phoneModel = Build.BRAND+ " " + Build.MODEL + "\n________________________________";
                Intent sendIntentBugReport = new Intent(Intent.ACTION_SENDTO);
                sendIntentBugReport.setData(Uri.parse("mailto:"));
                sendIntentBugReport.putExtra(Intent.EXTRA_EMAIL, EMAIL_BUGREPORT);
                sendIntentBugReport.putExtra(Intent.EXTRA_TEXT, phoneModel);
                startActivity(sendIntentBugReport);
                //startActivity(Intent.createChooser(sendIntentBugReport, null));
                break;
        }
        return super.onPreferenceTreeClick(preference);
    }
}