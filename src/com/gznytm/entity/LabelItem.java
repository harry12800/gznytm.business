package com.gznytm.entity; 

/**
 * @author:harry12800
 * <p>代码自动生成!数据库的资源文件.
 * <p>jdbc:mysql://127.0.0.1:3306/nytm
 * <p>root
 * <p>admin
 */
public class LabelItem {

	/**
	 * 主键
	 */
	@TableField(value="主键",type=1,sort=1, title ="主键", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";
	/**
	 * 名称
	 */
	@TableField(value="名称",type=1,sort=1, title ="名称", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "name")
	private String name= "";
	/**
	 * 查看次数
	 */
	@TableField(value="查看次数",type=1,sort=1, title ="查看次数", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "see_times")
	private Integer seeTimes= 0;
	/**
	 * 收藏次数
	 */
	@TableField(value="收藏次数",type=1,sort=1, title ="收藏次数", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "favorite_times")
	private Integer favoriteTimes= 0;
	/**
	 * 类型
	 */
	@TableField(value="类型",type=1,sort=1, title ="类型", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "type")
	private String type= "";
	/**
	 * 图片一路径
	 */
	@TableField(value="图片一路径",type=1,sort=1, title ="图片一路径", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "image_url1")
	private String imageUrl1= "";
	/**
	 * 图片二路径
	 */
	@TableField(value="图片二路径",type=1,sort=1, title ="图片二路径", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "image_url2")
	private String imageUrl2= "";
	/**
	 * 父项
	 */
	@TableField(value="父项",type=1,sort=1, title ="父项", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "parent_id")
	private Integer parentId= 0;
	/**
	 * 描述
	 */
	@TableField(value="描述",type=1,sort=1, title ="描述", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "description")
	private String description= "";
	/**
	 * 备注
	 */
	@TableField(value="备注",type=1,sort=1, title ="备注", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "remark")
	private String remark= "";
	/**
	 * 排序号
	 */
	@TableField(value="排序号",type=1,sort=1, title ="排序号", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "sort")
	private Integer sort= 0;
	/**
	 * 超链接
	 */
	@TableField(value="超链接",type=1,sort=1, title ="超链接", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "url")
	private String url= "";
	/**
	 * 打开目标
	 */
	@TableField(value="打开目标",type=1,sort=1, title ="打开目标", exp=true,width=100,  canAdd = true, canEdit = false, canSearch = false, dbFieldName = "target")
	private String target= "";
	/**
	 * 获取主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置主键
	 */
	public void setId(String id) {
		this.id=id;
	}
	/**
	 * 获取名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name=name;
	}
	/**
	 * 获取查看次数
	 */
	public Integer getSeeTimes() {
		return seeTimes;
	}
	/**
	 * 设置查看次数
	 */
	public void setSeeTimes(Integer seeTimes) {
		this.seeTimes=seeTimes;
	}
	/**
	 * 获取收藏次数
	 */
	public Integer getFavoriteTimes() {
		return favoriteTimes;
	}
	/**
	 * 设置收藏次数
	 */
	public void setFavoriteTimes(Integer favoriteTimes) {
		this.favoriteTimes=favoriteTimes;
	}
	/**
	 * 获取类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置类型
	 */
	public void setType(String type) {
		this.type=type;
	}
	/**
	 * 获取图片一路径
	 */
	public String getImageUrl1() {
		return imageUrl1;
	}
	/**
	 * 设置图片一路径
	 */
	public void setImageUrl1(String imageUrl1) {
		this.imageUrl1=imageUrl1;
	}
	/**
	 * 获取图片二路径
	 */
	public String getImageUrl2() {
		return imageUrl2;
	}
	/**
	 * 设置图片二路径
	 */
	public void setImageUrl2(String imageUrl2) {
		this.imageUrl2=imageUrl2;
	}
	/**
	 * 获取父项
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置父项
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
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
		this.remark=remark;
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
	 * 获取超链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置超链接
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

}