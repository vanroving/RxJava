package com.guoxin.githubdemo.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/21.
 */
public class HttpNetWork {
    public static String URL="http://gank.io/api/";
    public static  GithubService getGs(){
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubService gs=retrofit.create(GithubService.class);
        return gs;
    }
}
