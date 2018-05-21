package com.gznytm.businessorder;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Transparency;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Utils {
	public Icon getHoverCloseImg() {
		int width = 16; 
		int height = 12; 
		/* 创建BufferedImage对象*/ 
		BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB); 
		Graphics2D g2d = image.createGraphics(); 	// 获取Graphics2D 
		/* ---------- 增加下面的代码使得背景透明 -----------------*/ 
		image = g2d.getDeviceConfiguration().createCompatibleImage(16, 12, Transparency.TRANSLUCENT); 
		g2d.dispose(); 
		g2d = image.createGraphics(); 
		/* ---------- 背景透明代码结束 ----------------- 
		 画图 */
		drawLine(g2d,Color.black);
		/*drawCircle(g2d,Color.black);
		释放对象 */
		g2d.dispose(); 
		return new ImageIcon(image);
	}
	public ImageIcon getColseImg( )   {
		int width = 16; 
		int height = 12; 
		/**
		 *  创建BufferedImage对象 
		 */
		BufferedImage image = new BufferedImage(width, height,     BufferedImage.TYPE_INT_RGB); 
		/**
		 *  获取Graphics2D 
		 */
		Graphics2D g2d = image.createGraphics(); 
		/**
		 *  ---------- 增加下面的代码使得背景透明 ----------------- 
		 */
		image = g2d.getDeviceConfiguration().createCompatibleImage(16, 12, Transparency.TRANSLUCENT); 
		g2d.dispose(); 
		g2d = image.createGraphics(); 
		/**
		 *  ---------- 背景透明代码结束 ----------------- 
		 */
		/**
		 *  画图 
		 */
		drawLine(g2d,Color.gray);
		/**
		 * 释放对象 
		 */
		g2d.dispose(); 
		return new ImageIcon(image);
		/**
		 *  保存文件   
		 *  ImageIO.write(image, "png", new File("c:/test.png")); 
		 */
		
	}
	private void drawLine(Graphics2D g,Color color) {
		Color parentfontColor = color;
		g.setColor(parentfontColor);
		/**
		 * 得到当前的画刷
		 */
		Stroke stroke = g.getStroke(); 
		float parentThick = 2.0f;
		g.setStroke(new BasicStroke(parentThick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置新的画刷
		Line2D line2D = new Line2D.Float(3, 3, 13, 13);
		g.draw(line2D);
		g.setStroke(new BasicStroke(parentThick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置新的画刷
		line2D = new Line2D.Float(13, 3, 3, 13);
		g.draw(line2D);
		g.setStroke( stroke ); //将画刷复原
		
	}

/*	private void initCloseImgBtn() {
		Icon icon = getColseImg();
		closeBtn.setContentAreaFilled(false);
		Dimension pref =  new Dimension(16,12);
		closeBtn.setPreferredSize( pref );
		closeBtn.setIcon(icon);
		Cursor c = new Cursor(Cursor.HAND_CURSOR );
		closeBtn.setCursor(c);
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
			}
		});
	}*/

	/*private void drawCircle(Graphics2D g,Color color) {
		// TODO Auto-generated constructor stub
		g.setColor(Color.magenta);
		Stroke stroke = g.getStroke(); //得到当前的画刷
		float parentThick = 1.0f;
		g.setStroke(new BasicStroke(parentThick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)); //设置新的画刷
		g.drawOval(0,-2,15,15);
		g.setStroke( stroke ); //将画刷复原
		
	}*/
}
