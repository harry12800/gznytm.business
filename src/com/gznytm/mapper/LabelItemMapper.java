package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.LabelItem;

public interface LabelItemMapper extends  BaseMapper<LabelItem> {
	public LabelItem findById(String id);
	public List<LabelItem> findAll() ;
	public int save(LabelItem t);
	public int update(LabelItem t);
	public List<LabelItem> findBySql(String sql);
	public int deleteByIds(String ids);
}