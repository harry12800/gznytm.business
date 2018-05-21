package com.gznytm.entity; 

import cn.harry12800.tools.DbField;
import cn.harry12800.tools.DbTable;

/**
 * @author:harry12800
 * <p>代码自动生成!数据库的资源文件.
 * <p>jdbc:mysql://127.0.0.1:3306/nytm
 * <p>root
 * <p>admin
 * <p>商用订单表
 */
@DbTable(tableName = "business_order")
public class BusinessOrder {
	/**
	 * 主键
	 */
	@DbField(value="主键",isKey=true,type=0,sort=0, title = "主键",show=false, canAdd = false, canEdit = false, canSearch = false, dbFieldName = "id")
	private String id= "";
	/**
	 * 物料编码
	 */
	@DbField(value="物料编码",type=1,sort=1, title ="物料编码", imp=true,exp=true,width=100,  canAdd = true, canEdit = false, canSearch = true, dbFieldName = "material_code")
	private String materialCode= "";
	/**
	 * 旧编码
	 */
	@DbField(value="旧编码",type=1,sort=1, title = "旧编码", show = false, canAdd = false, canEdit = true, canSearch = true, dbFieldName = "old_order_code")
	private String oldOrderCode= "";
	@DbField(value="订单状态",type=1,sort=1, title = "订单状态",show = false,  canAdd = false, canEdit = false, canSearch = false, dbFieldName = "status")
	private String status= "";
	@DbField(value="订单号",type=1,sort=1, title = "订单号", show = false,imp=true,exp=true, canAdd = false, canEdit = true, canSearch = false, dbFieldName = "orderid")
	private String orderid= "";
	/**
	 * 开单日期
	 */
	@DbField(value="订单日期",type=1,sort=1, title = "订单日期",exp=true, imp=true, canAdd = true, canEdit = true, canSearch = false, dbFieldName = "billing_time")
	private String billingTime= "";
	/**
	 * 订单要求时间
	 */
	@DbField(value="需求日期",type=1,sort=1, title = "需求日期", exp=true,imp=true, canAdd = false, canEdit = true, canSearch = false, dbFieldName = "require_time")
	private String requireTime= "";
	
	/**
	 * 描述信息
	 */
	@DbField(value="物料描述",type=1,sort=1,width=250, title = "描述信息", imp=true, exp=true,canAdd = true, canEdit = true, canSearch = true, dbFieldName = "description")
	private String description= "";
	/**
	 * 数量
	 */
	@DbField(value="订单数量",type=1,sort=1, title = "数量（工厂开单数量）", imp=true,exp=true, canAdd = true, canEdit = true, canSearch = false, dbFieldName = "req_count")
	private String reqCount= "";
	
	/**
	 * 尺寸
	 */
	@DbField(value="尺寸",type=1,sort=1, title = "尺寸", exp=true,imp=false, canAdd = true, canEdit = true, canSearch = true, dbFieldName = "material_size")
	private String materialSize= "";
	/**
	 * 材料，材质
	 */
	@DbField(value="材质",type=1,sort=1, width=250, title = "材质",imp=false, exp=true, canAdd = true, canEdit = true, canSearch = true, dbFieldName = "material")
	private String material= "";
	/**
	 * 非零号
	 */
	@DbField(value="非零号",type=1,sort=1, title = "菲林号",  canAdd = true, canEdit = true, canSearch = true, dbFieldName = "unzero_code")
	private String unzeroCode= "";
	/**
	 * 刀模号
	 */
	@DbField(value="刀模号",type=1,sort=1, title = "刀模号",  canAdd = true, canEdit = true, canSearch = true, dbFieldName = "cutting_die_code")
	private String cuttingDieCode= "";
	/**
	 * 颜色
	 */
	@DbField(value="颜色",type=1,sort=1, title = "印色",  canAdd = true, canEdit = true, canSearch = false, dbFieldName = "color")
	private String color= "";
	/**
	 * 备注
	 */
	@DbField(value="订单备注",type=1,sort=1, title = "备注",imp=true, show = false, canAdd = true, canEdit = true, canSearch = true, dbFieldName = "remark")
	private String remark= "";

	@DbField(value="库存组织",type=1,sort=1, title = "客户",imp=true,show = false,  canAdd = true, canEdit = true, canSearch = true, dbFieldName = "customer")
	private String customer= "";
	/**
	 * 导入时间
	 */
	@DbField(value="导入时间",type=1,sort=1, title = "导入时间", show = false, canAdd = false, canEdit = false, canSearch = false, dbFieldName = "imp_time")
	private String impTime= "";
	/**
	 * 最后修改时间
	 */
	@DbField(value="修改时间",type=1,sort=1, title = "修改时间", show = false, canAdd = false, canEdit = false, canSearch = false, dbFieldName = "last_time")
	private String lastTime= "";
	/**
	 * 该行记录是Excel表中的哪一行
	 */
	@DbField(value="导入索引号",type=1,sort=1, title = "导入行号",show = false,  canAdd = false, canEdit = false, canSearch = false, dbFieldName = "excel_index")
	private Integer excelIndex= 0;

	@DbField(value="颜色标记",type=1,sort=1, title = "颜色标记",show = false,  canAdd = false, canEdit = false, canSearch = false, dbFieldName = "color_flag")
	private String colorFlag= "";
	//@TableField(value="要求数量",type=1,sort=1, title = "要求数量",show = false,  canAdd = false, canEdit = false, canSearch = false, dbFieldName = "number")
	private String number= "";
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
	 * 获取orderid
	 */
	public String getOrderid() {
		return orderid;
	}
	/**
	 * 设置orderid
	 */
	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}
	/**
	 * 获取开单日期
	 */
	public String getBillingTime() {
		return billingTime;
	}
	/**
	 * 设置开单日期
	 */
	public void setBillingTime(String billingTime) {
		this.billingTime=billingTime;
	}
	/**
	 * 获取物料编码
	 */
	public String getMaterialCode() {
		return materialCode;
	}
	/**
	 * 设置物料编码
	 */
	public void setMaterialCode(String materialCode) {
		this.materialCode=materialCode;
	}
	/**
	 * 获取旧编码
	 */
	public String getOldOrderCode() {
		return oldOrderCode;
	}
	/**
	 * 设置旧编码
	 */
	public void setOldOrderCode(String oldOrderCode) {
		this.oldOrderCode=oldOrderCode;
	}
	/**
	 * 获取订单要求时间
	 */
	public String getRequireTime() {
		return requireTime;
	}
	/**
	 * 设置订单要求时间
	 */
	public void setRequireTime(String requireTime) {
		this.requireTime=requireTime;
	}
	/**
	 * 获取描述信息
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置描述信息
	 */
	public void setDescription(String description) {
		this.description=description;
	}
	/**
	 * 获取数量
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置数量
	 */
	public void setNumber(String number) {
		this.number=number;
	}
	/**
	 * 获取寸尺
	 */
	public String getMaterialSize() {
		return materialSize;
	}
	/**
	 * 设置寸尺
	 */
	public void setMaterialSize(String materialSize) {
		this.materialSize=materialSize;
	}
	/**
	 * 获取材料，材质
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * 设置材料，材质
	 */
	public void setMaterial(String material) {
		this.material=material;
	}
	/**
	 * 获取非零号
	 */
	public String getUnzeroCode() {
		return unzeroCode;
	}
	/**
	 * 设置非零号
	 */
	public void setUnzeroCode(String unzeroCode) {
		this.unzeroCode=unzeroCode;
	}
	/**
	 * 获取刀模号
	 */
	public String getCuttingDieCode() {
		return cuttingDieCode;
	}
	/**
	 * 设置刀模号
	 */
	public void setCuttingDieCode(String cuttingDieCode) {
		this.cuttingDieCode=cuttingDieCode;
	}
	/**
	 * 获取颜色
	 */
	public String getColor() {
		return color;
	}
	/**
	 * 设置颜色
	 */
	public void setColor(String color) {
		this.color=color;
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
	 * 获取导入时间
	 */
	public String getImpTime() {
		return impTime;
	}
	/**
	 * 设置导入时间
	 */
	public void setImpTime(String impTime) {
		this.impTime=impTime;
	}
	/**
	 * 获取最后修改时间
	 */
	public String getLastTime() {
		return lastTime;
	}
	/**
	 * 设置最后修改时间
	 */
	public void setLastTime(String lastTime) {
		this.lastTime=lastTime;
	}
	/**
	 * 获取该行记录是Excel表中的哪一行
	 */
	public Integer getExcelIndex() {
		return excelIndex;
	}
	/**
	 * 设置该行记录是Excel表中的哪一行
	 */
	public void setExcelIndex(Integer excelIndex) {
		this.excelIndex=excelIndex;
	}
	/**
	 * 获取customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * 设置customer
	 */
	public void setCustomer(String customer) {
		this.customer=customer;
	}
	/**
	 * 获取status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置status
	 */
	public void setStatus(String status) {
		this.status=status;
	}
	/**
	 * 获取colorFlag
	 */
	public String getColorFlag() {
		return colorFlag;
	}
	/**
	 * 设置colorFlag
	 */
	public void setColorFlag(String colorFlag) {
		this.colorFlag=colorFlag;
	}
	/**
	 * 获取reqCount
	 */
	public String getReqCount() {
		return reqCount;
	}
	/**
	 * 设置reqCount
	 */
	public void setReqCount(String reqCount) {
		this.reqCount=reqCount;
	}
}