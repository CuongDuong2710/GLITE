package com.glite.popeyes.view.menu.our_menu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.glite.popeyes.R;
import com.glite.popeyes.view.menu.our_menu.model.AddOnItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QUOC CUONG on 11/10/2016.
 */
public class SpinnerAddOnAdapter extends BaseAdapter{
    /***
     * Value of spinner
     */
    private static final Integer VALUE_1 = 1;
    private static final Integer VALUE_2 = 2;
    private static final Integer VALUE_3 = 3;
    private static final Integer VALUE_4 = 4;
    private static final Integer VALUE_5 = 5;

    private Context context;
    private List<AddOnItem> items = new ArrayList<>();
    private LayoutInflater layoutInflater;

    private ArrayAdapter<Integer> mAdapterSpinner;
    private ArrayList<Integer> mValueSpinnerList = new ArrayList<>();

    public SpinnerAddOnAdapter(Context context, List<AddOnItem> items) {
        this.context = context;
        this.items = items;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.expanded_spinner_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AddOnItem item = items.get(position);

        holder.txtSpinner.setText(item.getTextAddOnName());

        mAdapterSpinner = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, mValueSpinnerList);
        holder.spinner.setAdapter(mAdapterSpinner);

        return convertView;
    }

    public static class ViewHolder {

        TextView txtSpinner;
        Spinner spinner;

        public ViewHolder(View itemView) {
            txtSpinner = (TextView) itemView.findViewById(R.id.text_spinner_add_on);
            spinner = (Spinner) itemView.findViewById(R.id.spinner_add_on);
        }
    }

    public void addValueSpinner(int minimum, int maximum) {
        for (int i = minimum; i <= maximum; i++) {
            mValueSpinnerList.add(i);
        }
    }
}
