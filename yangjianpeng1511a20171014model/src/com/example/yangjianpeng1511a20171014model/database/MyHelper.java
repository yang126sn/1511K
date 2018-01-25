package com.example.yangjianpeng1511a20171014model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper{

	public MyHelper(Context context) {
		super(context, "users.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table user(user_id integer primary key autoincrement,user_name varchar(20),user_password varchar(20))");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}

}
