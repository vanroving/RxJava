package com.guoxin.githubdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.guoxin.githubdemo.R;
import com.guoxin.githubdemo.adapter.ItemAdapter;
import com.guoxin.githubdemo.entity.Data;
import com.guoxin.githubdemo.entity.Results;
import com.guoxin.githubdemo.model.Item;
import com.guoxin.githubdemo.network.HttpNetWork;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class OneFragment extends Fragment {
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.rv)
    RecyclerView rv;
    ItemAdapter adapter=new ItemAdapter();
    private ArrayList<String> pic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.fragment_one,null);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv.setAdapter(adapter);
        pic = new ArrayList<>();

        return view;
    }
    @OnClick(R.id.bt)
    public void Load(){
        HttpNetWork.getGs().get(200,1).map(new Func1<Data, List<Item>>() {
            @Override
            public List<Item> call(Data data) {
                List<Results> results=data.getResults();
                List<Item> items=new ArrayList<Item>(results.size());
                for (Results re:results) {
                    Item item=new Item();
                    item.url=re.getUrl();
                    items.add(item);
                    pic.add(re.getUrl());
                }
                return items;
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<Item>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Item> items) {
                adapter.setItems(items);

            }
        });
    }
}
