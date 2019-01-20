package com.bwie.malonghuii2019120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.malonghuii2019120.fragment.ShopCartFragment;
import com.bwie.malonghuii2019120.fragment.WodeFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(10,10)
                .setTabPadding(4,6,10)
                .setFontSize(20)
                .addTabItem("购物车",null,ShopCartFragment.class)
                .addTabItem("我的",null,WodeFragment.class);

    }
}
