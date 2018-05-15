package android.lifeistech.com.memo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class CreateActivity extends AppCompatActivity {

    public Realm realm;

    public EditText titleText;
    public EditText contentText;
    public ImageView imageView;
    public ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        realm = Realm.getDefaultInstance();

        titleText = (EditText) findViewById(R.id.titleEditText);
        contentText = (EditText) findViewById(R.id.contentEditText);
        imageView = (ImageView) findViewById(R.id.mizu);



        }

    public void save(final int num, final String time, final String content, final int type) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Money money = realm.createObject(Money.class);
                money.okane = num;
                money.time = time;
                money.memo = content;
                money.type=type;
            }
        });
    }

    public void hairu(View view) {
        imageButton = (ImageButton)findViewById(R.id.button);

        int okane = Integer.parseInt(titleText.getText().toString());

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int zandaka = data.getInt("Zandaka",0 );
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("Zandaka", zandaka + okane);
        editor.apply();

        int syunyu = data.getInt("Syunyu",0 );
        editor.putInt("Syunyu", syunyu + okane);
        editor.apply();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String time = sdf.format(date);

        String content = contentText.getText().toString();

        //check(money, updateDate, content);

        save(okane, time, content,Money.INCOME);



        finish();
    }

    public void deru(View view) {
        imageButton = (ImageButton)findViewById(R.id.button2);

        int okane = Integer.parseInt(titleText.getText().toString());

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int zandaka = data.getInt("Zandaka",0 );
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("Zandaka", zandaka - okane);
        editor.apply();

        int sisyutu = data.getInt("Sisyutu",0 );
        editor.putInt("Sisyutu", sisyutu + okane);
        editor.apply();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String time = sdf.format(date);

        String content = contentText.getText().toString();

        //check(money, updateDate, content);

        save(okane, time, content,Money.OUT);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        run();

    }

    public void run() {
        //startTranslateUp();
        // TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
        final TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0.0f,
                Animation.ABSOLUTE, -500.0f,
                Animation.ABSOLUTE, 0.0f,
                Animation.ABSOLUTE, 0.0f);

        //1000が1秒
        translateAnimation.setDuration(1000);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(translateAnimation);

    }

    /*private void check(String updateDate, String content) {
        Money money = new Money();
        money.okane = 0;
        money.time = updateDate;
        money.memo = content;

        Log.d("okane", String.valueOf(money.okane));
        Log.d("okane", money.memo);
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }


    }

