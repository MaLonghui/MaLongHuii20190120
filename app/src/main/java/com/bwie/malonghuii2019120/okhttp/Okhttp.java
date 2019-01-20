package com.bwie.malonghuii2019120.okhttp;

import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp {

    private final OkHttpClient client;
    private static volatile Okhttp instance;
    private Handler handler = new Handler();

    //添加拦截器
    private Interceptor getAppInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截前
                Request request = chain.request();
                //拦截后
                Response response = chain.proceed(request);

                return response;
            }
        };
        return interceptor;
    }
    private Okhttp(){
        File file = new File(Environment.getDataDirectory(),"cachell");
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .cache(new Cache(file, 10 * 1024))
                .addInterceptor(getAppInterceptor())
                .build();
    }

    //单例okhttp
    public static Okhttp getInstance(){
        if (instance==null){
            synchronized (Okhttp.class){
                if (instance==null){
                    instance = new Okhttp();
                }
            }
        }
        return instance;
    }
    //get请求方式
    public void doGet(String url, final NetCallBack netCallBack){
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(data);
                    }
                });
            }
        });

    }
    //定义接口
    public interface NetCallBack{
        void onSuccess(String data);
        void onFailed(Exception e);
    }
}
