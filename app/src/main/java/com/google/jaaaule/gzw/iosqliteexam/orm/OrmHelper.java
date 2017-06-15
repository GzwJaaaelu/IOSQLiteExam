package com.google.jaaaule.gzw.iosqliteexam.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.jaaaule.gzw.iosqliteexam.model.Goods;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 */

public class OrmHelper {
    private static OrmHelper sInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private GoodsDao mGoodsDao;

    private OrmHelper(Context context) {
        MySQLOpenHelper helper = new MySQLOpenHelper(context, "ORM_DB");
        SQLiteDatabase db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        mGoodsDao = mDaoSession.getGoodsDao();
    }

    public static OrmHelper newInstance(Context context) {
        if (sInstance == null) {
            synchronized (OrmHelper.class) {
                if (sInstance == null) {
                    sInstance = new OrmHelper(context);
                }
            }
        }
        return sInstance;
    }

    public void insertGoods(Goods goods) {
        mGoodsDao.insertOrReplaceInTx(goods);
    }

    public void insertGoodsList(List<Goods> goodsList) {
        mGoodsDao.insertOrReplaceInTx(goodsList);
    }

    public List<Goods> queryGoods(int offset, int limit) {
        return mGoodsDao.queryBuilder()
                .orderDesc(GoodsDao.Properties.AddToCartTime)
                .offset(offset)
                .limit(limit)
                .build()
                .list();
    }
}
