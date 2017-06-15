package com.google.jaaaule.gzw.iosqliteexam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/14.
 */

public class GoodsOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "goods.db";
    private static final int DB_VERSION = 1;
    private String[] mColumns;

    public static class Goods {
        public static final String GOODS_TABLE_NAME = "goods";
        public static final String GOODS_ID = "goods_id";
        public static final String GOODS_INTRODUCTION = "goods_introduction";
        public static final String GOODS_PRICE = "goods_price";
        public static final String GOODS_AVATAR_URL = "goods_avatar_url";
        public static final String GOODS_REMAINING_QUANTITY = "goods_remaining_quantity";
        public static final String GOODS_ADD_TO_CART_TIME = "goods_add_to_cart_time";
    }

    public GoodsOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = goodsCreateSQL();
        db.execSQL(sql);
    }

    private String goodsCreateSQL() {
        return "CREATE TABLE " + Goods.GOODS_TABLE_NAME + " ("
                //  主键就已经是非空的了
                + Goods.GOODS_ID + " VARCHAR(16) PRIMARY KEY,"
                + Goods.GOODS_INTRODUCTION + " TEXT,"
                + Goods.GOODS_PRICE + " FLOAT,"
                + Goods.GOODS_AVATAR_URL + " VARCHAR(255),"
                + Goods.GOODS_REMAINING_QUANTITY + " INTEGER,"
                + Goods.GOODS_ADD_TO_CART_TIME + " VARCHAR(20)" + ");";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 开启事务循环插入
     * @param goodsList
     */
    public void insertMoreGoods(List<com.google.jaaaule.gzw.iosqliteexam.model.Goods> goodsList) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            for (com.google.jaaaule.gzw.iosqliteexam.model.Goods goods : goodsList) {
                ContentValues values = new ContentValues();
                values.put(Goods.GOODS_ID, goods.getId());
                values.put(Goods.GOODS_INTRODUCTION, goods.getIntroduction());
                values.put(Goods.GOODS_PRICE, goods.getPrice());
                values.put(Goods.GOODS_AVATAR_URL, goods.getAvatarUrl());
                values.put(Goods.GOODS_REMAINING_QUANTITY, goods.getRemainingQuantity());
                values.put(Goods.GOODS_ADD_TO_CART_TIME, goods.getAddToCartTime());
                db.insertWithOnConflict(Goods.GOODS_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /**
     * 开启事务循环插入
     */
    public void insertGoods(com.google.jaaaule.gzw.iosqliteexam.model.Goods goods) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Goods.GOODS_ID, goods.getId());
        values.put(Goods.GOODS_INTRODUCTION, goods.getIntroduction());
        values.put(Goods.GOODS_PRICE, goods.getPrice());
        values.put(Goods.GOODS_AVATAR_URL, goods.getAvatarUrl());
        values.put(Goods.GOODS_REMAINING_QUANTITY, goods.getRemainingQuantity());
        values.put(Goods.GOODS_ADD_TO_CART_TIME, goods.getAddToCartTime());
        db.insertWithOnConflict(Goods.GOODS_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    /**
     * 查询货物
     * @return
     */
    public List<com.google.jaaaule.gzw.iosqliteexam.model.Goods> queryAndOrderByDECSTime() {
        mColumns = new String[]{Goods.GOODS_ID,
                Goods.GOODS_INTRODUCTION,
                Goods.GOODS_PRICE,
                Goods.GOODS_AVATAR_URL,
                Goods.GOODS_REMAINING_QUANTITY,
                Goods.GOODS_ADD_TO_CART_TIME};
        List<com.google.jaaaule.gzw.iosqliteexam.model.Goods> goodsList = new ArrayList<>();
        Cursor cursor = getWritableDatabase().query(Goods.GOODS_TABLE_NAME,
                mColumns, null, null, null, null, Goods.GOODS_ADD_TO_CART_TIME + " DESC ");
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            com.google.jaaaule.gzw.iosqliteexam.model.Goods goods = new com.google.jaaaule.gzw.iosqliteexam.model.Goods();
            goods.setId(cursor.getString(0));
            goods.setIntroduction(cursor.getString(1));
            goods.setPrice(cursor.getFloat(2));
            goods.setAvatarUrl(cursor.getString(3));
            goods.setRemainingQuantity(cursor.getInt(4));
            goods.setAddToCartTime(cursor.getString(5));
            goodsList.add(goods);
        }
        cursor.close();
        return goodsList;
    }
}
