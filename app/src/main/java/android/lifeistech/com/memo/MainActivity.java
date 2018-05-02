package android.lifeistech.com.memo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public Realm realm;
    public ListView Listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        Listview = (ListView)findViewById(R.id.ListView);

        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Money money = (Money) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

    }

    public  void setMemoList(){
        RealmResults<Money> results = realm.where(Money.class).findAll();
        List<Money> item = realm.copyFromRealm(results);
        MoneyAdapter adapter = new MoneyAdapter(this,R.layout.layout_item_memo,item);
        Listview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMemoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void create(View view){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}


