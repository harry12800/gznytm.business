package com.gznytm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gznytm.mapper.BusinessOrderMapper;
import com.gznytm.mapper.ValidateMapper;

@Component
public class ValidateService {

	@Autowired
	BusinessOrderMapper mapper;
	@Autowired
	ValidateMapper mapper1;
	public boolean test() {
		try {
			mapper.findById("1");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * @param tablename
	 * @return
	 */
	public boolean validateTableExist(String tablename) {
		try {
			//mapper.executeSql(tablename);
			mapper1.selectBySql("select 1 from "+tablename +" where 1 != 1");
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean createTable(String sql){
		try {
			mapper1.updateBySql(sql);
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	public void insertSql(String sql) {
		try {
			mapper1.insertBySql(sql);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
