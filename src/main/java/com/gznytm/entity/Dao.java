package com.gznytm.entity; 

import cn.harry12800.tools.DbField;
import cn.harry12800.tools.DbTable;

/**
 * @author:harry12800
 * <p>代码自动生成!数据库的资源文件.
 * <p>jdbc:mysql://127.0.0.1:3306/nytm
 * <p>root
 * <p>admin
 */
@DbTable(tableName = "dao")
public class Dao {


	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "dao_code")
	private String daoCode= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "size")
	private String size= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "remark")
	private String remark= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "last_time")
	private String lastTime= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "alter1")
	private String alter1= "";

	@DbField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "alter2")
	private String alter2= "";
	/**
	 * 获取id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置id
	 */
	public void setId(String id) {
		this.id=id;
	}
	/**
	 * 获取daoCode
	 */
	public String getDaoCode() {
		return daoCode;
	}
	/**
	 * 设置daoCode
	 */
	public void setDaoCode(String daoCode) {
		this.daoCode=daoCode;
	}
	/**
	 * 获取size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * 设置size
	 */
	public void setSize(String size) {
		this.size=size;
	}
	/**
	 * 获取remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置remark
	 */
	public void setRemark(String remark) {
		this.remark=remark;
	}
	/**
	 * 获取lastTime
	 */
	public String getLastTime() {
		return lastTime;
	}
	/**
	 * 设置lastTime
	 */
	public void setLastTime(String lastTime) {
		this.lastTime=lastTime;
	}
	/**
	 * 获取alter1
	 */
	public String getAlter1() {
		return alter1;
	}
	/**
	 * 设置alter1
	 */
	public void setAlter1(String alter1) {
		this.alter1=alter1;
	}
	/**
	 * 获取alter2
	 */
	public String getAlter2() {
		return alter2;
	}
	/**
	 * 设置alter2
	 */
	public void setAlter2(String alter2) {
		this.alter2=alter2;
	}

}