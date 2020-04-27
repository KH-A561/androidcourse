package ru.omsu.imit.androidcourse;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Locale;
import java.util.Random;

import ru.omsu.imit.androidcourse.utils.LocaleUtils;
import ru.omsu.imit.androidcourse.utils.ThemeUtils;

import static ru.omsu.imit.androidcourse.utils.LocaleUtils.languageToLoad;

public class MainActivity extends AppCompatActivity {
    Menu optionsMenu;
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Cat channel";
    private SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    private int[] soundArray = new int[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        soundArray[0] = soundPool.load(getApplicationContext(), R.raw.audio1, 1);
        soundArray[1] = soundPool.load(getApplicationContext(), R.raw.audio2, 1);
        soundArray[2] = soundPool.load(getApplicationContext(), R.raw.audio3, 1);
        soundArray[3] = soundPool.load(getApplicationContext(), R.raw.audio4, 1);
        soundArray[4] = soundPool.load(getApplicationContext(), R.raw.audio5, 1);
        soundArray[5] = soundPool.load(getApplicationContext(), R.raw.audio6, 1);

        Button button_map = (Button) findViewById(R.id.button_map);
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button button_notification = (Button) findViewById(R.id.button_notification);
        button_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Уведомление")
                                .setContentText("Вам пришло уведомление!")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setAutoCancel(true);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(NOTIFY_ID, builder.build());

                Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                        0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Уведомление")
                                .setContentText("И ещё одно.")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .addAction(R.drawable.common_google_signin_btn_icon_light, "Открыть", contentIntent)
                                .setColor(Color.GREEN)
                                .setAutoCancel(true);
                notificationManager.notify(NOTIFY_ID + 1, builder.build());
            }
        });

        Button button_street = (Button) findViewById(R.id.button_street);
        button_street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUriString = "google.streetview:cbll=55.0285503,73.2613842&cbp=1,99.56,,1,2.0&mz=19";
                Uri geoUri = Uri.parse(geoUriString);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        Button button_toast = (Button) findViewById(R.id.button_toast);
        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        R.string.toast,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        Button button_localization = (Button) findViewById(R.id.button_localization);
        button_localization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleUtils.changeLocale(MainActivity.this);
            }
        });

        final Button button_sounds = (Button) findViewById(R.id.button_sounds);
        button_sounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int randNum = rand.nextInt(6);
                playSound(soundArray[randNum]);
                YoYo.with(Techniques.Shake)
                        .duration(500)
                        .playOn(button_sounds);
            }
        });

        Button button_dialogue = (Button) findViewById(R.id.button_dialogue);
        button_dialogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                MyDialogFragment myDialogFragment = new MyDialogFragment();
                FragmentTransaction transaction = manager.beginTransaction();
                myDialogFragment.show(transaction, "dialog");
            }
        });

        Button button_manual = (Button) findViewById(R.id.button_manual);
        button_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ManualThemesActivity.class);
                startActivity(intent);
            }
        });

        Button button_text_editor = (Button) findViewById(R.id.button_text_editor);
        button_text_editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TextEditorActivity.class);
                startActivity(intent);
            }
        });

        Button button_animation = (Button) findViewById(R.id.button_animation);
        button_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void playSound(int soundId) {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0,1.0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        optionsMenu = menu;
        if (ThemeUtils.THEME == ThemeUtils.LIGHT) {
            optionsMenu.getItem(0).setChecked(true);
        } else {
            optionsMenu.getItem(1).setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MenuItem otherItem;
        switch (id) {
            case R.id.action_dark:
                otherItem = optionsMenu.getItem(1);
                item.setChecked(true);
                otherItem.setChecked(false);
                ThemeUtils.changeToTheme(this, ThemeUtils.DARK);
                return true;

            case R.id.action_light:
                otherItem = optionsMenu.getItem(0);
                item.setChecked(true);
                otherItem.setChecked(false);
                ThemeUtils.changeToTheme(this, ThemeUtils.LIGHT);
                return true;
//            default:
//                return super.onOptionsItemSelected(item);
        }
        return false;
    }
}
