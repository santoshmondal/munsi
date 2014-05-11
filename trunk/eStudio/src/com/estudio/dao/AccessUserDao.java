package com.estudio.dao;

import java.util.List;

import com.estudio.pojo.AccessUser;

public interface AccessUserDao {

	public Boolean create(AccessUser accessUser);

	public Boolean update(AccessUser accessUser);

	public Boolean delete(String _id);

	public AccessUser get(String _id);

	public List<AccessUser> getAll();

	public AccessUser authenticate(String userName, String password);

	public Long getCount();

}
