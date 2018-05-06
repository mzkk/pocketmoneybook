package android.lifeistech.com.memo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Homefragment extends android.support.v4.app.Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public EditText ZankinText;
    public EditText HairuText;
    public EditText DeruText;


    public static Homefragment newInstance() {
        Homefragment frag = new Homefragment();
        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        return view;

    }


}

