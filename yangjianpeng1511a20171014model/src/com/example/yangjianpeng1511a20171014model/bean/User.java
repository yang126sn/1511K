package com.example.yangjianpeng1511a20171014model.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
  
	private String name,password;
    private int id;
	
    public User(String name, String password, int id) {
		super();
		this.name = name;
		this.password = password;
		this.id = id;
	}
	
    public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
    public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
    
   
	
    
   
}
