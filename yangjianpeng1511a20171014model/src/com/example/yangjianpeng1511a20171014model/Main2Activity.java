package com.example.yangjianpeng1511a20171014model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.yangjianpeng1511a20171014model.adapter.MyAdaper;
import com.example.yangjianpeng1511a20171014model.bean.User;
import com.example.yangjianpeng1511a20171014model.dao.Dao;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Main2Activity extends Activity implements OnClickListener {
    
	
   
	private ListView lv;
	private Button add;
	private MyAdaper adapter;
	private TextView name;
	private TextView password;
	Dao dao=new Dao(Main2Activity.this);
	private List<User> list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
	   
		
		name = (TextView) findViewById(R.id.tv01);
		password = (TextView) findViewById(R.id.tv02);
		lv = (ListView) findViewById(R.id.lv);
		add = (Button) findViewById(R.id.bt1);
		
	
	    add.setOnClickListener(this);
	    
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			list = new ArrayList<User>();
			
			Intent intent = getIntent();
			User u = (User) intent.getSerializableExtra("dname");
			String name2 = u.getName();
			String password2 = u.getPassword();
			ContentValues values=new ContentValues();
			values.put("user_name", name2);
			values.put("user_password", password2);
			dao.addData(values);
			list = dao.selectData();
			adapter = new MyAdaper(list, Main2Activity.this);
		    lv.setAdapter(adapter);
			
			
			default:
			break;
		}
		
	}

	

}
