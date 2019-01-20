package com.bwie.malonghuii2019120.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.bwie.malonghuii2019120.R;
import com.bwie.malonghuii2019120.adapter.ShangJiaAdapter;
import com.bwie.malonghuii2019120.bean.ShopCartBean;
import com.bwie.malonghuii2019120.presenter.ShopCartPresenter;
import com.bwie.malonghuii2019120.view.IShopCartView;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ShopCartFragment extends Fragment implements IShopCartView {



    @BindView(R.id.shangjia_recycler)
    RecyclerView shangjiaRecycler;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        ShopCartPresenter shopCartPresenter = new ShopCartPresenter(ShopCartFragment.this);
        shopCartPresenter.getPresenterData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        shangjiaRecycler.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getViewData(String data) {
       // Log.i("aa", "getViewData: "+data);
        //解析获取到的数据
        Gson gson = new Gson();
        ShopCartBean shopCartBean = gson.fromJson(data, ShopCartBean.class);
        List<ShopCartBean.DataBean> shangjiaList = shopCartBean.getData();
        //创建适配器
        ShangJiaAdapter shangJiaAdapter = new ShangJiaAdapter(getActivity(),shangjiaList);
        shangjiaRecycler.setAdapter(shangJiaAdapter);
        if (shangjiaList!=null){
            shangjiaList.remove(0);
            shangJiaAdapter.notifyDataSetChanged();
        }

    }
}
