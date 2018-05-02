package android.lifeistech.com.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    public Realm realm;
    public EditText titleEditText;
    public EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);

        showDate();
    }

    private void showDate() {

        final Money money = realm.where(Money.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();

        titleEditText.setText(money.money);
        contentEditText.setText(money.content);
    }

    public void update(View view) {

        final Money money = realm.where(Money.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                money.money = Integer.parseInt(titleEditText.toString());
                money.content = contentEditText.getText().toString();
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