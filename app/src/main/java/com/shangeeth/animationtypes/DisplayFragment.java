package com.shangeeth.animationtypes;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    public static final String CUSTOM_INTENT = "com.shangeeth.animation_types.CUSTOM_INTENT";

    BroadcastReceiver mBroadcastReceiver;
    LocalBroadcastManager mLocalBroadcastManager;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Im called", Toast.LENGTH_SHORT).show();
            }
        };

        IntentFilter lIntentFilter = new IntentFilter(CUSTOM_INTENT);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver,lIntentFilter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}
