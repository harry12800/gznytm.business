package com.gznytm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gznytm.entity.Config;
import com.gznytm.mapper.ConfigMapper;

@Component
public class ConfigService {

	@Autowired
	ConfigMapper configMapper;

	public List<Config> findAll() {
		return configMapper.findAll();
	}

	public void updatePath(Config config) {
		configMapper.updatePath(config);
	}
	
}
