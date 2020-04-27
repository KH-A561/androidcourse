package ru.omsu.imit.androidcourse.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by User on 17.12.2019.
 */

public class LocaleUtils {
    public static String languageToLoad  = "ru";

    public static void changeLocale(Activity activity) {
        if (languageToLoad.equals("ru")) {
            languageToLoad = "en";
        } else {
            languageToLoad = "ru";
        }
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

}
