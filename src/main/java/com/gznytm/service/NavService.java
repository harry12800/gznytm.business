package com.gznytm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gznytm.entity.Nav;
import com.gznytm.mapper.NavMapper;
@Component
public class NavService {
	@Autowired
	NavMapper navMapper;

	public void saveData(Nav t) {
		navMapper.save(t);
	}

	public void updateData(Nav t) {
		navMapper.update(t);
	}

	public Nav findById(String id) {
		return navMapper.findById(id);
	}

	public List<Nav> exeSql(String sql) {
		return navMapper.executeSql(sql);
	}

	public void deleteBySql(String tableSelectMamaId) {
		navMapper.deleteByIds(tableSelectMamaId);
	}

}
