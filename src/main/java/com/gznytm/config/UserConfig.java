package com.gznytm.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import cn.harry12800.tools.FileUtils;
import cn.harry12800.tools.Maps;

public class UserConfig {
	Map<String, String>map = Maps.newHashMap();
	static Map<String, UserConfig>userMap = Maps.newHashMap();
	String path="";
	public static synchronized UserConfig getInstance(String userName){
		UserConfig userConfig = userMap.get(userName);
		if(userConfig==null){
			userConfig = new UserConfig();
			userMap.put(userName, userConfig);
			userConfig.path=System.getProperty("user.dir")+File.separator+"user"+File.separator+userName+File.separator+"config.ini";
			try {
				if(!new File(userConfig.path).exists())
				FileUtils.createFile(userConfig.path);
				userConfig.map=FileUtils.properties2Map(new FileInputStream(new File(userConfig.path)));
				FileUtils.map2Properties(userConfig.map, new File(userConfig.path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userConfig;
	}
	public String getProp(String key){
		return map.get(key);
	}
	public synchronized  void setProp(String key,String value) {
		FileUtils.appendContent(path, key+"="+value+"\r\n");
		map.put(key, value);
	}
	public  synchronized    void setProp(Class<?> class1, String propName,
			String propVal) {
		setProp(class1.getName()+"."+propName, propVal);
	}
	public  String getProp(Class<?> class1, String propName) {
		return getProp(class1.getName()+"."+propName);
	}
}
