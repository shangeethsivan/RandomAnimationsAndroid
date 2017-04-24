package com.shangeeth.animationtypes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shangeeth.animationtypes.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 24/04/17.
 */

public class CustomGridAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<String> mAnimationTypes;

    public CustomGridAdapter(Context pContext, ArrayList<String> pAnimationTypes) {
        this.mContext = pContext;
        this.mAnimationTypes = pAnimationTypes;

    }

    @Override
    public int getCount() {
        return mAnimationTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return mAnimationTypes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View lView;

        if (convertView == null) {
            LayoutInflater lLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            lView = lLayoutInflater.inflate(R.layout.grid_item, null);
        } else
            lView = convertView;

        ((TextView) lView.findViewById(R.id.animation_type_tv)).setText(mAnimationTypes.get(position));

        return lView;
    }
}
