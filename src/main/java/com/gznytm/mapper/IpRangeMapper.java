package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.IpRange;

public interface IpRangeMapper extends CrudDao< IpRange> {
	public IpRange findById(String id);
	public String findByIp(long id);
	public List<IpRange> findAll();
	public int update(IpRange bo);
	public List< IpRange> findByMeriratlCode(String code);
	public void insert( IpRange order);
	public void deleteBySql(String sql);
	public List< IpRange> executeSql(String sql);
	/**
	 * 更新菲林刀模信息
	 * @param bo
	 */
	public void updateOther( IpRange bo);
	public void updateNumber( IpRange bo);
	public void updateMaterialcodeDesc( IpRange order);
	public void updateDesc( IpRange order);
	public void updateIsPrintStatus(String id);
	public void updateRed(String id);
	public void updateBlue(String id);
	public void updateNormal(String id);
}
