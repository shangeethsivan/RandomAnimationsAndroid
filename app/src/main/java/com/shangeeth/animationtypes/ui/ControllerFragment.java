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
    ArrayList<String> mAnimationTypes;
    public ControllerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View lView = inflater.inflate(R.layout.fragment_controller,container,false);

        mAnimationTypes = new ArrayList<>();
        mAnimationTypes.add("Alpha Animation");
        mAnimationTypes.add("Scale Animation");
        mAnimationTypes.add("Rotate Animation");
        mAnimationTypes.add("Translate with repeat Animation");
        mAnimationTypes.add("Accelerate Interpolator with Alpha Animation");
        mAnimationTypes.add("Bounce Interpolator with Translate Animation");
        mAnimationTypes.add("Accelerate Decelerate Interpolator with Scale Animation");
        mAnimationTypes.add("Anticipate Interpolator with Translate Animation");
        mAnimationTypes.add("Cycle Interpolator with Alpha Animation");
        mAnimationTypes.add("Linear Interpolator with Alpha Animation");
        mAnimationTypes.add("Overshoot Interpolator with Rotate Animation");
        mAnimationTypes.add("Decelerate Interpolator with Translate Animation");
        mAnimationTypes.add("Translate with Scale Animation");
        mAnimationTypes.add("Translate with Rotate Animation");
        mAnimationTypes.add("Translate Animation using Translate animation object");

        mGridView = (GridView) lView.findViewById(R.id.grid_view);
        mGridView.setAdapter(new CustomGridAdapter(mContext,mAnimationTypes));

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
