package com.shangeeth.animationtypes.ui;


import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.shangeeth.animationtypes.R;
import com.shangeeth.animationtypes.util.UtilityHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    public static final String CUSTOM_INTENT = "com.shangeeth.animation_types.CUSTOM_INTENT";

    BroadcastReceiver mBroadcastReceiver;
    LocalBroadcastManager mLocalBroadcastManager;

    ImageView mOrangeBall;
    ImageView mFootBall;
    ImageView mFlipBall;

    private ScaleAnimation lScaleAnimation;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lView = inflater.inflate(R.layout.fragment_display, container, false);

        mOrangeBall = (ImageView) lView.findViewById(R.id.orange_ball_iv);
        mFootBall = (ImageView) lView.findViewById(R.id.football_iv);
        mFlipBall = (ImageView) lView.findViewById(R.id.android_iv);


        return lView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int lPosition = intent.getIntExtra(getString(R.string.animation_type), 0);
                    loadAnimation(lPosition);
            }
        };

        IntentFilter lIntentFilter = new IntentFilter(CUSTOM_INTENT);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, lIntentFilter);
    }

    private void loadAnimation(int pPosition) {

        FrameLayout.LayoutParams lLayoutParams = (FrameLayout.LayoutParams) mOrangeBall.getLayoutParams();

        switch (pPosition) {

            case 0:
                clearAnimationsAndMakeAllViewsGone();

                mOrangeBall.setVisibility(View.VISIBLE);

                lLayoutParams.gravity = Gravity.CENTER | Gravity.LEFT;
                mOrangeBall.setLayoutParams(lLayoutParams);

                Animation anim1 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim1);
                mOrangeBall.startAnimation(anim1);

                break;

            case 1:
                clearAnimationsAndMakeAllViewsGone();

                mOrangeBall.setVisibility(View.VISIBLE);
                mFootBall.setVisibility(View.VISIBLE);

                lLayoutParams.gravity = Gravity.CENTER;
                mOrangeBall.setLayoutParams(lLayoutParams);

                Animation lAnim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim2_rotate_anticlockwise_infinite);
                mOrangeBall.startAnimation(lAnim2);

                Animation lAnim2_1 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim2_rotate_clockwise);
                mFootBall.startAnimation(lAnim2_1);
                lAnim2_1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.e("TAG", "onAnimationRepeat: ");
                    }
                });


                break;

            case 2:
                clearAnimationsAndMakeAllViewsGone();
                lLayoutParams = new FrameLayout.LayoutParams(UtilityHelper.convertToPx(getActivity(), 50), UtilityHelper.convertToPx(getActivity(), 50));
                lLayoutParams.gravity = Gravity.TOP | Gravity.CENTER;
                mOrangeBall.setLayoutParams(lLayoutParams);
                mOrangeBall.setVisibility(View.VISIBLE);

                Animation lAnim_3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim3_scale_bounce);
                mOrangeBall.startAnimation(lAnim_3);

                break;

            case 3:

                clearAnimationsAndMakeAllViewsGone();

                mFlipBall.setVisibility(View.VISIBLE);

                lScaleAnimation = new ScaleAnimation(1,-1,1,1, mFlipBall.getMeasuredWidth()/2, mFlipBall.getMeasuredHeight()/2);
                lScaleAnimation.setDuration(3000);

                lScaleAnimation.setRepeatMode(Animation.REVERSE);
                lScaleAnimation.setRepeatCount(1);
                lScaleAnimation.setInterpolator(new DecelerateInterpolator());
                lScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        lScaleAnimation.setInterpolator(new DecelerateInterpolator());
                    }
                });
                mFlipBall.startAnimation(lScaleAnimation);

//
//                final ValueAnimator lValueAnimator = ObjectAnimator.ofFloat(mFlipBall,"scaleX",1,-1);
//                lValueAnimator.setDuration(3000);
//                lValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//                lValueAnimator.setRepeatCount(1);
//                lValueAnimator.setInterpolator(new DecelerateInterpolator());
//                lValueAnimator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//                        lValueAnimator.setInterpolator(new DecelerateInterpolator());
//                    }
//                });
//                lValueAnimator.start();
                break;

        }
    }

    public void clearAnimationsAndMakeAllViewsGone() {

        mOrangeBall.clearAnimation();
        mFootBall.clearAnimation();
        mFlipBall.clearAnimation();

        mFootBall.setVisibility(View.INVISIBLE);
        mOrangeBall.setVisibility(View.INVISIBLE);
        mFlipBall.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DisplayFragment","Calling the display destory");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        Log.d("DisplayFragment","Calling the display detach");
    }

}
