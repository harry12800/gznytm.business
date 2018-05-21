package com.gznytm.config;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.swing.JPanel;

import cn.harry12800.j2se.action.IFileChooserExecute;
import cn.harry12800.j2se.action.SelectDirActioner;
import cn.harry12800.j2se.action.SelectFileActioner;
import cn.harry12800.j2se.component.InputText;
import cn.harry12800.j2se.component.NotifyWindow;
import cn.harry12800.j2se.component.PlainButton;
import cn.harry12800.j2se.component.TextLabel;
import cn.harry12800.j2se.component.TextLabel.Builder;
import cn.harry12800.j2se.component.btn.LabelButton;
import cn.harry12800.j2se.component.panel.SelectButtonDialog;
import cn.harry12800.j2se.component.utils.GBC;
import cn.harry12800.j2se.dialog.MessageDialog;
import cn.harry12800.j2se.style.UI;
import cn.harry12800.j2se.utils.Clip;
import cn.harry12800.tools.MachineUtils;

import com.gznytm.entity.Config;
import com.gznytm.login.LoginFrame;
import com.gznytm.main.MainFrame;
import com.gznytm.service.ConfigService;

@SuppressWarnings("serial")
public class ConfigPanel extends JPanel {
	public static Map<String, String> configs = new HashMap<String, String>();
	static Builder builder;
	static{
		builder = TextLabel.createBgColorBuilder(UI.backColor);
		builder.hasborder = false;
		builder.align = 2;
	}
	TextLabel addrLabel = new TextLabel("未知", 100, 25,builder);
	ConfigService configService = SysConfig.instance
			.getBean(ConfigService.class);

	public ConfigPanel() {
		setName("配置管理");
		setOpaque(false);
		this.setLayout(new GridBagLayout());
		try {
			init(SysConfig.map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	private void init(Map<String, String> map) throws IOException {
		
		TextLabel addr = new TextLabel("当前位置：", 100, 25,builder);
//		JLabel addr = new JLabel("当前位置：");
		add(addr, new GBC(0, 0, GBC.EAST, new Insets(1, 1, 1, 1), 1, 1));
		add(addrLabel, new GBC(1, 0, GBC.WEST, new Insets(1, 1, 1, 1), 1, 1));
		List<Config> list = configService.findAll();
		int i = 1;
		for (final com.gznytm.entity.Config config : list) {
			TextLabel label = new TextLabel(config.getParam_name() + "：", 100, 25,builder);
			configs.put(config.getReal_name(), config.getParam_value());
			final InputText text = new  InputText(30);
			text.setForeground(Color.WHITE);
			cn.harry12800.j2se.component.PlainButton.Builder a =
					PlainButton.createBgColorBuilder(UI.foreColor);
			a.hasborder=false;
			//			final JTextField text = new JTextField(config.getParam_value(), 100);
			PlainButton btn = new PlainButton( config.getPrompt() ,100,25,a);
			
			
			add(label, new GBC(0, i, GBC.EAST, new Insets(1, 1, 1, 1), 1, 1));
			add(text, new GBC(1, i, GBC.EAST, new Insets(1, 1, 1, 1), 1, 1));
			add(btn, new GBC(3, i, GBC.EAST, new Insets(1, 1, 1, 1), 1, 1));
			if ("file".equals(config.getType())) {
				a.hasborder=false;
				//			final JTextField text = new JTextField(config.getParam_value(), 100);
				PlainButton modify = new PlainButton( "打开修改" ,100,25,a);
				
				add(modify, new GBC(4, i, GBC.EAST, new Insets(1, 1, 1, 1), 1,1));
				modify.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							Clip.openFile(config.getParam_value());
						} catch (Exception e1) {
							new MessageDialog(null,"温馨提示",e1.getMessage());
						}
					}
				});

				btn.addActionListener(new SelectFileActioner(
						new IFileChooserExecute() {
							@Override
							public int execute(String path, String name) {
								text.setText(path);
								config.setParam_value(path);
								configService.updatePath(config);
								SysConfig.map.put(config.getReal_name(), path);
								return 0;
							}
						}));
			} else if ("dir".equals(config.getType())) {
				btn.addActionListener(new SelectDirActioner(
						new IFileChooserExecute() {
							@Override
							public int execute(String path, String name) {
								// System.out.println(path+"   "+name);
								text.setText(path);
								config.setParam_value(path);
								configService.updatePath(config);
								SysConfig.map.put(config.getReal_name(), path);
								return 0;
							}
						}, config.getParam_value()));
			} else if ("text".equals(config.getType())) {
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						config.setParam_value(text.getText().trim());
						configService.updatePath(config);
						SysConfig.map.put(config.getReal_name(), text.getText()
								.trim());
					}
				});
			} else if ("int".equals(config.getType())) {
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						config.setParam_value(text.getText().trim());
						configService.updatePath(config);
						SysConfig.map.put(config.getReal_name(), text.getText()
								.trim());
					}
				});
			} else if ("print".equals(config.getType())) {
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						List<String> printName = MachineUtils.getPrintName();
						System.out.println(printName);
						if (printName.size() == 0) {
							NotifyWindow.error("本机没有打印机！可在服务中开启.");
							return;
						}
						new SelectButtonDialog(printName) {
							public void exe(String name) {
								text.setText(name);
								configs.put(config.getReal_name(), name);
								config.setParam_value(name);
								configService.updatePath(config);
								SysConfig.map.put(config.getReal_name(), text
										.getText().trim());
								Clip.modifyPrintByName(name);
							}
						};
					}
				});
			}
			i++;
		}
		final LabelButton labelButton = new LabelButton("退出登录", 100, 30);
		add(labelButton, new GBC(3, i, GBC.EAST, new Insets(1, 1, 1, 1), 1, 1));
		labelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(labelButton.contains(e.getPoint())){
					MainFrame.getInstance().dispose();
					MainFrame.instance = null;
					LoginFrame.display();
					super.mouseClicked(e);
				}
			}
		});
	}
	public static String getOrderIdByUUId() {
		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}

	public static void main(String[] args) {
		System.out.println(getOrderIdByUUId());
		System.out.println(UUID.randomUUID().toString());
	}
}
