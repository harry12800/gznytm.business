package com.gznytm.main;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import cn.harry12800.j2se.action.DragListener;
import cn.harry12800.j2se.component.utils.ImageUtils;
import cn.harry12800.j2se.popup.ListItem;
import cn.harry12800.j2se.popup.PopupFrame;
import cn.harry12800.j2se.style.J2seColor;
import cn.harry12800.j2se.style.UI;

import com.gznytm.config.SysConfig;
import com.gznytm.hotkey.HotKey;
import com.gznytm.login.LoginCallback;
import com.gznytm.login.LoginFrame;
import com.gznytm.upgrade.Configuration;
import com.gznytm.upgrade.PlatUpdate;
import com.melloware.jintellitype.JIntellitype;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 5575710694424302773L;
	private static boolean mark = false;
	static LoginCallback a = new LoginCallback() {
		public void success() {
			if (instance == null)
				try {
					instance = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		public void fail() { }
	};
	static {
		ImageUtils.addImage(MainFrame.class);
		SysConfig.init();
		LoginFrame.display(a);
	}
	public static MainFrame instance;
	private Image image;
	PlatUpdate platUpdate = PlatUpdate.getInstance();
	static PopupFrame popupFrame = null;
	public static TrayIcon ti;
	static LoginFrame loginFrame;

	public synchronized static MainFrame getInstance() {
		return instance;
	}
	public void setVisible(boolean b) {
		if(b)
			new cn.harry12800.j2se.style.FadeOut(this).display();
		else{
			new cn.harry12800.j2se.style.FadeOut(this).hide();
		}
//		super.setVisible(b);
	}
	private MainFrame() {
		this.image = ImageUtils.getByName("logo_lan.png");
		this.setIconImage(image);
		setUndecorated(true);
		setTitle("南邮条码信息管理（" + Configuration.selfversion + "）");
		setName("南邮条码信息管理（" + Configuration.selfversion + "）");
		setSize(1300, 700);
		setContentPane(new MainPanel(this));
		setCenterScreen();
		setResizable(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==27){
					 setVisible(false);
				}
			}	
		}); 
		//Clip.setStyle(this);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// addComponentListener();
		new DragListener(this);
		if(!mark)
			setTrayImg();
		startPlatUpdate();
		HotKey.hotKey();
	}

	private void startPlatUpdate() {
		platUpdate.startPlatUpdate();
	}

	private static void setTrayImg() {
		mark =true;
		if (SystemTray.isSupported()) {
			SystemTray st = SystemTray.getSystemTray();
			PopupMenu pop = new PopupMenu();
			pop.setFont(UI.normalFont(14));
			ti = new TrayIcon(ImageUtils.getByName("logo.png"));
			try {
				st.add(ti);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			Toolkit tk = Toolkit.getDefaultToolkit();
			tk.addAWTEventListener(new MyAWTEventListener(),
					AWTEvent.KEY_EVENT_MASK);
			ti.setPopupMenu(pop);
			// String systemCode = System.getProperty("file.encoding");
			// System.out.println("systemCode:" + systemCode);
			ti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						MainFrame.getInstance().setVisible(true);
					}
					if (e.getButton() == 3) {
						Point point = e.getPoint();
						showPopup(point);
					}
				}
			});
		}
	}

	protected synchronized static void showPopup(Point point) {
		if (popupFrame == null) {
			popupFrame = new PopupFrame();
			ListItem font = new ListItem("选择字体", 188, 25);
			PopupFrame fontPop = new PopupFrame();
			font.addPop(fontPop);
			ListItem font1 = new ListItem("微软雅黑", 188, 25);
			ListItem font2 = new ListItem("萝莉体", 188, 25);
			ListItem font3 = new ListItem("华文行楷", 188, 25);
			ListItem font4 = new ListItem("汉仪舒同体简", 188, 25);
			ListItem font5 = new ListItem("汉仪中圆繁", 188, 25);
			ListItem font6 = new ListItem("汉仪小隶书简", 188, 25);
			ListItem showItem = new ListItem("打开主面板", 188, 25);
			fontPop.addItem(font1);
			fontPop.addItem(font2);
			fontPop.addItem(font3);
			fontPop.addItem(font4);
			fontPop.addItem(font5);
			fontPop.addItem(font6);
			font1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.微软雅黑);
					MainFrame.getInstance().repaint();
				}
			});
			font2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.萝莉体);
					MainFrame.getInstance().repaint();
				}
			});
			font3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.华文行楷);
					MainFrame.getInstance().repaint();
				}
			});
			font4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.汉仪舒同体简);
					MainFrame.getInstance().repaint();
				}
			});
			font5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.汉仪中圆繁);
					MainFrame.getInstance().repaint();
				}
			});
			font6.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
//					J2seFont.setDefinedFont(J2seFont.汉仪小隶书简);
					MainFrame.getInstance().repaint();
				}
			});
			ListItem styleItem = new ListItem("选择风格", 188, 25);
			ListItem ssItem = new ListItem("默认风格", 188, 25);
			ListItem blueItem = new ListItem("蓝色风格", 188, 25);
			ListItem pinkItem = new ListItem("红色风格", 188, 25);
			PopupFrame stylePop = new PopupFrame();
			styleItem.addPop(stylePop);
			stylePop.addItem(ssItem);
			stylePop.addItem(blueItem);
			stylePop.addItem(pinkItem);

			ListItem updateItem = new ListItem("版本更新", 188, 25);
			ListItem exitItem = new ListItem("退出", 188, 25);

			showItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MainFrame.getInstance().setVisible(true);
				}
			});
			exitItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JIntellitype.getInstance().cleanUp();
					System.exit(1);
				}
			});
			blueItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					J2seColor.setBackgroudColor(Color.BLUE);
					MainFrame.getInstance().repaint();
				}
			});
			ssItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					J2seColor.setBackgroudColor(new Color(153, 133, 245));
					MainFrame.getInstance().repaint();
				}
			});
			pinkItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					J2seColor.setBackgroudColor(Color.PINK);
					MainFrame.getInstance().repaint();
				}
			});
			updateItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					MainFrame.getInstance().updateSystem();
				}
			});
			popupFrame.addItem(font);
			popupFrame.addItem(styleItem);
			popupFrame.addSeparator();
			popupFrame.addItem(showItem);
			popupFrame.addItem(updateItem);
			popupFrame.addSeparator();
			popupFrame.addItem(exitItem);
			popupFrame.show(point);
		} else {
			popupFrame.show(point);
		}
	}

	protected void updateSystem() {
		new Thread() {
			public void run() {
				platUpdate.updateSystem();
			};
		}.start();
	}

	private void setCenterScreen() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int w = (int) d.getWidth();
		int h = (int) d.getHeight();
		this.setLocation((w - this.getWidth()) / 2, (h - this.getHeight()) / 2);
	}
}
