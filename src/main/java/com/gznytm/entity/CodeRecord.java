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
@DbTable(tableName="code_record")
public class CodeRecord {


	@DbField(value="主键",type=1,sort=1, title ="主键",isKey=true, exp=true, canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";

	@DbField(value="操作用户",type=1,sort=1, title ="操作用户", exp=true, canAdd = true, canEdit = false, canSearch = false, dbFieldName = "userid")
	private String userid= "";

	@DbField(value="创建时间",type=1,sort=1, title ="创建时间", exp=true, canAdd = true, canEdit = false, canSearch = false, dbFieldName = "create_time")
	private Date createTime= null;

	@DbField(value="null",type=1,sort=1, title ="导入时间", exp=true, canAdd = true, canEdit = false, canSearch = false, dbFieldName = "import_result")
	private Integer importResult= 0;
	@DbField(value="null",type=1,sort=1, title ="总条数", exp=true, canAdd = false, canEdit = false, canSearch = false, dbFieldName = "count")
	private Integer count= 0;
	public static String initSql=
		"create table code_record("+
			"id varchar(32) primary key,"+
			"userid varchar(32),"+
			"create_time date,"+
			"import_result integer,"+
			"count integer"+
			")";
	
	/**
	 * 获取id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 获取count
	 *	@return the count
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置count
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 设置id
	 */
	public void setId(String id) {
		this.id=id;
	}
	/**
	 * 获取userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置userid
	 */
	public void setUserid(String userid) {
		this.userid=userid;
	}
	/**
	 * 获取createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
	}
	/**
	 * 获取importResult
	 */
	public Integer getImportResult() {
		return importResult;
	}
	/**
	 * 设置importResult
	 */
	public void setImportResult(Integer importResult) {
		this.importResult=importResult;
	}

}