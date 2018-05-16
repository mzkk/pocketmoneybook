package android.lifeistech.com.memo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.List;
import java.util.ServiceLoader;

import io.realm.Realm;
import io.realm.RealmResults;

public class Homefragment extends android.support.v4.app.Fragment {
    private final static String BACKGROUND_COLOR = "background_color";

    public TextView ZankinText;
    public TextView HairuText;
    public TextView DeruText;
    ImageButton imageButton;

    public static Homefragment newInstance() {
        Homefragment frag = new Homefragment();
        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        ZankinText = (TextView) view.findViewById(R.id.Zankin);
        HairuText = (TextView) view.findViewById(R.id.Hairu);
        DeruText = (TextView) view.findViewById(R.id.Deru);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageButton = (ImageButton) view.findViewById(R.id.kosin);

        imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent refresh = new Intent(getContext(), FragmentActivity.class);
                        startActivity(refresh);
                    }
                });

        updateView();

    }

    public void updateView (){
        if (getContext() != null) {
            SharedPreferences data = getContext().getSharedPreferences("DataSave", Context.MODE_PRIVATE);
            int zandaka = data.getInt("Zandaka", 0);
            ZankinText.setText(String.valueOf("¥" + zandaka));

            int syunyu = data.getInt("Syunyu", 0);
            HairuText.setText(String.valueOf("¥ +" + syunyu));

            int sisyutu = data.getInt("Sisyutu", 0);
            DeruText.setText(String.valueOf("¥ -" + sisyutu));
        }
    }
}


