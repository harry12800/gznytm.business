package com.gznytm.businessorder;

import java.awt.Component;
import java.util.List;

import cn.harry12800.j2se.table.DisplayPanel;

import com.gznytm.config.SysConfig;
import com.gznytm.entity.Nav;
import com.gznytm.service.NavService;

@SuppressWarnings("serial")
public class NavPanel extends DisplayPanel<Nav>{
	NavService navService ;
	public NavPanel( ) {
		super( );
		setName("菜单管理");
	}

	@Override
	protected void initMeta(cn.harry12800.j2se.table.DisplayPanel.Meta meta) {
		meta.immediatelyUpdate =true;
		navService= SysConfig.instance.getBean(NavService.class);
	}

	@Override
	public List<Component> getWorkComp() {
		return null;
	}

	@Override
	public int saveData(Nav t) {
		navService.saveData(t);
		return 0;
	}

	@Override
	public int updateData(Nav t) {
		navService.updateData(t);
		return 0;
	}

	@Override
	public List<Nav> getSearchData(Nav t) {
		return null;
	}

	@Override
	public void validateData(Nav t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Nav> executeQuery(String sql) {
		return navService.exeSql(sql);
	}

	@Override
	public int deleteData() {
		if(unSelectTable()) return 0;
		navService.deleteBySql(getTableSelectMamaId());
		refresh();
		return 0;
	}

	@Override
	public int executeQueryCount(String mysqlPageContent) {
		
		return 0;
	}

}
