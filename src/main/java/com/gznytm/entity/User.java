package com.gznytm.entity;

import cn.harry12800.tools.DbField;
import cn.harry12800.tools.DbInitSentence;
import cn.harry12800.tools.DbInitType;
import cn.harry12800.tools.DbTable;

@DbTable(tableName = "t_user")
public class User{
	@DbField(canAdd = false,isKey=true, canEdit = false, canSearch = false, dbFieldName = "id", title = "")
	private String id;
	@DbField(canAdd = false, canEdit = false, canSearch = false, dbFieldName = "user_name", title = "")
	private String userName;
	@DbField(canAdd = false, canEdit = false, canSearch = false, dbFieldName = "password", title = "")
	private String password;
	
//	@TableField(canAdd = false, canEdit = false, canSearch = false, dbFieldName = "header", title = "")
//	private byte[] header;

	
	@DbInitSentence(type = DbInitType.Create)
	public static String initSql="create table t_user(id varchar(32) primary key,user_name varchar(64),password varchar(64))";
	@DbInitSentence(type = DbInitType.insert)
	public static String initInsert="insert into t_user values('1','nytm','nytm')";
	/*
	 create table t_user(id varchar(32) primary key,user_name varchar(64),password varchar(64))
	 */
	/**
	 * 获取userName
	 *	@return the userName
	 */
	
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置userNameString
	 * @param userNameString the userNameString to set
	 */
	public void setUserName(String userNameString) {
		this.userName = userNameString;
	}
	/**
	 * 获取password
	 *	@return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取id
	 *	@return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
