package android.lifeistech.com.memo;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class CreateActivity extends AppCompatActivity {

    public Realm realm;

    public EditText titleText;
    public EditText contentText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        realm = Realm.getDefaultInstance();

        titleText = (EditText) findViewById(R.id.titleEditText);
        contentText = (EditText) findViewById(R.id.contentEditText);


        }

    public void save(final int num, final String time, final String content) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Money money = realm.createObject(Money.class);
                money.okane = num;
                money.time = time;
                money.memo = content;

            }
        });
    }

    public void hairu(View view) {

        int okane = Integer.parseInt(titleText.getText().toString());



        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String time = sdf.format(date);

        String content = contentText.getText().toString();

        //check(money, updateDate, content);

        save(okane, time, content);
        finish();
    }

    public void deru(View view) {

        int okane = Integer.parseInt(titleText.getText().toString());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String time = sdf.format(date);

        String content = contentText.getText().toString();

        //check(money, updateDate, content);

        save(okane, time, content);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    /*private void check(String updateDate, String content) {
        Money money = new Money();
        money.okane = 0;
        money.time = updateDate;
        money.memo = content;

        Log.d("okane", String.valueOf(money.okane));
        Log.d("okane", money.memo);
    }*/


    }

