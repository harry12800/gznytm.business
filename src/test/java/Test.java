import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import cn.harry12800.developer.DeveloperUtils;
import cn.harry12800.tools.StringUtils;

public class Test {
	public static void main(String[] args) {
		String[] packageNames={"com.gznytm.entity","com.gznytm.mapper"};
		DeveloperUtils.generateDbEntityByTableName(packageNames, "jdbc:mysql://127.0.0.1:3306/nytm", "root", "admin", "person_application");
//		String content = "asdfaasdf<mo1le>sa</m1ole>dsfdfa中a<mol1e>a<b>asdfs</b>assfd<p>f</p>dfs\rsadf\nsdfgs</m1ole>a<a>sdasasdadsfasa</a>";
//		List<String> splitStack = splitMolecular(content,3);
//		StringUtils.printList("TEST", splitStack);
	}
	public static List<String> splitMolecular(String content,int fontLimit) {
		List<Node> list = splitXml(content);
		System.out.println(list.size() + ":" + content);
		List<String> contentList = new ArrayList<String>(1);
		if(list.isEmpty())
			return StringUtils.split(content, fontLimit);
		Node node = list.get(0);
		/**
		 * 是字符
		 */
		boolean isChar = true;
		/**
		 * 是分子式
		 */
		boolean isMole = false;
		/**
		 * 分子式起始位置
		 */
		int moleStart = 0;
		/**
		 * 分子式的内容长度
		 */
		int moleLen = 0;
		int moleEnd = 0;
		/**
		 * 标签嵌套层次
		 */
		int level = 0;
		int byteLen = 0;
		/**
		 * 上次的分割点
		 */
		int start = 0;
		for (int i = 0;i < content.length();) {
			String nameString ="";
			if (i == node.startLen) {
				i = node.endLen;
				moleEnd = node.endLen;
				nameString =node.name;
				if (level == 0) {
					moleStart = node.startLen;
					moleLen = 0;
				}
				if (!node.name.startsWith("/")) level++; else  level--;
				if(level>0) isMole=true;
				list.remove(0);
				if (!list.isEmpty())
					node = list.get(0);
				isChar = false;
			}
			if(isMole&&level==0)
			{
//				System.out.println(nameString+"   "+byteLen+"  "+moleLen);
			}
			if (!isChar&&isMole&&level==0) {  //分子式结束时是否切割。
				
				isChar = true;
				if(moleLen!=0)
				if (byteLen >= fontLimit * 2) {
						if (byteLen == fontLimit * 2) {
							contentList.add(content.substring(start, i + 1));
							start = i + 1;
							byteLen = 0;
						} else {
							System.out.println("结束符："+nameString+" "+moleStart + " " + byteLen + " "
									+ start);
							contentList.add(content.substring(start, moleStart));
							start = moleStart;
							if (moleLen >= fontLimit * 2) {
								contentList.add(content.substring(moleStart,moleEnd + 1));
								start = moleEnd + 1;
								byteLen = 0;
							} else
								byteLen= moleLen;
						}
				}
				isMole =false;
				i++;
				continue;
			}
			if (!isChar&& isMole){ //是分子式,但是是分子式标签的最后一个字符。
				i++;
				isChar = true;
				continue;
			}
			if (isChar && i < content.length()) {
				char ch = content.charAt(i);
				if (ch > 128) {
					byteLen += 2;
					moleLen += 2;
				} else {
					byteLen++;
					moleLen++;
				}
				if(!isMole){
					System.out.println(""+ch +"  "+byteLen);
				}
				if (!isMole&&byteLen >= fontLimit * 2) {
						contentList.add(content.substring(start, i + 1));
						start = i + 1;
						byteLen = 0;
				}
			}
			i++;
		}
		System.out.println(content.substring(start));
		contentList.add(content.substring(start));
		return contentList;
	}

	/**
	 * 自定义识别标签。
	 * @param list
	 * @param stack
	 * @param node
	 */
	private static void startElement(List<Node> list,Stack<Node> stack,Node node) {
		Set<String> set= new HashSet<>();
		set.add("mole");
		set.add("sub");
		set.add("sup");
		set.add("/mole");
		set.add("/sub");
		set.add("/sup");
		if(set.contains(node.name))
			list.add(new Node(node));
		if (node.name.startsWith("/")) {
			if (("/" + stack.lastElement().name).equals(node.name)) {
				stack.pop();
			}
		} else {
			stack.push(node);
		}
		System.out.println("startElement:" + node);
	}

	static class Node {
		public int startLen;
		public int endLen;
		public String name;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Node [startLen=" + startLen + ", endLen=" + endLen
					+ ", name=" + name + "]";
		}

		public Node() {
		}

		public Node(Node node) {
			this.startLen = node.startLen;
			this.endLen = node.endLen;
			this.name = node.name;
		}
	}
	/**
	 * 分词 使得每行只有fontLimit个字符
	 * 
	 * @param content
	 * @param 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<Node> splitXml(String content) {
		Stack<Node> stack = new Stack<Node>();
		List<Node> queue = new ArrayList<Node>();
		if (content == null)
			content = "";
		// content = content.replaceAll("\\s", "");
		boolean mark = false;
		int markLen = 0;
		boolean startLabel = false;
		char[] item = new char[64];
		Node node = new Node();
		for (int i = 0; i < content.length(); i++) {
			char ch = content.charAt(i);
			if (ch > 128) {
				mark = false;
				markLen = 0;
			} else if (ch == '<') {
				node.startLen = i;
				mark = true;
				markLen = 0;
				startLabel = false;
			} else if (mark) {
				if ("".equals((ch + "").trim())) {
					if (markLen != 0 && !startLabel) {
						node.name = new String(item, 0, markLen);
						startLabel = true;
					}
				} else if (ch == '>') {
					if (markLen != 0) {
						node.endLen = i;
						if (!startLabel)
							node.name = new String(item, 0, markLen);
						// System.out.println(node.name);
						startElement(queue,stack,node);
					}
					startLabel = false;
					markLen = 0;
					mark = false;
				} else {
					if (!startLabel)
						item[markLen++] = ch;
				}
			}
		}
		return queue;
	}
}
