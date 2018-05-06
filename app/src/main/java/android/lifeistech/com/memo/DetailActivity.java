package android.lifeistech.com.memo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    public Realm realm;
    public EditText titleText;
    public EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();
        titleText = (EditText)findViewById(R.id.titleEditText);
        contentText = (EditText)findViewById(R.id.contentEditText);

        showDate();
    }

    private void showDate() {

        final Money money = realm.where(Money.class).equalTo("time",
                getIntent().getStringExtra("time")).findFirst();

        titleText.setText(""+money.okane);
        contentText.setText(money.memo);
    }

    public void kesu(View view){

    }

    public void update(View view) {

        final Money money = realm.where(Money.class).equalTo("time",
                getIntent().getStringExtra("time")).findFirst();

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                money.okane = Integer.parseInt(titleText.getText().toString());
                money.memo = contentText.getText().toString();
            }
        });

        int kousin = Integer.parseInt(titleText.getText().toString());

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int zandaka = data.getInt("Zandaka",0 );
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("Zandaka", kousin - zandaka);
        editor.apply();

        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}