package com.google.jaaaule.gzw.iosqliteexam.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by admin on 2017/5/14.
 */

@Entity(nameInDb = "goods")
public class Goods {
    @Unique
    @NotNull
    private String id;              //  商品 Id
    private String introduction;    //  商品介绍
    private float price;            //  商品价格
    private String avatarUrl;       //  商品图片链接
    private int remainingQuantity;  //  商品剩余数量
    private String addToCartTime;   //  商品加入到购物车的时间

    @Generated(hash = 2124002439)
    public Goods(@NotNull String id, String introduction, float price, String avatarUrl, int remainingQuantity,
            String addToCartTime) {
        this.id = id;
        this.introduction = introduction;
        this.price = price;
        this.avatarUrl = avatarUrl;
        this.remainingQuantity = remainingQuantity;
        this.addToCartTime = addToCartTime;
    }

    @Generated(hash = 1770709345)
    public Goods() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getAddToCartTime() {
        return addToCartTime;
    }

    public void setAddToCartTime(String addToCartTime) {
        this.addToCartTime = addToCartTime;
    }
}
