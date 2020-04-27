package ru.omsu.imit.androidcourse.utils;

import android.app.Activity;
import android.content.Intent;

import ru.omsu.imit.androidcourse.R;

/**
 * Created by User on 16.12.2019.
 */

public class ThemeUtils {
    public static int THEME = 0;
    public final static int LIGHT = 0;
    public final static int DARK = 1;

    public static void changeToTheme(Activity activity, int theme) {
        THEME = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (THEME) {
            default:
            case LIGHT:
                activity.setTheme(R.style.AppTheme);
                break;

            case DARK:
                activity.setTheme(R.style.AppThemeDark);
                break;
        }
    }
}
