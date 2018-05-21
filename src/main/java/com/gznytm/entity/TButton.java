/**
 * Copyright &copy; 2015-2020 <a href=" ">harry12800</a> All rights reserved.
 */
package com.gznytm.entity;

import cn.harry12800.tools.DbField;
import cn.harry12800.tools.DbInitSentence;
import cn.harry12800.tools.DbInitType;
import cn.harry12800.tools.DbTable;
/**
 * Entity
 * @author 周国柱
 * @version 1.0
 * <dt>jdbc:mysql://192.168.0.167:3306/nytm
 * <dt>root
 * <dt>admin
 * <dt>代码自动生成!数据库的资源文件.
 */
@DbTable(tableName = "t_button")
public class TButton { // extends DataEntity<TButton> {
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "ID")
 	private String	id;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "PAGE_ID")
 	private String	pageId;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "NAME")
 	private String	name;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "TYPE")
 	private String	type;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "BUTTON_ID")
 	private String	buttonId;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "APPLICATION_ID")
 	private String	applicationId;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "PAGE_CODE")
 	private String	pageCode;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "PAGE_NAME")
 	private String	pageName;
	/**
	 * 
	 */
	@DbField(value="null",type=1,sort=1, title ="null", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "OPERATION_ID")
 	private String	operationId;
	@DbInitSentence(type = DbInitType.Create)
	public static String initSql="CREATE TABLE t_button("+
		"	ID		VARCHAR(32) ,"+
		"	PAGE_ID		VARCHAR(32) ,"+
		"	NAME		VARCHAR(30) ,"+
		"	TYPE		VARCHAR(10) ,"+
		"	BUTTON_ID		VARCHAR(100) ,"+
		"	APPLICATION_ID		VARCHAR(32) ,"+
		"	PAGE_CODE		VARCHAR(250) ,"+
		"	PAGE_NAME		VARCHAR(50) ,"+
		"	OPERATION_ID		VARCHAR(100) "+
		");";
	@DbInitSentence(type = DbInitType.Create)
	public static String initOracleSql="CREATE TABLE t_button("+
		"	ID		VARCHAR2(32) ,"+
		"	PAGE_ID		VARCHAR2(32) ,"+
		"	NAME		VARCHAR2(30) ,"+
		"	TYPE		VARCHAR2(10) ,"+
		"	BUTTON_ID		VARCHAR2(100) ,"+
		"	APPLICATION_ID		VARCHAR2(32) ,"+
		"	PAGE_CODE		VARCHAR2(250) ,"+
		"	PAGE_NAME		VARCHAR2(50) ,"+
		"	OPERATION_ID		VARCHAR2(100) "+
		");";
	/**
	 *获取
	 */
 	public  String	getId() {
 		return id;
 	}
	/**
	 *获取
	 */
 	public  String	getPageId() {
 		return pageId;
 	}
	/**
	 *获取
	 */
 	public  String	getName() {
 		return name;
 	}
	/**
	 *获取
	 */
 	public  String	getType() {
 		return type;
 	}
	/**
	 *获取
	 */
 	public  String	getButtonId() {
 		return buttonId;
 	}
	/**
	 *获取
	 */
 	public  String	getApplicationId() {
 		return applicationId;
 	}
	/**
	 *获取
	 */
 	public  String	getPageCode() {
 		return pageCode;
 	}
	/**
	 *获取
	 */
 	public  String	getPageName() {
 		return pageName;
 	}
	/**
	 *获取
	 */
 	public  String	getOperationId() {
 		return operationId;
 	}
	
	/**
	 * 设值
	 */
 	public void	setId(String id) {
 		this.id = id;
 	}
	/**
	 * 设值
	 */
 	public void	setPageId(String pageId) {
 		this.pageId = pageId;
 	}
	/**
	 * 设值
	 */
 	public void	setName(String name) {
 		this.name = name;
 	}
	/**
	 * 设值
	 */
 	public void	setType(String type) {
 		this.type = type;
 	}
	/**
	 * 设值
	 */
 	public void	setButtonId(String buttonId) {
 		this.buttonId = buttonId;
 	}
	/**
	 * 设值
	 */
 	public void	setApplicationId(String applicationId) {
 		this.applicationId = applicationId;
 	}
	/**
	 * 设值
	 */
 	public void	setPageCode(String pageCode) {
 		this.pageCode = pageCode;
 	}
	/**
	 * 设值
	 */
 	public void	setPageName(String pageName) {
 		this.pageName = pageName;
 	}
	/**
	 * 设值
	 */
 	public void	setOperationId(String operationId) {
 		this.operationId = operationId;
 	}
}
	