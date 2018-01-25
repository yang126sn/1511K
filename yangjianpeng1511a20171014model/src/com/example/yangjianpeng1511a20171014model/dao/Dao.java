package com.example.yangjianpeng1511a20171014model.dao;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yangjianpeng1511a20171014model.bean.User;
import com.example.yangjianpeng1511a20171014model.database.MyHelper;

public class Dao {
  
	private MyHelper helper;

	public Dao(Context context){
		this.helper=new MyHelper(context);
	}
	  
	public void addData(ContentValues values){
		SQLiteDatabase db = helper.getWritableDatabase();
		 db.insert("user", null, values);
		
		
		
	}

  
	public void deleteData(String name) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from user where user_name=?",new String[]{name});
		
	}
   public void updateData(String name,String password,String name2){
	   SQLiteDatabase db = helper.getWritableDatabase();
	   db.execSQL("update user set user_name=?,user_password=? where user_name=?",new String[]{name,password,name2} );
   }

   public List<User> selectData(){
	   List<User> list=new ArrayList<User>();
	   SQLiteDatabase db = helper.getWritableDatabase();
	   Cursor cursor = db.query("user", null, null, null, null, null, null);
	   while(cursor.moveToNext()){
		  // int id = cursor.getInt(cursor.getColumnIndex("id"));
		   String name = cursor.getString(cursor.getColumnIndex("user_name"));
		   String password = cursor.getString(cursor.getColumnIndex("user_password"));
		   list.add(new User(name, password));
	   }
	   return list;
	   
   }


}
