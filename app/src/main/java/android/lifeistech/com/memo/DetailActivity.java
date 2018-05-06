package android.lifeistech.com.memo;

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

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}