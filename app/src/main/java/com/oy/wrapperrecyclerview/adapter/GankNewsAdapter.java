package com.oy.wrapperrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oy.wrapperrecyclerview.R;
import com.oy.wrapperrecyclerview.bean.GankNewsBean;
import com.oy.wrapperrecyclerview.widget.xRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   : xiaoyu
 * Date     : 2017/9/28 15:06
 * Describe :
 */

public class GankNewsAdapter extends xRecyclerView.xAdapter<GankNewsAdapter.MyViewHolder> {

    private final List<GankNewsBean> mListData;
    // 是否是 福利
    private boolean mIsFuli = false;

    public GankNewsAdapter(List<GankNewsBean> data, String type) {
        mListData = data;
        mIsFuli = "福利".equals(type);
    }

    @Override
    protected int getxItemCount() {
        return mListData.size();
    }

    @Override
    protected MyViewHolder onCreatexViewHolder(ViewGroup parent, int itemType) {
        Context context = parent.getContext().getApplicationContext();
        if (mIsFuli) {
            return new MyViewHolder(View.inflate(context, R.layout.item_gank_news_fuli, null));
        } else {
            return new MyViewHolder(View.inflate(context, R.layout.item_gank_news, null));
        }
    }

    @Override
    protected void onBindxViewHolder(MyViewHolder holder, int position) {
        final GankNewsBean item = mListData.get(position);
        if (!mIsFuli) {
            holder.title.setText(item.getDesc());
            holder.subtitle.setText(item.getPublishedAt().toLocaleString());
            final List<String> images = item.getImages();
            if (images != null && images.size() > 0) {
                holder.image.setVisibility(View.VISIBLE);
                Glide.with(holder.itemView).asBitmap().load(images.get(0)).into(holder.image);
            } else {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            Glide.with(holder.itemView).asBitmap().load(item.getUrl()).into(holder.imageFuli);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Nullable@BindView(R.id.gank_item_title)
        TextView title;

        @Nullable@BindView(R.id.gank_item_subtitle)
        TextView subtitle;

        @Nullable@BindView(R.id.gank_item_image)
        ImageView image;

        // FULI fragment
        @Nullable@BindView(R.id.gank_item_image_fuli)
        ImageView imageFuli;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
