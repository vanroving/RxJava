package com.guoxin.githubdemo.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.guoxin.githubdemo.R;
import com.guoxin.githubdemo.image.ImagPagerUtil;
import com.guoxin.githubdemo.model.Item;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    List<Item> items;
    List<String> pic=new ArrayList<>();

    public void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder =new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Item item=items.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.iv.setImageDrawable(holder.itemView.getContext().getDrawable(R.mipmap.ic_launcher));
        }
        Glide.with(holder.itemView.getContext())
                .load(item.url)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.iv);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                holder.itemView.getContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagPagerUtil imagPagerUtil = new ImagPagerUtil(holder.itemView.getContext(), pic);
                imagPagerUtil.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items==null?0:items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
        for (Item s:items
        ){
            pic.add(s.url);
        }
    }

    public class   MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.imageIv);
        }
    }
}
