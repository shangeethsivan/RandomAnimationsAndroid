package com.shangeeth.animationtypes;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager lFragmentManager = getSupportFragmentManager();

        FragmentTransaction lFragmentTransaction = lFragmentManager.beginTransaction();

        lFragmentTransaction.add(R.id.container_1,new DisplayFragment());
        lFragmentTransaction.add(R.id.container_2,new ControllerFragment());

        lFragmentTransaction.commit();
    }
}
