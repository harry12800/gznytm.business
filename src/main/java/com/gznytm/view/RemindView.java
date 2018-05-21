/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.gznytm.view;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
 
import com.gznytm.entity.Remind;
import com.gznytm.service.RemindService;
import cn.harry12800.j2se.table.DisplayPanel;
import com.gznytm.config.SysConfig;
/**
 * Service
 * @author 周国柱
 * @version 1.0
 */
@Component
@Transactional(readOnly = true)
public class RemindView  extends DisplayPanel<Remind> {
 	private static final long serialVersionUID = 1L;
	RemindService service;
	public RemindView() {
	}
	@Override
	protected void initMeta(cn.harry12800.j2se.table.DisplayPanel.Meta meta) {
		service = SysConfig.instance.getBean(RemindService.class);
	}
	@Override
	public List<java.awt.Component> getWorkComp() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int saveData(TButton t) {
		return service.save(t);
	}
	@Override
	public int updateData(TButton t) {
		return service.update(t);
	}
	@Override
	public List<TButton> getSearchData(TButton t) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void validateData(TButton t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<TButton> executeQuery(String sql) {
		return service.findBySql(sql);
	}
	@Override
	public int deleteData() {
		if(unSelectTable()) return 0;
		service.deleteByIds(getTableSelectMamaId());
		refresh();
		return 0;
	}
	@Override
	public int executeQueryCount(String mysqlPageContent) {
		// TODO Auto-generated method stub
		return 0;
	}
}
	