package com.gznytm.code;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.harry12800.j2se.component.NotifyWindow;
import cn.harry12800.j2se.popup.ListItem;
import cn.harry12800.j2se.popup.PopupFrame;
import cn.harry12800.j2se.table.DisplayPanel;
import cn.harry12800.tools.StringUtils;

import com.gznytm.config.SysConfig;
import com.gznytm.entity.CodeRecord;
import com.gznytm.entity.User;
import com.gznytm.entity.VerificationCode;
import com.gznytm.service.CodeRecordService;
import com.gznytm.service.UserService;
import com.gznytm.service.VerificationCodeService;

@SuppressWarnings("serial")
public class VerificationCodePanel extends DisplayPanel<VerificationCode> {

	VerificationCodeService verificationCodeService;
	CodeRecordService codeRecordService;
	UserService userService;
	private CodeRecord codeRecord;

	public VerificationCodePanel() {
		this.setName("条型码验证");
	}

	protected void initMeta(cn.harry12800.j2se.table.DisplayPanel.Meta meta) {
		verificationCodeService = SysConfig.instance.getBean(VerificationCodeService.class);
		codeRecordService = SysConfig.instance.getBean(CodeRecordService.class);
		userService = SysConfig.instance.getBean(UserService.class);
	}

	public List<Component> getWorkComp() {

		return null;
	}

	public int saveData(VerificationCode t) {
		t.setCreateUser(SysConfig.map.get("user"));
		verificationCodeService.saveData(t);
		return 1;
	}

	public int updateData(VerificationCode t) {
		t.setUpdateUser(SysConfig.map.get("user"));
		verificationCodeService.updateData(t);
		return 1;
	}

	public List<VerificationCode> getSearchData(VerificationCode t) {

		return null;
	}

	public void validateData(VerificationCode t) throws Exception {

	}

	protected VerificationCode getBeanById(String id) {

		return null;
	}

	public List<VerificationCode> executeQuery(String sql) {

		return verificationCodeService.executeQuery(sql);
	}

	public int deleteData() {
		if(unSelectTable()) return 0;
		verificationCodeService.deleteBySql(getTableSelectMamaId());
		refresh();
		return 0;
	}

	public int executeQueryCount(String mysqlPageContent) {

		return 0;
	}
	@Override
	public PopupFrame getPop() {
		popupMenu = new PopupFrame();
		ListItem usedItem = new ListItem("标记为使用");
		ListItem unusedItem = new ListItem("标记为未使用");
		ListItem detailItem = new ListItem("详情页");
		usedItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerificationCode selectBean = getSelectBean();
				selectBean.setStatus(1);
				selectBean.setUpdateDate(new Date());
				selectBean.setUpdateUser(SysConfig.map.get("user"));
				verificationCodeService.updateData(selectBean);
				refresh();
			}
		});
		unusedItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerificationCode selectBean = getSelectBean();
				selectBean.setUpdateDate(new Date());
				selectBean.setUpdateUser(SysConfig.map.get("user"));
				selectBean.setStatus(0);
				verificationCodeService.updateData(selectBean);
				refresh();
			}
		});
		detailItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerificationCode selectBean = getSelectBean();
				User createUser = userService.getUserById(selectBean.getCreateUser());
				String createUserName = createUser.getUserName();
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
				Date createDate = selectBean.getCreateDate();
				String format = sdf.format(createDate);
				NotifyWindow.out("由"+createUserName+"在"+format+"时创建。" );
				if(!StringUtils.isEmpty(selectBean.getUpdateUser())){
					User updateUser = userService.getUserById(selectBean.getUpdateUser());
					String updateUserName = updateUser.getUserName();
					Date updateDate = selectBean.getUpdateDate();
					format = sdf.format(updateDate);
					NotifyWindow.out("由"+updateUserName+"在"+format+"时修改。" );
				}
			}
		});
		popupMenu.addItem(usedItem);
		popupMenu.addItem(unusedItem);
		popupMenu.addItem(detailItem);
		return popupMenu;
	}
	@Override
	public void impBegin() {
		this.codeRecord = new CodeRecord();
		codeRecord.setId(StringUtils.uuid());
		codeRecord.setImportResult(0);
		codeRecord.setUserid(SysConfig.map.get("user"));
		codeRecord.setCreateTime(new Date());
		codeRecordService.saveData(codeRecord );
	}

	@Override
	protected void impEnd(int count) {
		codeRecord.setImportResult(1);
		codeRecord.setCount(count);
		codeRecordService.updateData(codeRecord);
	}
	@Override
	protected void impException(Exception e,VerificationCode t) {
		verificationCodeService.deleteByRecordId(codeRecord.getId()); 
		NotifyWindow.error("导出错误，重复编号："+t.getCode());
	}
	@Override
	protected void impOperBean(VerificationCode t) {
		t.setRecordId(codeRecord.getId());
	}
}
