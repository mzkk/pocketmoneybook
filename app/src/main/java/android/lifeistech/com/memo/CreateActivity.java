package android.lifeistech.com.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    public void save(final int num, final String updateDate, final String content) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Money money = realm.createObject(Money.class);
                money.money = num;
                money.updateDate = updateDate;
                money.content = content;
            }
        });
    }

    public void create(View view) {

        int money = Integer.parseInt(titleText.getText().toString());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate = sdf.format(date);

        String content = contentText.getText().toString();

        //check(money, updateDate, content);

        save(money, updateDate, content);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    private void check(String updateDate, String content) {
        Money money = new Money();
        money.money = 0;
        money.updateDate = updateDate;
        money.content = content;

        Log.d("Money", String.valueOf(money.money));
        Log.d("Money", money.content);
    }
}