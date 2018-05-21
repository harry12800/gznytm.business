package com.gznytm.mapper;

import cn.harry12800.dao.BaseDao;

public interface ValidateMapper extends BaseDao {
	public void selectBySql(String sql);
	public int deleteBySql(String sql);
	public int insertBySql(String sql);
	public void updateBySql(String sql);
}
