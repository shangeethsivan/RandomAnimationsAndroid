package com.shangeeth.animationtypes.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.shangeeth.animationtypes.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager lFragmentManager = getSupportFragmentManager();

        FragmentTransaction lFragmentTransaction = lFragmentManager.beginTransaction();

        findViewById(R.id.display_container).getContext();

        lFragmentTransaction.add(R.id.display_container,new DisplayFragment());
        lFragmentTransaction.add(R.id.controller_container,new ControllerFragment());

        lFragmentTransaction.commit();
    }
}
