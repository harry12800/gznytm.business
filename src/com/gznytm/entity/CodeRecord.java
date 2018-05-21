package com.gznytm.entity; 

/**
 * @author:周国柱
 * <p>代码自动生成!数据库的资源文件.
 * <p>jdbc:mysql://127.0.0.1:3306/code
 * <p>root
 * <p>tiger
 */
public class CodeRecord {


	@TableField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";

	@TableField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "userid")
	private String userid= "";

	@TableField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "create_time")
	private date createTime= null;

	@TableField(value="null",type=1,sort=1, title ="null", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "import_result")
	private Integer importResult= 0;
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
	public date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置createTime
	 */
	public void setCreateTime(date createTime) {
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