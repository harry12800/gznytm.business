package com.gznytm.main;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class AppMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	UserApplicationService bean = SysConfig.instance.getBean(UserApplicationService.class);
	public AppMenuPanel(String userId) {
		System.out.println(":"+userId);
		setLayout(new BorderLayout());
//		List<Application> list = bean.getUserApp(userId);
//		for (Application application : list) {
//			
//			List<TPage> pages = bean.getMenuByUserApp(application);
//			application.setPages(pages);
//			add(new JButton(application.getName()));
//		}
		
	}
}
