package com.shangeeth.animationtypes.ui;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.shangeeth.animationtypes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    public static final String CUSTOM_INTENT = "com.shangeeth.animation_types.CUSTOM_INTENT";

    private int[] mAnimationFiles = {R.anim.alpha, R.anim.scale, R.anim.rotate, R.anim.translate, R.anim.accelerate_alpha,
            0, R.anim.accelerate_dec_scale, R.anim.anticipate_translate,
            R.anim.cycle_alpha, R.anim.linear_alpha, R.anim.overshoot_rotate, R.anim.decelerate_translate,
            R.anim.translate_scale, R.anim.translate_rotate};

    BroadcastReceiver mBroadcastReceiver;
    LocalBroadcastManager mLocalBroadcastManager;

    ImageView mImageView;
    private ObjectAnimator mObjectAnimator;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lView = inflater.inflate(R.layout.fragment_display, container, false);
        mImageView = (ImageView) lView.findViewById(R.id.text_view);
        mObjectAnimator = ObjectAnimator.ofFloat(mImageView, "translationY", 500);
        mObjectAnimator.setDuration(2000);
        mObjectAnimator.setInterpolator(new BounceInterpolator());
        mObjectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mImageView.setTranslationY(mImageView.getTranslationY()-mImageView.getTranslationY());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return lView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int lPosition = intent.getIntExtra(getString(R.string.animation_type), 0);
                if (lPosition == 5)
                    mObjectAnimator.start();
                else if(lPosition == 14){

                    TranslateAnimation lTranslateAnimation = new TranslateAnimation(0f,200f,0f,200f);
                    lTranslateAnimation.setDuration(2000);
                    mImageView.invalidate();
                    mImageView.startAnimation(lTranslateAnimation);
                }
                else
                    loadAnimation(lPosition);
            }
        };

        IntentFilter lIntentFilter = new IntentFilter(CUSTOM_INTENT);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, lIntentFilter);
    }

    private void loadAnimation(int pPosition) {

        mImageView.invalidate();
        Animation lAnimation = AnimationUtils.loadAnimation(getActivity(), mAnimationFiles[pPosition]);
        mImageView.startAnimation(lAnimation);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }

}
