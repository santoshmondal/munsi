package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.MainAccount;

public interface MainAccountDao {

	public Boolean create(MainAccount mainAccount);

	public Boolean update(MainAccount mainAccount);

	public Boolean delete(String _id);

	/**
	 * Get MainAccount instance, MainAccount instance does not load references
	 * instance,
	 * 
	 * @param _id
	 *            MainAccountDao id to be loaded
	 * @return instance of MainAccountDao
	 */
	public MainAccount get(String _id);

	/**
	 * @param _id
	 *            MainAccount id to be loaded
	 * @param withReferences
	 *            - Boolean, if true MainAccount object load its references, if
	 *            false MainAccount object does not load its references.
	 *            (default is false)
	 * @return ArraList of MainAccount
	 */
	public MainAccount get(String _id, Boolean withReferences);

	/**
	 * Get list of MainAccount instance, MainAccount instance does not load
	 * references instance,
	 * 
	 * @return ArraList of MainAccount
	 */
	public List<MainAccount> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true MainAccount object load its references, if
	 *            false MainAccount object does not load its references.(default
	 *            is false)
	 * @return ArraList of MainAccounts
	 */
	public List<MainAccount> getAll(Boolean withReferences);

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 */
	List<String[]> getIdName();

}
