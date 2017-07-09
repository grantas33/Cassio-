package com.example.grantas.cassio.Tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.grantas.cassio.R;

/**
 * Created by Grantas on 2017-07-07.
 * Klase, padedanti isdestyti paveiksliukus GridView
 */

public class ImageAdapter extends BaseAdapter {

    Context context;

    int[] thumbIds = {
            R.drawable.banana, R.drawable.apple, R.drawable.orange,
            R.drawable.slicewatermelon, R.drawable.pear, R.drawable.cucumber,
            R.drawable.tomato, R.drawable.loafofbread, R.drawable.potato,
            R.drawable.buckwheat, R.drawable.pastapng, R.drawable.curd,
            R.drawable.egg, R.drawable.cheesecurdsnack, R.drawable.pizzapng,
            R.drawable.souppng,


    };

    public ImageAdapter(Context c)
    {
        context = c;
    }

    @Override
    public int getCount() {
        return thumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null)
        {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            //imageView.SetScaleType(ImageView.ScaleType.CenterCrop);
            //imageView.SetPadding(50, 50, 50, 50);
        }
        else
        {
            imageView = (ImageView)convertView;
        }

        imageView.setImageResource(thumbIds[position]);
        return imageView;
    }
}
