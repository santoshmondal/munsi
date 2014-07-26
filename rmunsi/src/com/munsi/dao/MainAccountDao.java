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
	 * Get list of MainAccount instance, MainAccount instance does not load
	 * references instance,
	 * 
	 * @return ArraList of MainAccount
	 */
	public List<MainAccount> getAll();


}
