package com.gznytm.main;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.harry12800.developer.DeveloperUtils;
import cn.harry12800.tools.NetworkUtils;

public class Main {
	static String url = "jdbc:mysql://119.23.9.164:3306/scan";
	static String user = "root";
	static String pwd = "zhouguozhu";
	static String[]tableName = {"t_button", "t_application","t_class_method","t_node","t_page","t_person","t_role","t_user"};
	public static void main(String[] args) throws Exception {
//		for (String str : tableName) {
			 DeveloperUtils.generateDbEntityByTableName( url, user, pwd,"remind");
//			break ;
//		}
//			System.out.println(x);
//		run(args);
		// stop();
	}

	static void stop() {
		new Timer().schedule(new TimerTask() {
			public void run() {
				System.exit(1);
			}
		}, 120000);
	}


	public static void run(String[] args) throws Exception {
		runSocket();
		while (true) {
			if (ServerThread.mark) {
				MainFrame.getInstance();
				break;
			}
		}
	}

	public static void main2(String[] args) throws Exception {
		// boolean portIsOpen = NetworkUtils.portIsOpen("192.168.0.114", 1521);
		// System.out.println(portIsOpen);
		// JarUtils.jarProtectByName("gznytm.business",null,true,false);
		// DeveloperUtils.generateDbEntity("com.gznytm.db.entity",
		// "jdbc:mysql://127.0.0.1:3306/nytm", "root", "admin");
		List<String> someIntranetIp = NetworkUtils.getSomeIntranetIp("192.168.10.114", 1521);
		System.out.println(someIntranetIp);

	}

	private static void runSocket() {
		new ServerThread().start();
	}
}
