package com.gznytm.mapper;

import java.util.List;

import cn.harry12800.dao.BaseDao;

public interface CrudDao<T> extends BaseDao{
	public T findById(String id);
	public List<T> findAll() ;
	public int save(T t);
	public int update(T t);
	public List<T> findBySql(String sql);
	public int deleteByIds(String ids);
}
