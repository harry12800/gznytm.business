package com.gznytm.config;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gznytm.entity.Config;
import com.gznytm.mapper.ConfigMapper;
import com.gznytm.service.ValidateService;

import cn.harry12800.dbhelper.DBBase;
import cn.harry12800.dbhelper.DBType;
import cn.harry12800.j2se.component.NotifyWindow;
import cn.harry12800.j2se.dialog.MessageDialog;
import cn.harry12800.tools.DbInitSentence;
import cn.harry12800.tools.DbInitType;
import cn.harry12800.tools.DbTable;
import cn.harry12800.tools.FileUtils;
import cn.harry12800.tools.MachineUtils;
import cn.harry12800.tools.NetworkUtils;
import cn.harry12800.tools.RegularExpression;

public class SysConfig {
	public static final Map<String, String> map = new HashMap<String, String>(0);
	public static String urlString = "config/SqlMapConfig.xml";
	public static String springUrl = "classpath:applicationContext.xml";
	public static String dbUrl = "C:/nytm/db.properties";
	public static ApplicationContext instance = null;
	public static final DBType dbType = DBType.MYSQL;
	public static final String Version = "1.0";
	static {
		System.out.println(System.getProperty("os.name"));
		/**
		 * 判断是linux 还是windows
		 */
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			 
		} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			dbUrl = "/Users/java/nytm/db.properties";
		} else {
			dbUrl = "/home/java/nytm/db.properties";
		}
		NotifyWindow.out("数据库config path :" + dbUrl + "！");
	}
	static Timer time;

	public static void init() {
		/**
		 * 初始化数据库
		 */
		initDBConfig();
		/**
		 * 读取资源配置文件，映射map
		 */
		// readResourceConfig();
		time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				NotifyWindow.out("已使用（" + MachineUtils.getUsedMemery() + "）M内存");
				NotifyWindow.out("剩余（" + MachineUtils.getRestMemery() + "）M内存");
			}
		}, 10, 60 * 60 * 1000);
		instance = new ClassPathXmlApplicationContext(springUrl);
		ConfigMapper bean = instance.getBean(ConfigMapper.class);
		List<Config> list = null;
		try {
			list = bean.findAll();
		} catch (Exception e) {
			 new MessageDialog(null,"温馨提示","未找到数据库服务程序！");
		}
		if (list != null)
			for (Config config : list) {
				map.put(config.getReal_name(), config.getParam_value());
			}
	}

	private static boolean testDB() {
		ValidateService s = instance.getBean(ValidateService.class);
		try {
			mainTemp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s.test();
	}

	public static void mainTemp() throws Exception {
		Set<Class<?>> clazz = getClasses("com.gznytm.entity");
		for (Class<?> class1 : clazz) {
			DbTable annotation = class1.getAnnotation(DbTable.class);
			if (annotation == null)
				continue;
			String tableName = annotation.tableName();
			ValidateService s = instance.getBean(ValidateService.class);
			boolean flag = s.validateTableExist(tableName);
			if (!flag) {
				Field[] fields = class1.getFields();
				for (Field field : fields) {
					DbInitSentence dbInitSentence = field.getAnnotation(DbInitSentence.class);
					if (dbInitSentence == null)
						continue;
					DbInitType type = dbInitSentence.type();
					if (type == DbInitType.Create) {
						String sql = field.get(null).toString();
						// http://idea.imsxm.com/
						field.setAccessible(true);
						field.set(null, null);
						try {
							s.createTable(sql);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				for (Field field : fields) {
					DbInitSentence dbInitSentence = field.getAnnotation(DbInitSentence.class);
					if (dbInitSentence == null)
						continue;
					DbInitType type = dbInitSentence.type();
					if (type == DbInitType.insert) {
						String sql = field.get(null).toString();
						field.setAccessible(true);
						field.set(null, null);
						s.insertSql(sql);
					}
				}
			}
		}
	}

	public static List<Class<?>> getClazz(String p) throws Exception {
		List<Class<?>> list = new ArrayList<Class<?>>();
		String re = p.replace(".", "/");
		URL resource = Thread.currentThread().getContextClassLoader().getResource(re);
		File file = new File(resource.getPath());
		if (!file.exists()) {

		}
		File[] fsFiles = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith(".class"))
					return true;
				return false;
			}
		});
		for (File f : fsFiles) {
			String cname = p + "." + f.getName().substring(0, f.getName().lastIndexOf("."));
			Class<?> sClass = Class.forName(cname);
			DbTable annotation = sClass.getAnnotation(DbTable.class);
			if (annotation != null)
				list.add(sClass);
		}
		return list;
	}

	/**
	 * 初始化数据库配置
	 */
	private static void initDBConfig() {
		List<String> serverip = null;
		boolean flag = false;
		if (new File(dbUrl).exists()) {
			NotifyWindow.out("使用上次的服务器IP......");
			try {
				SysConfig.class.getResourceAsStream(urlString);
			} catch (Exception e) {
				NotifyWindow.error("使用上次的服务器IP时发生异常..");
				NotifyWindow.error(e.getMessage());
				e.printStackTrace();
			}
			instance = new ClassPathXmlApplicationContext(springUrl);
			try {
				Map<String, String> mapd = FileUtils.properties2Map(new FileInputStream(dbUrl));
				for (Entry<String, String> enty : mapd.entrySet()) {
					NotifyWindow.out(enty.getKey() + ":" + enty.getValue());

				}
				flag = testDB();
				if (flag) {
					NotifyWindow.out("进来了" + mapd.get("jdbc.url"));
					Pattern p = Pattern.compile(RegularExpression.ipv4);
					Matcher m = p.matcher(mapd.get("jdbc.url"));
					if (m.find()) {
						NotifyWindow.out("匹配的地址" + m.group());
						SysConfig.map.put("serverIp", m.group());
						SysConfig.map.putAll(mapd);
					}
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		NotifyWindow.out("database type is " + dbType);
		NotifyWindow.out("从局域网中查找服务器IP......");
		try {
			if (dbType == DBType.ORACLE)
				serverip = NetworkUtils.getPortServerIp(1521);
			else if (dbType == DBType.MYSQL) {
				serverip = NetworkUtils.getPortServerIp(3306);
			}
		} catch (Exception e1) {
			NotifyWindow.error(e1.getMessage());
			e1.printStackTrace();
		}
		serverip.add("127.0.0.1");
		NotifyWindow.out("查找到IP：" + serverip);
		int i = 0;
		Map<String, String> properties2Map = null;
		try {
			if (dbType == DBType.MYSQL)
				properties2Map = FileUtils.properties2Map("mysql.properties");
			else
				properties2Map = FileUtils.properties2Map("oracle.properties");
		} catch (IOException e1) {
			NotifyWindow.out("本身数据库配置错误！");
		}
		while (!flag && i < serverip.size()) {
			try {
				NotifyWindow.out("检测IP（" + serverip.get(i) + "）是否可用");
				// System.out.println("检测IP（"+serverip.get(i)+"）是否可用");
				/* 读取预先准备的配置文件 */
				Map<String, String> mapd = DBBase.getDBBase(dbType, serverip.get(i++), properties2Map.get("port"),
						properties2Map.get("dbName"), properties2Map.get("userName"), properties2Map.get("pwd"));
				/* 修改配置文件 */
				System.out.println(dbUrl);
				FileUtils.map2Properties(mapd, dbUrl);
				NotifyWindow.out("数据库配置");
				for (Entry<String, String> enty : mapd.entrySet()) {
					NotifyWindow.out(enty.getKey() + ":" + enty.getValue());
				}
				SysConfig.class.getResourceAsStream(urlString);
				NotifyWindow.out("IP：" + serverip.get(i - 1) + "正确！");
				SysConfig.map.put("serverIp", serverip.get(i - 1));
				SysConfig.map.putAll(mapd);
				instance = new ClassPathXmlApplicationContext(springUrl);
				flag = testDB();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "未找到数据库服务程序！");
			}
		}
	}

	//
	// public static void main(String[] args) throws Exception {
	// SqlSession sqlSession = sqlSessionFactory.openSession();
	// ConfigMapper mapper = sqlSession
	// .getMapper(ConfigMapper.class);
	// Config config =new Config();
	// config.setId(StringUtils.getUUID());
	// config.setParam_name("导入-订单备注");
	// config.setParam_value("订单备注");
	// config.setPrompt("保存");
	// config.setReal_name("order_remark");
	// config.setRemark("");
	// config.setType("text");
	// config.setSort(13);
	// mapper.save(config);
	// sqlSession.commit();
	// sqlSession.close();
	// System.out.println("a");
	// }
	// void jarScan(){
	// Set<String> classNames = new HashSet<String>();
	// String packageName = "com.maoshen";
	// String packagePath = packageName.replace(".", "/");
	// ClassLoader loader = Thread.currentThread().getContextClassLoader();
	// URL url = loader.getResource("" + packagePath);
	// if (url != null) {
	// String protocol = url.getProtocol();
	// if (protocol.equals("file")) {
	// classNames = getClassNameFromDir(url.getPath(), packageName, true);
	// }
	// }
	// classNames.remove(this.getClass().getName());
	// }
	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static Set<Class<?>> getClasses(String pack) {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					System.err.println("file类型的扫描");
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					System.err.println("jar类型的扫描");
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											// log
											// .error("添加用户自定义视图类错误
											// 找不到此类的.class文件");
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						// log.error("在扫描用户定义视图时从jar包获取文件出错");
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
			Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
						classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					// classes.add(Class.forName(packageName + '.' +
					// className));
					// 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
					classes.add(
							Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					// log.error("添加用户自定义视图类错误 找不到此类的.class文件");
					e.printStackTrace();
				}
			}
		}
	}

	public static void refersh() {

	}
}
