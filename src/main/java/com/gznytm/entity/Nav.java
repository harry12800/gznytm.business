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
@DbTable(tableName="nav")
public class Nav {

	/**
	 * 主键
	 */
	@DbField(value="主键",type=1,sort=1,isKey=true, title ="主键", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private Integer id= 0;
	/**
	 * 菜单名称
	 */
	@DbField(value="菜单名称",type=1,sort=1, title ="菜单名称", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "name")
	private String name= "";
	/**
	 * 菜单类型
	 */
	@DbField(value="菜单类型",type=1,sort=1, title ="菜单类型", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "type")
	private String type= "";
	/**
	 * 父主键
	 */
	@DbField(value="父主键",type=1,sort=1, title ="父主键", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "parent_id")
	private Integer parentId= 0;
	/**
	 * 描述
	 */
	@DbField(value="描述",type=1,sort=1, title ="描述", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "description")
	private String description= "";
	/**
	 * 排序号
	 */
	@DbField(value="排序号",type=1,sort=1, title ="排序号", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "sort")
	private Integer sort= 0;
	/**
	 * 链接路径
	 */
	@DbField(value="链接路径",type=1,sort=1, title ="链接路径", exp=true, canAdd = true, canEdit = true, canSearch = false, dbFieldName = "url")
	private String url= "";
	/**
	 * 打开目标
	 */
	@DbField(value="打开目标",type=1,sort=1, title ="打开目标", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "target")
	private String target= "";
	/**
	 * 是否叶子节点,1是叶子，0不是
	 */
	@DbField(value="是否叶子节点,1是叶子，0不是",type=1,sort=1, title ="是否叶子节点,1是叶子，0不是", exp=true,width=100,  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "is_leaf")
	private Integer isLeaf= 0;
	/**
	 * 所属应用
	 */
	@DbField(value="所属应用",type=1,sort=1, title ="所属应用", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "application_id")
	private Integer applicationId= 0;
	/**
	 * 获取主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置主键
	 */
	public void setId(Integer id) {
		this.id=id;
	}
	/**
	 * 获取菜单名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置菜单名称
	 */
	public void setName(String name) {
		this.name=name;
	}
	/**
	 * 获取菜单类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置菜单类型
	 */
	public void setType(String type) {
		this.type=type;
	}
	/**
	 * 获取父主键
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置父主键
	 */
	public void setParentId(Integer parentId) {
		this.parentId=parentId;
	}
	/**
	 * 获取描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置描述
	 */
	public void setDescription(String description) {
		this.description=description;
	}
	/**
	 * 获取排序号
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置排序号
	 */
	public void setSort(Integer sort) {
		this.sort=sort;
	}
	/**
	 * 获取链接路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置链接路径
	 */
	public void setUrl(String url) {
		this.url=url;
	}
	/**
	 * 获取打开目标
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * 设置打开目标
	 */
	public void setTarget(String target) {
		this.target=target;
	}
	/**
	 * 获取是否叶子节点,1是叶子，0不是
	 */
	public Integer getIsLeaf() {
		return isLeaf;
	}
	/**
	 * 设置是否叶子节点,1是叶子，0不是
	 */
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf=isLeaf;
	}
	/**
	 * 获取所属应用
	 */
	public Integer getApplicationId() {
		return applicationId;
	}
	/**
	 * 设置所属应用
	 */
	public void setApplicationId(Integer applicationId) {
		this.applicationId=applicationId;
	}

}