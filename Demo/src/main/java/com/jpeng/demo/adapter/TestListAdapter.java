package com.jpeng.demo.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.jpeng.demo.R;
import com.jpeng.demo.picasso.SquareUtils;
import com.jpeng.progress.CircleProgress;
import com.squareup.picasso.NetworkPolicy;

import java.util.List;

/**
 * Created by peng on 16-10-22.
 */
public class TestListAdapter extends BaseAdapter {
    private Context context;
    private List<String> mDatas;
    private Handler handler;
    public TestListAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        handler=new Handler();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view ==null){
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_test,null);
            holder.image = (ImageView) view.findViewById(R.id.item_image);
             view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        final CircleProgress progress = new CircleProgress.Builder()
                .build();
        progress.setTextColor(Color.GREEN);
        progress.inject(holder.image);
//        Glide.with(context).using(new ProgressModelLoader(new Handler(){
//
//            @Override
//            public void handleMessage(Message msg) {
//                progress.setLevel(msg.arg1);
//                progress.setMaxValue(msg.arg2);
//            }
//        })).load(mDatas.get(position)).placeholder(R.mipmap.loading2).centerCrop().into(holder.image);
        SquareUtils.getPicasso(context, new SquareUtils.ProgressListener() {
            @Override
            public void update(final long current, final long total) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progress.setLevel((int) current);
                        progress.setMaxValue(total);
                    }
                });

            }
        }).load(mDatas.get(position)).config(Bitmap.Config.ARGB_4444).networkPolicy(NetworkPolicy.NO_CACHE).placeholder(R.mipmap.loading2).resize(400,400).centerCrop().into(holder.image);

        return view;
    }

    class ViewHolder{
        ImageView image;
    }

}
