package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.Nav;

public interface NavMapper extends  CrudDao<Nav> {
	public Nav findById(String id);
	public List<Nav> findAll() ;
	public int save(Nav t);
	public int update(Nav t);
	public List<Nav> findBySql(String sql);
	public int deleteByIds(String ids);
	public List<Nav> executeSql(String sql);
}