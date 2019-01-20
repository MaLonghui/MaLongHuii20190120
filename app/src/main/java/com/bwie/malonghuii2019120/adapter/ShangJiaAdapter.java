package com.bwie.malonghuii2019120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bwie.malonghuii2019120.R;
import com.bwie.malonghuii2019120.bean.ShopCartBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShangJiaAdapter extends RecyclerView.Adapter<ShangJiaAdapter.ViewHolder> {


    private Context context;
    private List<ShopCartBean.DataBean> shangjiaList;

    public ShangJiaAdapter(Context context, List<ShopCartBean.DataBean> shangjiaList) {
        this.context = context;
        this.shangjiaList = shangjiaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shangjia_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //设置值
        viewHolder.checkShangjia.setText(shangjiaList.get(i).getSellerName());
        //创建商品的集合
        List<ShopCartBean.DataBean.ListBean> goodsList = shangjiaList.get(i).getList();
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewHolder.goodsRecycler.setLayoutManager(linearLayoutManager);
        //创建商品适配器
        GoodsAdapter goodsAdapter = new GoodsAdapter(context,goodsList);
        viewHolder.goodsRecycler.setAdapter(goodsAdapter);

    }

    @Override
    public int getItemCount() {
        return shangjiaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.check_shangjia)
        CheckBox checkShangjia;
        @BindView(R.id.goods_recycler)
        RecyclerView goodsRecycler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
