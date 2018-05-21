package com.gznytm.mapper;

import java.util.List;

import com.gznytm.entity.User;

public interface UserMapper extends  CrudDao<User> {
	public User findById(String id);
	public List<User> findAll() ;
	public int save(User t);
	public int update(User t);
	public List<User> findBySql(String sql);
	public int deleteByIds(String ids);
	public User findUser(User user);
	public User findUserByUserName(User user);
}