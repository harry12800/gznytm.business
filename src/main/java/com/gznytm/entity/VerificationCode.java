package com.gznytm.entity; 

import java.util.Date;

import cn.harry12800.tools.DbField;
import cn.harry12800.tools.DbTable;

/**
 * @author:周国柱
 * <p>代码自动生成!数据库的资源文件.
 * <p>jdbc:mysql://127.0.0.1:3306/code
 * <p>root
 * <p>tiger
 */
@DbTable(tableName = "verification_code")
public class VerificationCode {


	@DbField(value="主键",type=1,sort=1,isKey=true, title ="主键", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";

	@DbField(value="编号",type=1,sort=1, title ="编号",imp=true, exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "code")
	private String code= "";

	@DbField(value="状态",type=1,sort=1, title ="状态", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "status")
	private Integer status= 0;

	@DbField(value="创建用户",type=1,sort=1, title ="创建用户", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "create_user")
	private String createUser= "";

	@DbField(value="创建时间",type=1,sort=1, title ="创建时间",isCreateTime=true, exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "create_Date")
	private Date createDate= null;

	@DbField(value="更新用户",type=1,sort=1, title ="更新用户", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "update_user")
	private String updateUser= "";

	@DbField(value="更新时间",type=1,sort=1, title ="更新时间",isMoidfyTime=true, exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "update_Date")
	private Date updateDate= null;
	
	@DbField(value="操作号",type=1,sort=1, title ="操作号", exp=true,  canAdd = false, canEdit = false, canSearch = false, dbFieldName = "record_id")
	private String recordId= null;

	@DbField(value="备注",type=1,sort=1, title ="备注", exp=true,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "remark")
	private String remark= "";
	public static String initSql="create table verification_code( "+
			"id varchar(32) primary key,"+
			"code varchar(32),"+
			"status integer,"+
			"create_user varchar(32),"+
			"create_date date,"+
			"update_user varchar(32),"+
			"update_date date,"+
			"record_id varchar(32),"+
			"remark varchar(255)"+
			")";
			 
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
	 * 获取code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置code
	 */
	public void setCode(String code) {
		this.code=code;
	}
	/**
	 * 获取statuc
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置statuc
	 */
	public void setStatus(Integer status) {
		this.status=status;
	}
	
	/**
	 * 获取recordId
	 *	@return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * 设置recordId
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * 获取createUser
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser=createUser;
	}
	/**
	 * 获取createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate=createDate;
	}
	/**
	 * 获取upDateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * 设置upDateUser
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser=updateUser;
	}
	/**
	 * 获取upDateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置upDateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate=updateDate;
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

}