package com.gznytm.businessorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.gznytm.config.ConfigPanel;
import com.gznytm.config.SysConfig;
import com.gznytm.entity.BusinessOrder;
import com.gznytm.entity.SerialCode;
import com.gznytm.service.BusinessOrderService;
import com.gznytm.service.SerialCodeService;
import com.newtec.tree2word.excel.ExcelPosition;
import com.newtec.tree2word.excel.ExcelReader;

import cn.harry12800.j2se.calendar.DatePanel.DateActionListener;
import cn.harry12800.j2se.component.HtmlDialog;
import cn.harry12800.j2se.component.NotifyWindow;
import cn.harry12800.j2se.component.btn.ClickAction;
import cn.harry12800.j2se.component.btn.DateChooser;
import cn.harry12800.j2se.component.btn.LabelButton;
import cn.harry12800.j2se.dialog.MessageDialog;
import cn.harry12800.j2se.popup.ListItem;
import cn.harry12800.j2se.popup.PopupFrame;
import cn.harry12800.j2se.table.DisplayPanel;
import cn.harry12800.j2se.table.OperDialog;
import cn.harry12800.j2se.table.OperType;
import cn.harry12800.j2se.utils.PrintDemo;
import cn.harry12800.tools.MachineUtils;
import cn.harry12800.tools.StringUtils;
 
public class BusinessOrderPanel extends DisplayPanel<BusinessOrder>   implements DateActionListener{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BusinessOrderService businessOrderService ;
	SerialCodeService serialCodeService;
	public BusinessOrderPanel() {
		setName("南邮商用订单管理");
	}
	
	/**
	 * 获取businessOrderService
	 *	@return the businessOrderService
	 */
	public BusinessOrderService getBusinessOrderService() {
		return businessOrderService;
	}
	/**
	 * 设置businessOrderService
	 * @param businessOrderService the businessOrderService to set
	 */
	public void setBusinessOrderService(BusinessOrderService businessOrderService) {
		this.businessOrderService = businessOrderService;
	}
	/**
	 * 七天订单按钮
	 */
	public LabelButton weekBtn;
	/**
	 * 打印预览按钮
	 */
	public LabelButton previewBtn;
	/**
	 * 更新尺寸等信息按钮
	 */
	public LabelButton btnUpdate;
	/**
	 * 显示所有订单的按钮
	 */
	public LabelButton showAllBtn;
	/**
	 * 按时间选择的按钮
	 */
	public DateChooser dateChooser;
	/**
	 * 打印按钮
	 */
	public LabelButton printBtn;
	 
	@Override
	protected String getInitSql() {
		String sqlurl = " from business_order  LEFT JOIN scan_code on  business_order.id = scan_code.oid  order by billing_time desc,material_size,require_time,excel_index";
		return sqlurl;
	}
	/**
	 * 时间
	 * @param date
	 */
	public void dateActionClick(String date) {
		String endDate = date + " 23:59:59";
		String sqlurl = " from business_order  LEFT JOIN scan_code on  business_order.id = scan_code.oid  where billing_time >= '"
				+ date + "' and billing_time <= '" + endDate + "' order by billing_time,material_size,require_time,excel_index";
		table.refresh(sqlurl);
	}
	
	@Override
	public List<Component> getWorkComp() {
		initButton();
		List<Component>   list= new ArrayList<Component>(0);
		list.add(btnUpdate );
		list.add(weekBtn );
		list.add(previewBtn );
		list.add(printBtn );
		list.add(showAllBtn );
		list.add(dateChooser );
		return list;
	}
	private void initButton() {
		
		weekBtn = new LabelButton( "七日",100,32);
		previewBtn = new LabelButton("预览",100,32);
		btnUpdate =  new LabelButton("更新信息",100,32);
		printBtn = new LabelButton("打印",100,32);
		showAllBtn = new LabelButton("显示所有",100,32);
		dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		
		weekBtnListener();
		printBtnListener();
		dateChooseListener();
		previewBtnListener();
		showAllBtnListener();
		btnUpdateListener();
	}
	private void dateChooseListener() {
		dateChooser.addDateActionListener(this );
	}
	private void btnUpdateListener() {
		btnUpdate.addMouseListener(new ClickAction(btnUpdate) {
			@Override
			public void leftClick(MouseEvent e) {
				if(unSelectTable())return ;
				businessOrderService.updateOthers(getSelectBeans());
				refresh();
			}
		});  
	}
	private void printBtnListener() {
		printBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(printBtn.contains(e.getPoint())){
				String nameString = MachineUtils.getDefaultPrintName();
				if(nameString==null) {
					new MessageDialog(null, "温馨提示", "无本地打印机服务！");
				}else{
					if(!nameString.equals(ConfigPanel.configs.get("print"))){
						new MessageDialog(null, "温馨提示","设置中的打印机与默认打印机不符，请设置！");
						return ;
					}
				}
				if(unSelectTable()) return ;
				int x[] = table.getSelectedRows();
				for (int i = 0; i < x.length; ++i) {
					String id = "" + table.getModel().getValueAt(x[i], 0);
					BusinessOrder bo = businessOrderService.findById(id);
					String desPath = null;
					try {
						desPath = PrintExcel(bo);
					} catch (Exception e1) {
						NotifyWindow.error(e1.getMessage());
						new MessageDialog(null, "温馨提示",e1.getMessage());
						e1.printStackTrace();
					}
					boolean flag = PrintDemo.printExcelFile(new File(desPath));
					if(flag) {
						businessOrderService.updateIsPrintStatus(id);
					}else{
						JOptionPane.showMessageDialog(null, "打印失败");
					}
				}
				refresh();
				}
			}
		});
	}

	/**
	 * 
	 * 数据就是两种模式。<br>
	 * 第一种  1000 <br>
	 * 第二种 1000(1200)
	 * 得到真实的数量,工厂实际的数量 
	 * @param number
	 * @return
	 */
	public static String getReqCount(String number) {
		String reg ="\\d*\\((\\s*(\\d*)\\s*)\\)|\\d*（(\\d*)）";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(number);
		String res= null;
		while(m.find()){
//			System.out.println(m.group(3));
			if(null!=m.group(1)){
				res = m.group(1);
			}
			if(null!=m.group(3)){
				res = m.group(3);
			}
		}
		return res;
	}

	/**
	 * 
	 * 数据就是两种模式。<br>
	 * 第一种  1000 <br>
	 * 第二种 1000(1200)
	 * 得到需求的数量 1000
	 * @param number
	 * @return
	 */
	public static String getRequireNumber(String number) {
		String reg ="(\\d*)";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(number);
		String res= null;
		while(m.find()){
			if(null!=m.group(1)){
				return m.group(1);
			}
		}
		return res;
	}
	
	private void weekBtnListener() {
		weekBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(weekBtn.contains(e.getPoint())){
				String date = StringUtils.getBeforeDate(7);
				String sqlurl = " from business_order  LEFT JOIN scan_code on  business_order.id = scan_code.oid  where billing_time >= '"
						+ date + "' order by billing_time,material_size,require_time,excel_index";
				refresh(sqlurl);
				}
			};
		});
	}
	private void showAllBtnListener() {
		showAllBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(showAllBtn.contains(e.getPoint())){
					String sqlurl = " from business_order  LEFT JOIN scan_code on " +
							"business_order.id = scan_code.oid order by billing_time desc,material_size,require_time,excel_index";
					refresh(sqlurl);
				}
			}
		});
	}

	private void previewBtnListener() {
		previewBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				if(previewBtn.contains(e.getPoint())){
					if(unSelectTable()) return ;
					BusinessOrder selectBean = getSelectBean();
					String desPath = previewExcel(selectBean);
					int sx = desPath.indexOf('.');
					String t = desPath.substring(0, sx)+".html";
					try {
						ExcelReader.toHtml(desPath, t);
					} catch (Exception e1) {
						NotifyWindow.error(e1.getMessage());
						new MessageDialog(null, "提示", e1.getMessage());
						e1.printStackTrace();
						return ;
					}
					new HtmlDialog("file:///"+t);
				}
			}
		});
	}
	/**
	 * 将该订单打印出来
	 * @param bo
	 */
	protected String previewExcel(BusinessOrder bo) {
		/* 模板文件路径 */
		String month = StringUtils.getCurrentMonth();
		SerialCode x = serialCodeService.getCodeByMonth(month);
		if(x ==null) {
			x = new SerialCode();
			x.setId(StringUtils.getUUID());
			x.setCode(1);
			x.setLastTime(StringUtils.getCurrentTime());
			x.setMonth(month);
			serialCodeService.insert(x);
		}
		String template = SysConfig.map.get("printTemplate");
		String desPath = SysConfig.map.get("tmpFilePath") + "/" + bo.getMaterialCode() + "_" + bo.getOrderid()
				+ ".xls";
		Set<ExcelPosition> set = new HashSet<ExcelPosition>(0);
		try {
			ExcelPosition ep = new ExcelPosition(3, 1, bo.getOldOrderCode());
			set.add(ep);
			DecimalFormat df = new DecimalFormat("0000");
			String str2 = df.format(x.getCode());
//			System.out.println(StringUtils.getCurrentMonth() + str2);
/*			// 第一行
*/			ep = new ExcelPosition(0, 5, "编号：" + StringUtils.getCurrentMonth() + str2);
			set.add(ep);
	/*		// 第二行
*/			ep = new ExcelPosition(1, 1, bo.getOrderid());
			set.add(ep);
			ep = new ExcelPosition(1, 3, bo.getCustomer());
			set.add(ep);
			ep = new ExcelPosition(1, 5, StringUtils.getCurrentDate());
			set.add(ep);
		/*	// 第三行
*/			ep = new ExcelPosition(2, 1, bo.getMaterialCode());
			set.add(ep);
			ep = new ExcelPosition(2, 3, bo.getDescription());
			set.add(ep);
			ep = new ExcelPosition(2, 5, bo.getRequireTime());
			set.add(ep);
		/*	// 第四行
*/			ep = new ExcelPosition(3, 1, bo.getOldOrderCode());
			set.add(ep);
			ep = new ExcelPosition(3, 3, bo.getUnzeroCode());
			set.add(ep);
			ep = new ExcelPosition(3, 5, bo.getCuttingDieCode());
			set.add(ep);
			/*// 第五航
*/			set.add(ep);
			ep = new ExcelPosition(4, 1, getRealNumber(bo.getReqCount()) + "/" + getRequireNumber(bo.getReqCount()));
			set.add(ep);
			ep = new ExcelPosition(4, 3, bo.getMaterial());
			set.add(ep);
			ep = new ExcelPosition(4, 5, bo.getMaterialSize());
			set.add(ep);
			/*// 第六航
*/			ep = new ExcelPosition(5, 1, bo.getMaterial());
			set.add(ep);
			ep = new ExcelPosition(5, 3, getMaterialSpecifications(bo.getMaterialSize()));
			set.add(ep);
			ep = new ExcelPosition(5, 5, getTotalNumber(bo));
			set.add(ep);
			/*//第八航
*/			ep = new ExcelPosition(8, 1, bo.getColor());
			set.add(ep);
			
			ExcelReader.writeCells(template, desPath, set);
		} catch (Exception e) {
			e.printStackTrace();
			NotifyWindow.error(e.getMessage());
		}
		return desPath;
	}
	/**
	 * 得到打印数量
	 * @param number
	 * @return
	 */
	private int getRealNumber(String number) {
		/**
		 * 如果已经自己填写了真实的数量值。
		 */
		String real = getReqCount(number);
		if(real!=null) {
			return Integer.valueOf(real); 
		}
		if(StringUtils.isNull(number))number="0";
		int temp = Integer.valueOf(number);
		if (temp < 50) {
			return 50;
		} else if (temp >= 50 && temp <= 100) {
			double s = (temp * 1.5);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		} else if (temp >= 101 && temp <= 300) {
			double s = (temp * 1.3);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		} else if (temp >= 301 && temp <= 500) {
			double s = (temp * 1.2);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		} else if (temp >= 501 && temp <= 1000) {
			double s = (temp * 1.1);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		} else if (temp >= 1001 && temp <= 5000) {
			double s = (temp * 1.05);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		} else if (temp > 5000) {
			double s = (temp * 1.03);
			temp = (int) s;
			temp -= temp % 10;
			return temp;
		}
		return -1;
	}

	/**
	 * 得到打印的物料尺寸
	 * @param size
	 * @return
	 */
	public String getMaterialSpecifications(String size) {
		if(size != null){
			size= size.trim();
		}else{
			return size + "(未处理)";
		}
		String[] strs = size.split("[*]");
//		System.out.println(strs);
//		System.out.println(strs.length);
		if(strs.length != 2)
			return size + "(未处理)";
		double max = 0;
		double min = 999989;
		for (int i = 0; i < strs.length; i++) {
			double temp = 0;
			try {
				temp = Double.valueOf(strs[i]);
			} catch (Exception e) {
				return size + "(未处理)";
			}
			if (temp > max) {
				max = temp;
			}
			if (temp < min) {
				min = temp;
			}
		}
		if (max > 270) {
			min += 5;
			return min + "(mm)";
		} else {
			max += 5;
			return max + "(mm)";
		}
	}

	public double getMinWidth(String size) {
		String[] strs = size.split("[*]");
		if (strs.length != 2)
			return 0;
		double max = 0;
		double min = 999989;
		for (int i = 0; i < strs.length; i++) {
			double temp = 0;
			try {
				temp = Double.valueOf(strs[i]);
			} catch (Exception e) {
				return -1;
			}
			if (temp > max) {
				max = temp;
			}
			if (temp < min) {
				min = temp;
			}
		}
		return min;
	}

	/**
	 * 得到总长度
	 * @param bo
	 * @return
	 */
	public String getTotalNumber(BusinessOrder bo) {
		double length = getRealNumber(bo.getReqCount()) * getMinWidth(bo.getMaterialSize());
		return length / 1000 + "(m)";
	}

	/**
	 * 将该订单打印出来
	 * 
	 * @param bo
	 * @throws Exception 
	 */
	protected String PrintExcel(BusinessOrder bo) throws Exception {
		/* 模板文件路径 */
		String month = StringUtils.getCurrentMonth();
		SerialCode x = serialCodeService.getCodeByMonth(month);
		if(x ==null) {
			x = new SerialCode();
			x.setId(StringUtils.getUUID());
			x.setCode(1);
			x.setLastTime(StringUtils.getCurrentTime());
			x.setMonth(month);
			serialCodeService.insert(x);
		}
		serialCodeService.updateCodeCurrentMonth(month);
		String template = SysConfig.map.get("printTemplate");
		String desPath = SysConfig.map.get("tmpFilePath") + "/" + bo.getMaterialCode() + "_" + bo.getOrderid()
				+ ".xls";
		Set<ExcelPosition> set = new HashSet<ExcelPosition>(0);
			ExcelPosition ep = new ExcelPosition(3, 1, bo.getOldOrderCode());
			set.add(ep);
			DecimalFormat df = new DecimalFormat("0000");
			String str2 = df.format(x.getCode());
//			System.out.println(StringUtils.getCurrentMonth() + str2);
			int realNumber = getRealNumber(bo.getReqCount());
			 String requireNumber = getRequireNumber(bo.getReqCount());
			/*// 第一行
*/			ep = new ExcelPosition(0, 5, "编号：" + StringUtils.getCurrentMonth() + str2);
			set.add(ep);
			/*// 第二行
*/			ep = new ExcelPosition(1, 1, bo.getOrderid());
			set.add(ep);
			ep = new ExcelPosition(1, 3, bo.getCustomer());
			set.add(ep);
			ep = new ExcelPosition(1, 5, StringUtils.getCurrentDate());
			set.add(ep);
			/*// 第三行
*/			ep = new ExcelPosition(2, 1, bo.getMaterialCode());
			set.add(ep);
			ep = new ExcelPosition(2, 3, bo.getDescription());
			set.add(ep);
			ep = new ExcelPosition(2, 5, bo.getRequireTime());
			set.add(ep);
			/*// 第四行
*/			ep = new ExcelPosition(3, 1, bo.getOldOrderCode());
			set.add(ep);
			ep = new ExcelPosition(3, 3, bo.getUnzeroCode());
			set.add(ep);
			ep = new ExcelPosition(3, 5, bo.getCuttingDieCode());
			set.add(ep);
			/*// 第五航
*/			ep = new ExcelPosition(4, 1, realNumber + "/"+requireNumber);
			set.add(ep);
			ep = new ExcelPosition(4, 3, bo.getMaterial());
			set.add(ep);
			ep = new ExcelPosition(4, 5, bo.getMaterialSize());
			set.add(ep);
			/*// 第六航
*/			ep = new ExcelPosition(5, 1, bo.getMaterial());
			set.add(ep);
			ep = new ExcelPosition(5, 3, getMaterialSpecifications(bo.getMaterialSize()));
			set.add(ep);
			ep = new ExcelPosition(5, 5, getTotalNumber(bo));
			set.add(ep);
			/*//第八航
*/			ep = new ExcelPosition(8, 1, bo.getColor());
			set.add(ep);
			ExcelReader.writeCells(template, desPath, set);
			String number= requireNumber+"(" +realNumber + ")";
			if(!number.equals(bo.getReqCount())){
				bo.setReqCount(number);
				businessOrderService.updateNumber(bo);
			}
		return desPath;
	}
	@Override
	public int saveData(BusinessOrder t) {
		businessOrderService.insert(t);
		return 0;
	}
	@Override
	public int updateData(BusinessOrder t) {
		businessOrderService.update(t);
		return 1;
	}

	@Override
	public void validateData(BusinessOrder t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<BusinessOrder> executeQuery(String sql) {
		return businessOrderService.executeSql(sql);
	}
	@Override
	public int deleteData() {
		if(unSelectTable()) return 0;
		businessOrderService.deleteBySql(getTableSelectMamaId());
		refresh();
		return 0;
	}
	
	@Override
	protected void initMeta(cn.harry12800.j2se.table.DisplayPanel.Meta meta) {
		meta.immediatelyUpdate =true;
		meta.addPanelMeta.height = 400;
		meta.editPanelMeta.height = 400;
		meta.isCanExp = true;
		meta.isCanImp = true;
		businessOrderService= SysConfig.instance.getBean(BusinessOrderService.class);
		serialCodeService = SysConfig.instance.getBean(SerialCodeService.class);
	}
	@Override
	public Component getAddDialogNorthComp(OperDialog<BusinessOrder> dialog) {
//		ImportOrderFilePanel autoPanel = new ImportOrderFilePanel(this,dialog);
		return null;
	}
	@Override
	public PopupFrame getPop() {
		popupMenu = new PopupFrame();
		ListItem editItem = new ListItem("编辑");
		ListItem delItem = new ListItem("删除");
		ListItem redItem = new ListItem("标红");
		ListItem blueItem = new ListItem("标蓝");
		ListItem normalItem = new ListItem("标正常色");
		ListItem updateItem = new ListItem("更新尺寸等信息");
		ListItem selectCodeItem = new ListItem("查询该编号所有订单");
		popupMenu.addItem(editItem);
		popupMenu.addItem(delItem);
		popupMenu.addSeparator();
		popupMenu.addItem(redItem);
		popupMenu.addItem(blueItem);
		popupMenu.addItem(normalItem);
		popupMenu.addSeparator();
		popupMenu.addItem(updateItem);
		popupMenu.addItem(selectCodeItem);
		
		normalItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unSelectTable())return ;
				String id =  getTableSelectFirstId();
				businessOrderService.updateNormal(id);
				getSelectBean().setColorFlag("0");
				getTable().reload(getSelectBean());
			}
		});
		blueItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unSelectTable())return ;
				String id =  getTableSelectFirstId();
				businessOrderService.updateBlue(id);
				getSelectBean().setColorFlag("2");
				getTable().reload(getSelectBean());
			}
		});
		redItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unSelectTable())return ;
				String id =  getTableSelectFirstId();
				businessOrderService.updateRed(id);
				getSelectBean().setColorFlag("1");
				getTable().reload(getSelectBean());
			}
		});
		selectCodeItem.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unSelectTable())return ;
				int selectedRow = getTable().getSelectedRow();
				String c = "" + getTable().getModel().getValueAt(selectedRow, getColumnByFieldName("materialCode"));
				String sqlurl = " from business_order LEFT JOIN scan_code on  business_order.id = scan_code.oid   where material_code = '"
						+ c + "' order by billing_time desc,material_size,require_time,excel_index";
				refresh(sqlurl);
			}
		});
		updateItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unSelectTable())return ;
				businessOrderService.updateOthers(getSelectBeans());
				refresh();

			}
		});
		
		delItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row[] = getTable().getSelectedRows();
				if (row.length == 0)
					JOptionPane.showMessageDialog(null, "请选择数据", "是吧",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					String desc = "" + getTable().getModel().getValueAt(row[0], 7);
					String id = "" + getTable().getModel().getValueAt(row[0], 0);
					int result = JOptionPane.showConfirmDialog(null, "删除数据:"+desc,
							"", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
							Set<String> set = new HashSet<String>(0);
							set.add(id);
							String sql = StringUtils.getSQLInSentence(set, "id");
							businessOrderService.deleteBySql(sql);
							refresh();
					}
				}
			}
		});
		editItem.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = getTable().getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "请选择数据", "是吧",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					new OperDialog<BusinessOrder>(BusinessOrderPanel.this,OperType.EDIT,getSelectBean());
				}
			}
		});
		return popupMenu;
	}
	@Override
	public void addRender(TableColumnModel columnModel) {
		TableColumn desc = columnModel.getColumn(getColumnByFieldName("description"));
		desc.setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel l = new JLabel(value+"") ;
//				l.setBackground(UI.backColor);
				l.setForeground(Color.WHITE);
				l.setOpaque(false);
				Object s = table.getModel().getValueAt(row, getColumnByFieldName("colorFlag"));
				if(isSelected)	{
					l.setOpaque(true);
					l.setBackground(getTable().getSelectionBackground());
				}else{
					l.setOpaque(false);
				}
				
				if( (s+"").contains("1")){
					l.setForeground(Color.red);
					return l;
				}else if((s+"").contains("2")){
					l.setForeground(Color.blue);
					return l;
				}else if(isSelected){
					l.setForeground(Color.WHITE);
					return l;
				}else{
					return l;
				}
			}
		});
		super.addRender(columnModel);
	}

	@Override
	public List<BusinessOrder> getSearchData(BusinessOrder t) {
		return businessOrderService.searchSql(t);
	}

	@Override
	public int executeQueryCount(String mysqlPageContent) {
		return businessOrderService.findCount(mysqlPageContent);
	}

	@Override
	public void dateActionClick(Calendar calendar, String date) {
		
	}
}
