package com.example.partnershop;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StringAdapter extends ArrayAdapter<String> {
    private Typeface font;
    private LayoutInflater inflater;
    private String[] msg;

    public StringAdapter(Context ctx, String[] msg) {
        super(ctx, R.layout.string_item, R.id.item, msg);
        this.inflater = null;
        this.msg = null;
        this.font = null;
        this.inflater = LayoutInflater.from(ctx);
        this.msg = msg;
    }

    public int getCount() {
        return this.msg.length;
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.string_item, null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.item);
        item.setTypeface(this.font);
       // item.setTextColor(Color.parseColor("#ffffffff"));
        item.setText((String) getItem(position));
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
