package com.guoxin.githubdemo.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class Data {
    public List<Results> results;
    public void setResults(List<Results> results){
        this.results=results;
    }
    public List<Results> getResults(){
        return results;
    }
}
