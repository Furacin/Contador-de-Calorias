package com.furazin.android.calorias.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.furazin.android.calorias.DataBase.RegistroDbSchema.*;

/**
 * Created by manza on 19/02/2017.
 */

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "users.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.NAME + "(" +
                UserTable.Cols.DATE + ", " +
                UserTable.Cols.RESULT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
