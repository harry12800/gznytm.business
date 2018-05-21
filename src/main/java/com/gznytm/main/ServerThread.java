package com.gznytm.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerThread extends Thread {
	
	ServerSocket serverSocket = null;
	static boolean mark = false;
	static boolean dbServer = false;
	static int port =12700;
	public ServerThread(){
		while(serverSocket==null){
			try {
				serverSocket = new ServerSocket(port);
			} catch (IOException e) {
				SendDataClient.send(port);
	//			System.exit(1);
			}
		}
		mark=true;
	}
	public void run() {
		byte[] receiveBuf=new byte[1024];
		Socket clntSocket=null;
		//循环迭代，监听端口号,处理新的连接请求
		while(true){
			//阻塞等待，每接收到一个请求就创建一个新的连接实例
			//获取连接的客户端的SocketAddress
			while(serverSocket==null);
			try {
				clntSocket = serverSocket.accept();
				SocketAddress clientAddress=clntSocket.getRemoteSocketAddress();
				 
				//打印输出连接客户端地址信息
				System.out.println("Handlingclientat"+clientAddress);
				//从客户端接收数据的对象
				InputStream in=clntSocket.getInputStream();
				//向客户端发送数据的对象
				OutputStream out=clntSocket.getOutputStream();
				int recvMsgSize;
				//读取客户端发送的数据后，再发送到客户端
				while((recvMsgSize=in.read(receiveBuf))!=-1) {
					out.write(receiveBuf,0,recvMsgSize);
				}
				out.close();
				String data = new String(receiveBuf);
				System.out.println("接受数据:"+data);
				System.out.println("接收数据长度:"+data.length());
				if("wake".equals(data.trim())){
					System.out.println("唤醒");
					MainFrame.getInstance().setVisible(true);
					JOptionPane.showMessageDialog(null, "已经开启了一个此程序！");
				}
				else if("restart".equals(data.trim())){
					System.exit(1);
					System.out.println("重启");
					MainFrame.instance.dispose();
					MainFrame.instance=null;
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					MainFrame.getInstance().setState(JFrame.NORMAL);
					MainFrame.getInstance().setVisible(true);
					MainFrame.getInstance().toFront();
					MainFrame.getInstance().requestFocus();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
