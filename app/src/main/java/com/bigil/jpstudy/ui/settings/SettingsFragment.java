package com.bigil.jpstudy.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.bigil.jpstudy.R;

public class SettingsFragment extends PreferenceFragmentCompat{

    private static final String EMAIL_BUGREPORT = "jpstudy.issue@gmail.com";
    private static final String EMAIL_FEEDBACK = "jpstudy.feedback@gmail.com";
    private String[] emails = {EMAIL_BUGREPORT};

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();

        switch (key) {
            case "resetScore":

                Toast.makeText(getContext(), "Successful score reset", Toast.LENGTH_SHORT).show();
                break;
            case "feedback":
                Intent sendIntentFeedBack = new Intent(Intent.ACTION_SENDTO);
                sendIntentFeedBack.setData(Uri.parse("mailto:"+EMAIL_FEEDBACK));
                startActivity(Intent.createChooser(sendIntentFeedBack, null));
                break;
            case "bugReport":
                Intent sendIntentBugReport = new Intent(Intent.ACTION_SENDTO);
                sendIntentBugReport.setData(Uri.parse("mailto:"+EMAIL_BUGREPORT));
                startActivity(Intent.createChooser(sendIntentBugReport, null));
                break;
        }
        return super.onPreferenceTreeClick(preference);
    }
}