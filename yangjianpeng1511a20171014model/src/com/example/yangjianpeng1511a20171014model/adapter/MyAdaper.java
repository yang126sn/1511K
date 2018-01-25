package com.example.yangjianpeng1511a20171014model.adapter;

import java.util.List;

import com.example.yangjianpeng1511a20171014model.R;
import com.example.yangjianpeng1511a20171014model.bean.User;
import com.example.yangjianpeng1511a20171014model.dao.Dao;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyAdaper extends BaseAdapter {
    List<User> list;
    Context ctx;
	
    public MyAdaper(List<User> list, Context ctx) {
		super();
		this.list = list;
		this.ctx = ctx;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	ViewHolder vh;
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 vh=new ViewHolder();
		 final User u = list.get(position);
		 if(convertView==null){
			convertView = View.inflate(ctx, R.layout.item, null);
			vh.name=(TextView) convertView.findViewById(R.id.tv01);
			vh.password=(TextView) convertView.findViewById(R.id.tv02);
		    convertView.setTag(vh);
		}else{
			 vh = (ViewHolder) convertView.getTag();
		}
		    vh.name.setText(list.get(position).getName());
		    vh.password.setText(list.get(position).getPassword());
		    convertView.findViewById(R.id.bt2).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					list.remove(position);
					notifyDataSetChanged();
					Dao dao=new Dao(ctx);
					dao.deleteData(u.getName());
					
				}
			});
		 convertView.findViewById(R.id.bt3).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AlertDialog.Builder dialog=new AlertDialog.Builder(ctx);
					dialog.setTitle("修改");
					final View view = View.inflate(ctx, R.layout.xiugai, null);
					dialog.setView(view);
					dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							EditText ename = (EditText) view.findViewById(R.id.et01);
							EditText epassword = (EditText) view.findViewById(R.id.et02);
							
							String trimname = ename.getText().toString().trim();
							String trimepassword = epassword.getText().toString().trim();
							
							Dao dao=new Dao(ctx);
							dao.updateData(trimname, trimepassword, u.getName());
							
							List<User> selectData = dao.selectData();
							
							list.set(position, selectData.get(position));
							
							notifyDataSetChanged();
						}
					});
					dialog.setNegativeButton("取消", null);
					dialog.create();
					dialog.show();
				}
			});
		    
		  return convertView;
	}
 
	class ViewHolder{
		TextView name,password;
		Button del,updata;
	}
}
