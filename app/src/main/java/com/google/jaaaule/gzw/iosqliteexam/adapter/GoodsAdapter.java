package com.google.jaaaule.gzw.iosqliteexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.jaaaule.gzw.iosqliteexam.R;
import com.google.jaaaule.gzw.iosqliteexam.model.Goods;

import java.util.List;

/**
 * Created by admin on 2017/5/8.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsHolder> {
    private List<Goods> mGoodsList;
    private Context mContext;

    public GoodsAdapter(List<Goods> goodsList) {
        mGoodsList = goodsList;
    }

    @Override
    public GoodsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_show_goods, parent, false);
        return new GoodsHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsHolder holder, int position) {
        holder.setData();
    }

    public void setGoodsList(List<Goods> goodsList) {
        mGoodsList = goodsList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    class GoodsHolder extends RecyclerView.ViewHolder {
        private ImageView mAvatarUrl;
        private TextView mIntroduction;
        private TextView mPrice;
        private TextView mRemainingQuantity;
        private TextView mAddToCartTime;

        public GoodsHolder(View itemView) {
            super(itemView);

            mAvatarUrl = (ImageView) itemView.findViewById(R.id.tv_goods_avatar_url);
            mIntroduction = (TextView) itemView.findViewById(R.id.tv_goods_introduction);
            mPrice = (TextView) itemView.findViewById(R.id.tv_goods_price);
            mRemainingQuantity = (TextView) itemView.findViewById(R.id.tv_goods_remaining_Quantity);
            mAddToCartTime = (TextView) itemView.findViewById(R.id.tv_goods_add_to_cart_time);
        }

        public void setData() {
            mAvatarUrl.setImageResource(Integer.valueOf(mGoodsList.get(getAdapterPosition()).getAvatarUrl()));
            mIntroduction.setText(mGoodsList.get(getAdapterPosition()).getIntroduction());
            mPrice.setText("¥ " + mGoodsList.get(getAdapterPosition()).getPrice());
            mRemainingQuantity.setText("剩余数量：" + mGoodsList.get(getAdapterPosition()).getRemainingQuantity());
            mAddToCartTime.setText("购入时间：" + mGoodsList.get(getAdapterPosition()).getAddToCartTime());
        }
    }
}
