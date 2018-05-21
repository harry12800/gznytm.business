package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.CodeRecord;

public interface CodeRecordMapper extends  CrudDao<CodeRecord> {
	public CodeRecord findById(String id);
	public List<CodeRecord> findAll() ;
	public int save(CodeRecord t);
	public int update(CodeRecord t);
	public List<CodeRecord> findBySql(String sql);
	public int deleteByIds(String ids);
}