/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.gznytm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.gznytm.entity.TButton;
import com.gznytm.mapper.TButtonMapper;

/**
 * Service
 * @author 周国柱
 * @version 1.0
 */
@Component
@Transactional(readOnly = true)
public class TButtonService {// extends CrudService<TButtonMapper, TButton> {

	@Autowired
	TButtonMapper mapper;
	
	public TButton findById(String id){
		return mapper.findById(id);
	}
	public List<TButton> findAll() {
		return mapper.findAll();
	}
	public int save(TButton t){
		return mapper.save(t);
	}
	public int update(TButton t){
		return mapper.update(t);
	}
	public List<TButton> findBySql(String sql){
		return mapper.findBySql(sql);
	}
	public int deleteByIds(String ids){
		return mapper.deleteByIds(ids);
	}
}
	