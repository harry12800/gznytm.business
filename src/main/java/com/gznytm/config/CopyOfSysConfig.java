package com.gznytm.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.harry12800.dbhelper.DBBase;
import cn.harry12800.dbhelper.DBType;
import cn.harry12800.j2se.component.NotifyWindow;
import cn.harry12800.tools.FileUtils;
import cn.harry12800.tools.MachineUtils;
import cn.harry12800.tools.NetworkUtils;
import cn.harry12800.tools.RegularExpression;
import cn.harry12800.tools.StringUtils;

import com.gznytm.entity.Config;
import com.gznytm.mapper.ConfigMapper;

public class CopyOfSysConfig {
	public static final Map<String, String> map = new HashMap<String, String>(0);
	public static String urlString = "config/SqlMapConfig.xml";
	public static String dbUrl = "D:/nytm/db.properties";
	//public static String dbUrl = "/home/java/nytm/db.properties";
	static{
		if(System.getProperty("os.name").toLowerCase().contains("win")) {
			dbUrl = "D:/nytm/db.properties";
		}
		else{
			dbUrl = "/home/java/nytm/db.properties";
		}
		NotifyWindow.out("数据库config path :"+dbUrl+"！");
	}
	public static SqlSessionFactory sqlSessionFactory = null;
	public static final DBType dbType = DBType.MYSQL;
	static Timer time;
	
	public void init() throws IOException {
		/**
		 * 初始化数据库
		 */
		initDBConfig();
		/**
		 * 读取资源配置文件，映射map
		 */
	//	readResourceConfig();
		time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				NotifyWindow.out("已使用（"+MachineUtils.getUsedMemery()+"）M内存");
				NotifyWindow.out("剩余（"+MachineUtils.getRestMemery()+"）M内存");
			}
		}, 10,60*60*1000);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ConfigMapper mapper = sqlSession.getMapper(ConfigMapper.class);
		List<Config> list = null;
		try {
			list = mapper.findAll();
		} catch (Exception e) {
			NotifyWindow.error( "未找到数据库服务程序！");
		}
		if(list!=null)
		for (Config config : list) {
			map.put(config.getReal_name(), config.getParam_value());
		}
		sqlSession.close();
	}

	private static boolean testDB() {
		SqlSession s = sqlSessionFactory.openSession();
		try{
		 s.getConnection();}
		catch (Exception e) {
			return false;
		}
		return true;
	}

//	private static void readResourceConfig() {
//		try {
//			InputStream in = SysConfig.class.getResourceAsStream("/config.properties");
//			map.putAll(FileUtils.properties2Map(in));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 初始化数据库配置
	 */
	private static void initDBConfig() {
		InputStream inputStream = null;
		List<String> serverip = null;
		boolean flag = false;
		if(new File(dbUrl).exists() ) {
			NotifyWindow.out("使用上次的服务器IP......");
			try {
				inputStream = org.apache.ibatis.io.Resources.getResourceAsStream(urlString);
			} catch (IOException e) {
				NotifyWindow.error("使用上次的服务器IP时发生异常..");
				NotifyWindow.error(e.getMessage());
				e.printStackTrace();
			}
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			try {
				Map<String, String> mapd = FileUtils.properties2Map(new FileInputStream(dbUrl));
				for (Entry<String, String> enty : mapd.entrySet()) {
					NotifyWindow.out(enty.getKey()+":"+enty.getValue());
					
				}
				flag = testDB();
				if(flag) {
					NotifyWindow.out("进来了"+mapd.get("jdbc.url"))	;
					Pattern p =  Pattern.compile(RegularExpression.ipv4);
					Matcher m = p.matcher( mapd.get("jdbc.url"));
					if(m.find()){
						NotifyWindow.out("匹配的地址"+m.group())	;
						CopyOfSysConfig.map.put("serverIp", m.group());
						CopyOfSysConfig.map.putAll(mapd);
					}
					return ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		NotifyWindow.out("database type is "+dbType);
		NotifyWindow.out("从局域网中查找服务器IP......");
		try {
			if(dbType == DBType.ORACLE)
				serverip = NetworkUtils.getPortServerIp(1521);
			else if(dbType == DBType.MYSQL){
				serverip = NetworkUtils.getPortServerIp(3306);
			}
		} catch (Exception e1) {
			NotifyWindow.error(e1.getMessage());
			e1.printStackTrace();
		}
		serverip.add(0,"127.0.0.1");
		NotifyWindow.out("查找到IP："+serverip);
		 
		int i = 0;
		while(!flag&&i<serverip.size()){
			try {
				NotifyWindow.out("检测IP（"+serverip.get(i)+"）是否可用");
				/*读取预先准备的配置文件*/
				Map<String, String> properties2Map =null;
				if(dbType == DBType.MYSQL)
					properties2Map = FileUtils.properties2Map("mysql.properties");
				else{
					properties2Map = FileUtils.properties2Map("oracle.properties");
				}
				Map<String, String> mapd = DBBase.getDBBase(
						dbType,serverip.get(i++),
						properties2Map.get("port"),
						properties2Map.get("dbName"),
						properties2Map.get("userName"),
						properties2Map.get("pwd")
						);
				/*修改配置文件*/
				FileUtils.map2Properties(mapd,dbUrl);
				NotifyWindow.out("数据库配置");
				for (Entry<String, String> enty : mapd.entrySet()) {
					NotifyWindow.out(enty.getKey()+":"+enty.getValue());
				}
				inputStream = org.apache.ibatis.io.Resources
						.getResourceAsStream(urlString);
				NotifyWindow.out("IP："+serverip.get(i-1)+"正确！");
				CopyOfSysConfig.map.put("serverIp", serverip.get(i-1));
				CopyOfSysConfig.map.putAll(mapd);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				flag = testDB();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "未找到数据库服务程序！");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ConfigMapper mapper = sqlSession
				.getMapper(ConfigMapper.class);
		Config config =new Config();
		config.setId(StringUtils.getUUID());
		config.setParam_name("导入-订单备注");
		config.setParam_value("订单备注");
		config.setPrompt("保存");
		config.setReal_name("order_remark");
		config.setRemark("");
		config.setType("text");
		config.setSort(13);
		mapper.save(config);
		sqlSession.commit();
		sqlSession.close();
//		System.out.println("a");
	}

	public static void refersh() {

	}
}
