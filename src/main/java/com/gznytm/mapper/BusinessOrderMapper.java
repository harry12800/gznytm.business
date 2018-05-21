package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.BusinessOrder;

public interface BusinessOrderMapper extends CrudDao<BusinessOrder> {
	public List<BusinessOrder> findByMeriratlCode(String code) ;
	public void insert(BusinessOrder order);
	public void deleteBySql(String sql);
	public List<BusinessOrder> executeSql(String sql);
	public List<BusinessOrder> searchSql(BusinessOrder bo);
	/**
	 * 更新菲林刀模信息
	 * @param bo
	 */
	public void updateOther(BusinessOrder bo);
	public void updateNumber(BusinessOrder bo);
	public void updateMaterialcodeDesc(BusinessOrder order);
	public void updateDesc(BusinessOrder order);
	public void updateIsPrintStatus(String id);
	public void updateRed(String id);
	public void updateBlue(String id);
	public void updateNormal(String id);
	public int findCount(String sql);
}
