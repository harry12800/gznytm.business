package com.gznytm.mapper;

import com.gznytm.entity.SerialCode;

public interface SerialCodeMapper  extends CrudDao<SerialCode>{
	public SerialCode getCodeByMonth(String month);
	public void updateCodeCurrentMonth(String month);
	public void insert(SerialCode x);
}
