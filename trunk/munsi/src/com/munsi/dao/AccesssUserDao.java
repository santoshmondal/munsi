package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.AccessUser;

public interface AccesssUserDao {

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
	 * @param _id
	 *            AccessUser id to be loaded
	 * @param withReferences
	 *            - Boolean, if true AccessUser object load its references, if false
	 *            AccessUser object does not load its references. (default is false)
	 * @return ArraList of AccessUser
	 */
	public AccessUser get(String _id, Boolean withReferences);

	/**
	 * Get list of AccessUser instance, AccessUser instance does not load references
	 * instance,
	 * 
	 * @return ArraList of AccessUser
	 */
	public List<AccessUser> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true AccessUser object load its references, if false
	 *            AccessUser object does not load its references.(default is false)
	 * @return ArraList of Areas
	 */
	public List<AccessUser> getAll(Boolean withReferences);

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 */
	List<String[]> getIdName();

}
