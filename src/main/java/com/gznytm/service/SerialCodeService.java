package com.gznytm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gznytm.entity.SerialCode;
import com.gznytm.mapper.SerialCodeMapper;
@Component
public class SerialCodeService {
	@Autowired
	SerialCodeMapper serialCodeMapper;

	public SerialCode getCodeByMonth(String month) {
		return serialCodeMapper.getCodeByMonth(month);
	}

	public void insert(SerialCode x) {
		serialCodeMapper.insert(x);
	}

	public void updateCodeCurrentMonth(String month) {
		serialCodeMapper.updateCodeCurrentMonth(month);
	}
}
