package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.VerificationCode;

public interface VerificationCodeMapper extends CrudDao<VerificationCode> {
	public VerificationCode findById(String id);

	public List<VerificationCode> findAll();

	public int save(VerificationCode t);

	public int update(VerificationCode t);

	public List<VerificationCode> findBySql(String sql);

	public int deleteByIds(String ids);

	public void updateUsedByRecordId(VerificationCode t);

	public void updateUnusedByRecordId(VerificationCode t);

	public void deleteByRecordId(String id);
}