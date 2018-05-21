package cn.harry12800.j2se.style;

import java.awt.Color;
import java.awt.Font;

public class UITT {
	public static Color foreColor = new Color(21,92,143);
	public static Color scrollColor = new Color(51,143,255);
	public static Color hoverColor = new Color(215,220,226);
//	public static Color backColor = new Color(91,80,122);
	public static Color backColor = new Color(8,38,65);
	public static Color fontColor = new Color(255,255,255);
	public static Color borderColor=new Color(255,255,255);
	
	public static Font normalFont =new Font("宋体", Font.PLAIN, 12);
	public static Font 微软雅黑Font =new Font("微软雅黑", Font.PLAIN, 12);
//	public static Color backColor = new Color(91,80,122);
	public static Color earnColor = new Color(255,0,0);
	public static Color voidColor = new Color(120,120,120,120);
	public static Color transColor = new Color(0,0,0,0);
	
	public static Font 华文新魏Font =new Font("华文新魏", Font.PLAIN, 14);
	public static Color hoverForeColor = Color.BLACK;
	
	public static Color foreColor(int op){
		return new Color(foreColor.getRed(),foreColor.getGreen(),foreColor.getBlue(),op);
	}
	 
	public static Color GREEN(int op){
		return new Color(Color.green.getRed(),Color.green.getGreen(),Color.green.getBlue(),op);
	}
	public static Color RED(int op){
		return new Color(Color.red.getRed(),Color.red.getGreen(),Color.red.getBlue(),op);
	}
	public static Color backColor(int op) {
		return new Color(backColor.getRed(),backColor.getGreen(),backColor.getBlue(),op);
	}
}
