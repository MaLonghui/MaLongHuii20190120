package com.bwie.malonghuii2019120.presenter;

import com.bwie.malonghuii2019120.api.Api;
import com.bwie.malonghuii2019120.fragment.ShopCartFragment;
import com.bwie.malonghuii2019120.model.IShopCartModel;
import com.bwie.malonghuii2019120.model.ShopCartModel;

public class ShopCartPresenter implements IShopCartPresenter{
    ShopCartFragment shopCartFragment;
    private final ShopCartModel shopCartModel;

    public ShopCartPresenter(ShopCartFragment shopCartFragment) {
        this.shopCartFragment = shopCartFragment;
        //实例化model
        shopCartModel = new ShopCartModel();
    }

    @Override
    public void getPresenterData() {
        shopCartModel.getData(Api.SHOPCART, new IShopCartModel.ModelInterface() {
            @Override
            public void loadSuccess(String data) {
                shopCartFragment.getViewData(data);
            }

            @Override
            public void loadFailed() {

            }
        });
    }
}
