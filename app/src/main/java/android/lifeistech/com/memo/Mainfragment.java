package android.lifeistech.com.memo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Mainfragment extends android.support.v4.app.Fragment{
    private final static String BACKGROUND_COLOR = "background_color";

    public Realm realm;
    public ListView Listview;
    public FloatingActionButton button;


    public static Mainfragment newInstance() {
        Mainfragment frag = new Mainfragment();
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Listview = (ListView)view.findViewById(R.id.ListView);
        button = (FloatingActionButton)view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CreateActivity.class);
                startActivity(intent);
            }
        });

        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Money money = (Money) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("time", money.time);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.fragment_main_linearlayout);

        return view;
    }

    public  void setMemoList(){
        RealmResults<Money> results = realm.where(Money.class).findAll();
        List<Money> item = realm.copyFromRealm(results);
        MoneyAdapter adapter = new MoneyAdapter(getContext(),R.layout.layout_item_memo,item);
        Listview.setAdapter(adapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        setMemoList();
    }
}


