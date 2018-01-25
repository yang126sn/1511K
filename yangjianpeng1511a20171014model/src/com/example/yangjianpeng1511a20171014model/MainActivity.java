package com.example.yangjianpeng1511a20171014model;

import java.util.List;

import com.example.yangjianpeng1511a20171014model.bean.User;
import com.example.yangjianpeng1511a20171014model.dao.Dao;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private EditText name;
	private EditText password;
	private Button login;
	private String text;
	private String pwd;
	private CheckBox auto;
	private SharedPreferences sp;
	private CheckBox rem;
    private String nameDatabase;
	private boolean d;
	private boolean r;
	private Editor edit;
	private String  pwdDatabase;
	
	 Dao dao=new Dao(MainActivity.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	     
	     sp = getSharedPreferences("remeber", MODE_PRIVATE);
		 
	    //获取控件
		 name = (EditText) findViewById(R.id.et1);
	     password = (EditText) findViewById(R.id.et2);
	     login = (Button) findViewById(R.id.btn);
	     auto = (CheckBox) findViewById(R.id.ck1);
	     rem = (CheckBox) findViewById(R.id.ck2);
	     
	     login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		//获取输入的用户名和密码
    	text = name.getText().toString();
	  
    	pwd = password.getText().toString();
    	
		
		if(text.length()<=0 && pwd.length()<=0){
			Toast.makeText(MainActivity.this, "用户名或密码为空", 0).show();
		}else if(text.length()<=0){
			Toast.makeText(MainActivity.this, "用户名不能为空", 0).show();
		}else if(pwd.length()<=0){
			Toast.makeText(MainActivity.this, "密码不能为空", 0).show();
		}else if(text!=null && pwd.length()!=0){
			
			boolean isFirst = sp.getBoolean("first", true);
			if(isFirst){
				 d = auto.isChecked();
				 r = rem.isChecked();
				 edit = sp.edit();
				 
				 if(r){
					auto.setChecked(true);
					rem.setChecked(true);
					edit.putBoolean("autoLogin", d);
					edit.putBoolean("jizhu", r);
				 }else{
			    	sp.getBoolean("autoLogin", false);
			    	sp.getBoolean("jizhu", false);
			    }
				  edit.commit();
				 
				  User u=new User(text, pwd);
				  Intent intent=new Intent(MainActivity.this, Main2Activity.class);
			      intent.putExtra("dname", u);
			      startActivity(intent);
			}else{
				 List<User> list = dao.selectData();
				 for (int i = 0; i < list.size(); i++) {
					nameDatabase = list.get(i).getName();
				    pwdDatabase = list.get(i).getPassword();
				 }
				 
				 if(text.equals(nameDatabase)&&pwd.equals(pwdDatabase)){
					Intent intent=new Intent(MainActivity.this, Main2Activity.class);
				    startActivity(intent);
				}else{
					Toast.makeText(MainActivity.this,"用户名或密码错误", 0).show();
				}
			}
			
		}
	}

	

}
