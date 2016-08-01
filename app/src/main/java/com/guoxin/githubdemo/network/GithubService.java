package com.guoxin.githubdemo.network;

import com.guoxin.githubdemo.entity.Data;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/21.
 */
public interface GithubService {
    @GET("data/福利/{number}/{line}")
    Observable<Data> get(@Path("number")int number,@Path("line") int line);
}
