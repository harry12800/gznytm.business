package com.gznytm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gznytm.entity.BusinessOrder;
import com.gznytm.mapper.BusinessOrderMapper;
@Component
@Transactional
public class BusinessOrderService {

	@Autowired
	BusinessOrderMapper businessOrderMapper;

	public List<BusinessOrder> executeSql(String sql) {
		return businessOrderMapper.executeSql(sql);
	}

	public void update(BusinessOrder t) {
		businessOrderMapper.update(t);
	}

	public BusinessOrder findById(String id) {
		return businessOrderMapper.findById(id);
	}

	public void deleteBySql(String sql) {
		businessOrderMapper.deleteBySql(sql);
		
	}

	/**
	 * 把颜色标记改正常
	 * @param id
	 */
	public void updateNormal(String id) {
		businessOrderMapper.updateNormal(id);		
	}

	public void updateBlue(String id) {
		businessOrderMapper.updateBlue(id);
	}

	public void updateRed(String id) {
		businessOrderMapper.updateRed(id);
	}

	public void updateOther(BusinessOrder update) {
		businessOrderMapper.updateOther(update);
		
	}

	public List<BusinessOrder> findByMeriratlCode(String material_code) {
		return businessOrderMapper.findByMeriratlCode(material_code);
	}

	public void updateIsPrintStatus(String id) {
		businessOrderMapper.updateIsPrintStatus(id);
		
	}

	public void insert(BusinessOrder t) {
		businessOrderMapper.insert(t);
	}

	public void updateNumber(BusinessOrder bo) {
		businessOrderMapper.updateNumber(bo);
	}

	public void insertAll(List<BusinessOrder> list) {
		for (BusinessOrder businessOrder : list) {
			businessOrderMapper.insert(businessOrder);
		}
	}

	/**
	 * 更新尺寸等消息
	 * @param selectBeans
	 */
	public void updateOthers(List<BusinessOrder> selectBeans) {
		for (BusinessOrder businessOrder : selectBeans) {
			List<BusinessOrder> bes = findByMeriratlCode(businessOrder.getMaterialCode());
			BusinessOrder update = getUpdateBusiness(bes);
			if (update != null) {
				update.setId(businessOrder.getId());
				updateOther(update);
			}
		}
	}
	/**
	 * 返回第一个尺寸不为空的订单
	 * @param bes
	 * @return
	 */
	private BusinessOrder getUpdateBusiness(List<BusinessOrder> bes) {
		for (BusinessOrder businessOrder : bes) {
			if (businessOrder.getMaterialSize() != null && !businessOrder.getMaterialSize().trim().equals("")) {
				return businessOrder;
			}
		}
		return null;
	}
	public List<BusinessOrder> searchSql(BusinessOrder bo){
		return businessOrderMapper.searchSql(bo);
	}

	public int findCount(String mysqlPageContent) {
		return businessOrderMapper.findCount(mysqlPageContent);
	}
}
