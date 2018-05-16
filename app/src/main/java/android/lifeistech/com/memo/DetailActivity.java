package android.lifeistech.com.memo;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {

    public Realm realm;
    public EditText titleText;
    public EditText contentText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();
        titleText = (EditText)findViewById(R.id.titleEditText);
        contentText = (EditText)findViewById(R.id.contentEditText);
        imageView = (ImageView)findViewById(R.id.mizu);

        showDate();
    }

    private void showDate() {

        final Money money = realm.where(Money.class).equalTo("time",
                getIntent().getStringExtra("time")).findFirst();

        titleText.setText(""+money.okane);
        contentText.setText(money.memo);
    }

    public void kesu(View view){

        Money money = realm.where(Money.class).equalTo("time",
                getIntent().getStringExtra("time")).findFirst();

        int num = Integer.parseInt(titleText.getText().toString());

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        int syunyu = data.getInt("Syunyu",  0);
        int sisyutu = data.getInt("Sisyutu",0 );
        int zandaka = data.getInt("Zandaka",0);

        SharedPreferences.Editor editor = data.edit();
        if(money.type==Money.OUT) {
            editor.putInt("Zandaka", zandaka + num);
            editor.putInt("Sisyutu",sisyutu+num);
        }else {
            editor.putInt("Zandaka", zandaka - num);
            editor.putInt("Syunyu",syunyu-num);
        }
        editor.commit();


        realm.beginTransaction();
            money.deleteFromRealm();
            realm.commitTransaction();

            finish();
        }

    public void update(View view) {

        final Money money =
                realm.where(Money.class).equalTo("time",
                getIntent().getStringExtra("time")).findFirst();
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        int mae = money.okane;
        int kousin = Integer.parseInt(titleText.getText().toString());

        int syunyu = data.getInt("Syunyu",0);
        int sisyutu = data.getInt("Sisyutu",0 );
        int zandaka = data.getInt("Zandaka",0);

        SharedPreferences.Editor editor = data.edit();
        if(money.type==Money.OUT) {
            editor.putInt("Zandaka", zandaka + mae-kousin);
            editor.putInt("Sisyutu",sisyutu -mae + kousin);
        }else {
            editor.putInt("Zandaka", zandaka - mae+ kousin);
            editor.putInt("Syunyu",syunyu-mae+ kousin);
        }
        editor.commit();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}