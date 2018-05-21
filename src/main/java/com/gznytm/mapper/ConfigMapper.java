package com.gznytm.mapper;

import com.gznytm.entity.Config;

public interface ConfigMapper extends CrudDao<Config>{
	public int updatePath(Config config);
}
