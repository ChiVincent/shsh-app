package com.gameball.school;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MMFragmentTest22 extends Fragment {
    private AnimationDrawable animationDrawable = null;
    private View mFragmentView;
    private ImageView mImageViewTree;
    private ImageView mImageViewTreeBackground;
    private ImageView mImageViewTreeBackgroundNext;
    private ImageView mImageViewTreeNext;
    private String tag = getClass().getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_test2, null);
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        return this.mFragmentView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }
}
