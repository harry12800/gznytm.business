package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.Dao;

public interface DaoMapper extends  CrudDao<Dao> {
	public Dao findById(String id);
	public List<Dao> findAll() ;
	public int save(Dao t);
	public int update(Dao t);
	public List<Dao> findBySql(String sql);
	public int deleteByIds(String ids);
}