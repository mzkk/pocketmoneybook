package android.lifeistech.com.memo;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

public class MoneyAdapter extends ArrayAdapter<Money> {

    private LayoutInflater layoutinflater;

    MoneyAdapter(Context context, int textViewResourceId, List<Money> objects) {
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Money money = getItem(position);

        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.layout_item_memo, null);
        }

        TextView titleText =(TextView)convertView.findViewById(R.id.titleText);
        TextView contentText = (TextView)convertView.findViewById(R.id.contentText);

        titleText.setText(""+money.okane);
        if(money.type==Money.OUT) {
            titleText.setTextColor(Color.RED);
        }
        else {
            titleText.setTextColor(Color.BLUE);
        }

        contentText.setText(money.memo);

        return convertView;
    }


}
