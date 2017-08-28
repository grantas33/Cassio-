package com.example.grantas.cassio.Tools;

import android.content.Context;
import android.content.res.Resources;
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
            R.drawable.cucumber, R.drawable.pineapple, R.drawable.orange, R.drawable.slicewatermelon,
            R.drawable.banana, R.drawable.kiwi, R.drawable.pear, R.drawable.tangerine, R.drawable.mango,
            R.drawable.melon, R.drawable.apple, R.drawable.peach, R.drawable.tomato, R.drawable.plum

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
            final float scale = context.getResources().getDisplayMetrics().density;
            int dpWidthInPx  = (int) (80 * scale + 0.5f);
            int dpHeightInPx = (int) (80 * scale + 0.5f);
            imageView.setLayoutParams(new GridView.LayoutParams(dpWidthInPx, dpHeightInPx));
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
