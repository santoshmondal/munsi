package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.AccessUser;

public interface AccessUserDao {

	public Boolean create(AccessUser accessUser);

	public Boolean update(AccessUser accessUser);

	public Boolean delete(String _id);

	/**
	 * Get AccessUser instance, AccessUser instance does not load references instance,
	 * 
	 * @param _id - AreaDao id to be loaded
	 * @return instance of AccessUser
	 */
	public AccessUser get(String _id);

	/**
	 * Get list of AccessUser instance, AccessUser instance does not load references
	 * instance,
	 * 
	 * @return ArraList of AccessUser
	 */
	public List<AccessUser> getAll();

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 */
	List<String[]> getIdName();

}
