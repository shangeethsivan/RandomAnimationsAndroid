package com.shangeeth.animationtypes.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;

import com.shangeeth.animationtypes.R;
import com.shangeeth.animationtypes.adapters.CustomGridAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ControllerFragment extends Fragment {

    Context mContext;
    GridView mGridView;
    public ControllerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View lView = inflater.inflate(R.layout.fragment_controller,container,false);


        mGridView = (GridView) lView.findViewById(R.id.grid_view);
        mGridView.setAdapter(new CustomGridAdapter(mContext,getResources().getStringArray(R.array.animation_types)));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LocalBroadcastManager.getInstance(mContext)
                        .sendBroadcast(new Intent().setAction(DisplayFragment.CUSTOM_INTENT)
                                .putExtra(getString(R.string.animation_type),position));
            }
        });

        return lView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
