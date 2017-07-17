package com.example.grantas.cassio.Tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.grantas.cassio.FragmentLogic.FoodLogLogic;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Grantas on 2017-07-16.
 */

public class LogItemListAdapter extends BaseAdapter {

    Context context;
    List<LogItem> items;
    FoodLogLogic Logic;


    public LogItemListAdapter(Context context, List<LogItem> items)
    {
        this.context = context;
        Logic = new FoodLogLogic(context);
        this.items = items;
    }

    @Override
    public int getCount() {

     if(items!= null)   return items.size();
        else return 0;
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LogItem item = items.get(position);
        View view = convertView;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.log_item_info, parent, false);
        }

        ImageView trashcan = (ImageView)view.findViewById(R.id.redtrashcan);
        trashcan.setImageResource(R.drawable.red_trash_can);

        trashcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.deleteLogItem(item.logId);
                UpdateAdapter(Logic.getLogItems());
            }
        });

        ((TextView)view.findViewById(R.id.logiteminfoname)).setText(item.Fooditem.Name);
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm", Locale.getDefault());
        ((TextView)view.findViewById(R.id.logiteminfodate)).setText(sdfDate.format(item.Time));
        ((TextView)view.findViewById(R.id.logiteminfocalories)).setText(item.Fooditem.Calories + " kal.");
        ((TextView)view.findViewById(R.id.logiteminfograms)).setText(item.Grams + " g.");

        return view;

    }

    public void UpdateAdapter(List<LogItem> list)
    {
        this.items = list;
        notifyDataSetChanged();
    }


}
