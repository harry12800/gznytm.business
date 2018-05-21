package com.gznytm.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gznytm.config.SysConfig;
import com.gznytm.entity.VerificationCode;
import com.gznytm.mapper.VerificationCodeMapper;
@Component
@Transactional
public class VerificationCodeService {
	@Autowired
	VerificationCodeMapper verificationCodeMapper;

	public void saveData(VerificationCode t) {
		verificationCodeMapper.save(t);
	}

	public void updateData(VerificationCode t) {
		verificationCodeMapper.update(t);
	}

	public List<VerificationCode> executeQuery(String sql) {
		return verificationCodeMapper.findBySql(sql);
	}

	public void deleteBySql(String sql) {
		verificationCodeMapper.deleteByIds(sql);
	}
	public void deleteByRecordId(String id) {
		verificationCodeMapper.deleteByRecordId(id);
	}
	/**
	 * 
	 * @param id
	 */
	public void updateUsedByRecordId(String id) {
		VerificationCode t = new VerificationCode();
		t.setRecordId(id);
		t.setUpdateDate(new Date());
		t.setUpdateUser(SysConfig.map.get("user"));
		t.setStatus(1);
		verificationCodeMapper.updateUnusedByRecordId(t );
	}

	public void updateUnusedByRecordId(String id) {
		VerificationCode t = new VerificationCode();
		t.setRecordId(id);
		t.setUpdateDate(new Date());
		t.setUpdateUser(SysConfig.map.get("user"));
		t.setStatus(0);
		verificationCodeMapper.updateUnusedByRecordId(t );
	}
}
