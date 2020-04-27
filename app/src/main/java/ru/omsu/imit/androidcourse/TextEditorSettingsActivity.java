package ru.omsu.imit.androidcourse;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by User on 17.12.2019.
 */

public class TextEditorSettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // загружаем предпочтения из ресурсов
        addPreferencesFromResource(R.xml.preferences);
    }
}
