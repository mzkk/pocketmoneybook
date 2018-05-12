package android.lifeistech.com.memo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
