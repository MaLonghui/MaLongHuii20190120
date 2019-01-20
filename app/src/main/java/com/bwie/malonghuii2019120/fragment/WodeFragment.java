package com.bwie.malonghuii2019120.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.malonghuii2019120.R;

import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class WodeFragment extends Fragment {

    @BindView(R.id.wode_img_view)
    ImageView wodeImgView;
    @BindView(R.id.wode_text)
    TextView wodeText;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.wode_text)
    public void onViewClicked() {
        //设置动画平移
        ObjectAnimator translationY = ObjectAnimator.ofFloat(wodeImgView, "translationY", wodeImgView.getTranslationY(), 400);
        //旋转
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(wodeImgView, "rotationY", 0, 180);
        //缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(wodeImgView, "scaleX", 1, 2);
        AnimatorSet animatorSet = new AnimatorSet();
        //设置动画
        animatorSet.play(translationY).with(rotationX).with(scaleX);
        //设置动画shijian
        animatorSet.setDuration(3000);
        //开启动画
        animatorSet.start();

    }
}
