package com.bwie.malonghuii2019120.model;

public interface IShopCartModel {
    void getData(String url,ModelInterface modelInterface);
    interface ModelInterface{
        void loadSuccess(String data);
        void loadFailed();
    }
}
