package com.bwie.malonghuii2019120.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.malonghuii2019120.CustomView;
import com.bwie.malonghuii2019120.R;
import com.bwie.malonghuii2019120.bean.ShopCartBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {


    private Context context;
    private List<ShopCartBean.DataBean.ListBean> goodsList;
    int num = 0;

    public GoodsAdapter(Context context, List<ShopCartBean.DataBean.ListBean> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.goods_layout, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //设置值
        Glide.with(context).load(goodsList.get(i).getImages().split("\\|")[0].replace("https", "http")).into(viewHolder.goodsImg);
        viewHolder.goodsText.setText(goodsList.get(i).getTitle());
        viewHolder.goodsPrice.setText("￥" + goodsList.get(i).getPrice() + "元");
        viewHolder.edit_num.setText(goodsList.get(i).getNum());
        viewHolder.customView.setAddOrDelClickListener(new CustomView.AddOrDelClickListener() {
            @Override
            public void addClickListener(View view) {
                int num = viewHolder.customView.getNum();
                num++;
                viewHolder.customView.setNum(num);
            }

            @Override
            public void delClickListener(View view) {
                int num = viewHolder.customView.getNum();
                num--;
                viewHolder.customView.setNum(num);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_img)
        ImageView goodsImg;
        @BindView(R.id.goods_text)
        TextView goodsText;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.custom_view)
        CustomView customView;
        private final EditText edit_num;
        private final Button btn_jie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            edit_num = itemView.findViewById(R.id.edit_num);
            btn_jie = itemView.findViewById(R.id.btn_jie);
        }
    }
}
