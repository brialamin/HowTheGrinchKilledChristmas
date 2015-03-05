package com.example.root.exam2program;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class UserDB extends SQLiteOpenHelper {
    private static UserDB sInstance;
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME_1 = "user";
    private static final String USER_ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";






    public static UserDB getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new UserDB(context.getApplicationContext());
        }
        return sInstance;
    }

    private UserDB(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crate a new table
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                USERNAME + " TEXT NOT NULL UNIQUE,  " +
                PASSWORD + " TEXT NOT NULL" + ");");
        System.out.print("");
        db.execSQL("insert into " + TABLE_NAME_1 + " (" + USERNAME + ", " + PASSWORD + ") values(" +
                "'codeIt', 'droid4Fun');");
        System.out.print("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        onCreate(db);
    }




}
