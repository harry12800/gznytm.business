package com.gznytm.code;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import cn.harry12800.j2se.popup.ListItem;
import cn.harry12800.j2se.popup.PopupFrame;
import cn.harry12800.j2se.table.DisplayPanel;

import com.gznytm.config.SysConfig;
import com.gznytm.entity.CodeRecord;
import com.gznytm.service.CodeRecordService;
import com.gznytm.service.VerificationCodeService;

@SuppressWarnings("serial")
public class CodeRecordPanel extends DisplayPanel<CodeRecord> {
	VerificationCodeService verificationCodeService;
	CodeRecordService codeRecordService;

	public CodeRecordPanel() {
		setName("导入记录");
	}

	@Override
	protected void initMeta(cn.harry12800.j2se.table.DisplayPanel.Meta meta) {
		meta.isCanAdd = false;
		meta.isCanDel = false;
		meta.isCanEdit = false;
		meta.isCanImp = false;
		meta.isCanSearch = false;
		codeRecordService = SysConfig.instance.getBean(CodeRecordService.class);
		verificationCodeService = SysConfig.instance.getBean(VerificationCodeService.class);
	}

	@Override
	public List<Component> getWorkComp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveData(CodeRecord t) {
		t.setUserid(SysConfig.map.get("user"));
		codeRecordService.saveData(t);
		return 1;
	}

	@Override
	public int updateData(CodeRecord t) {
		codeRecordService.updateData(t);
		return 0;
	}

	@Override
	public List<CodeRecord> getSearchData(CodeRecord t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateData(CodeRecord t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CodeRecord> executeQuery(String sql) {
		return codeRecordService.exeSql(sql);
	}

	@Override
	public int deleteData() {
		return 0;
	}

	@Override
	public int executeQueryCount(String mysqlPageContent) {
		return 0;
	}

	@Override
	public PopupFrame getPop() {
		popupMenu = new PopupFrame();
		ListItem usedItem = new ListItem("标记为使用");
		ListItem unusedItem = new ListItem("标记为未使用");
		usedItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CodeRecord selectBean = getSelectBean();
				verificationCodeService.updateUsedByRecordId(selectBean.getId());
				refresh();
			}
		});
		unusedItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CodeRecord selectBean = getSelectBean();
				verificationCodeService.updateUnusedByRecordId(selectBean.getId());
				refresh();
			}
		});
		popupMenu.addItem(usedItem);
		popupMenu.addItem(unusedItem);
		return popupMenu;
	}
}
