package com.gznytm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gznytm.entity.CodeRecord;
import com.gznytm.mapper.CodeRecordMapper;

@Component
@Transactional
public class CodeRecordService {

	@Autowired
	CodeRecordMapper mapper;
	public void saveData(CodeRecord t) {
		mapper.save(t);
	}
	public void updateData(CodeRecord t) {
		mapper.update(t);
	}
	public List<CodeRecord> exeSql(String sql) {
		return mapper.findBySql(sql);
	}

}
