package com.cassio.app.cassio.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Klase, padedanti isdestyti paveiksliukus GridView
 */

public class ImageAdapter extends BaseAdapter {

    Context context;

    int[] thumbIds;

    public ImageAdapter(Context c, int[] thumbIds) {
        this.thumbIds = thumbIds;
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

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            final float scale = context.getResources().getDisplayMetrics().density;
            int dpWidthInPx = (int) (80 * scale + 0.5f);
            int dpHeightInPx = (int) (80 * scale + 0.5f);
            imageView.setLayoutParams(new GridView.LayoutParams(dpWidthInPx, dpHeightInPx));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(thumbIds[position]);
        return imageView;
    }
}
