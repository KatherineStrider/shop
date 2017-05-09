package com.example.kate.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Kate on 26.04.2017.
 */

public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] items;
    private String[] counts;

    public ListViewAdapter(Context context, String[] items, String[] counts){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.counts = counts;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_shop_list, parent, false);
        }
        TextView item = (TextView) convertView.findViewById(R.id.textItem);
        TextView count = (TextView) convertView.findViewById(R.id.textCount);
        item.setText(items[position]);

        String[] arr = counts[position].split("\\s");
        String s = arr[0] + "\n" + arr[1];
        count.setText(s);
        return convertView;
    }
}
