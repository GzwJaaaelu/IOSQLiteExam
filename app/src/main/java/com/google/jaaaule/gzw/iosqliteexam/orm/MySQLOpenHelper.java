package com.google.jaaaule.gzw.iosqliteexam.orm;

import android.content.Context;

/**
 * Created by admin on 2017/5/11.
 */

public class MySQLOpenHelper extends DaoMaster.OpenHelper {
    public MySQLOpenHelper(Context context, String name) {
        super(context, name);
    }
}
