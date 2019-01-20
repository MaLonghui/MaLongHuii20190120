package com.bwie.malonghuii2019120.model;

import com.bwie.malonghuii2019120.okhttp.Okhttp;

public class ShopCartModel implements IShopCartModel{
    @Override
    public void getData(String url, final ModelInterface modelInterface) {
        //获取数据
        Okhttp.getInstance().doGet(url, new Okhttp.NetCallBack() {
            @Override
            public void onSuccess(String data) {
                modelInterface.loadSuccess(data);
            }

            @Override
            public void onFailed(Exception e) {
                modelInterface.loadFailed();
            }
        });
    }
}
