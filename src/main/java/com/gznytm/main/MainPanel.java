package com.gznytm.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import cn.harry12800.j2se.component.panel.TitlePanel;
import cn.harry12800.j2se.component.panel.TitlePanel.Builder;
import cn.harry12800.j2se.component.panel.TitlePanel.TitleHeight;
import cn.harry12800.j2se.component.utils.ImageUtils;
import cn.harry12800.j2se.popup.ListItem;
import cn.harry12800.j2se.popup.PopupFrame;
import cn.harry12800.j2se.style.ShadowBorder;
import cn.harry12800.j2se.style.UI;

import com.gznytm.login.LoginFrame;

public class MainPanel extends JLayeredPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ContainerPanel containerPanel = new ContainerPanel();
	TitlePanel titlePanel ;
	JLabel picture ;
	public MainPanel(MainFrame mainFrame) {
		setLayout(null);
		setBorder(null);
		setBorder(
				BorderFactory.createCompoundBorder(
						ShadowBorder.newBuilder().shadowSize(3).center().build(), 
				BorderFactory.createLineBorder(UI.backColor))
				);
		Builder builder = TitlePanel.createBuilder(mainFrame);
		builder.hasLogo=true;
		builder.hasMenu=true;
		builder.hasMax=true;
		builder.hasMin=true;
		builder.hasTitle=true;
		builder.titleHeight = TitleHeight.large;
		PopupFrame popupFrame = new PopupFrame(100);
		ListItem item = new ListItem("个人资料");
		ListItem exitItem = new ListItem("退出登录");
		final BufferedImage image = ImageUtils.getByName("desk.jpg") ;
		exitItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.getInstance().dispose();
				MainFrame.instance = null;
				LoginFrame.display();
			}
		});
		popupFrame.addItem(item );
		popupFrame.addItem(exitItem );
		builder.popupFrame= popupFrame;
	
		add(containerPanel, new Integer(10));
		titlePanel = new TitlePanel(builder );
		add(titlePanel,new Integer(10));
//		image.setImage(image.getImage().getScaledInstance( 1300,700,Image.SCALE_DEFAULT));
		picture = new JLabel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				int w = getWidth();
				int h = getHeight();
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.drawImage(image, 0	, 0, w-1,h-1,null);
				g2d.dispose();
			}
		};
		titlePanel.setBounds(0, 0, 1300, 30);
		containerPanel.setBounds(0, 30, 1300, 700-30);
		picture.setBounds(0, 0,1300, 700);
		add(picture, new Integer(Integer.MIN_VALUE));
		//add(new AppMenuPanel(SysConfig.map.get("user")), BorderLayout.WEST);
//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent e) {
//				Component component2 = e.getComponent();
//				System.out.println(component2);
//				super.componentResized(e);
//			}
//		}); 
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==27){
					System.exit(0);
				}
			}
		});
	}
	protected void paintComponent(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		titlePanel.setBounds(0, 0, w, 30);
		containerPanel.setBounds(0, 30, w, h-30);
		picture.setBounds(0, 0,w, h);
		super.paintComponent(g);
	}
}
